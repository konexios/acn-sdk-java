/*******************************************************************************
 * Copyright (c) 2018 Arrow Electronics, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License 2.0
 * which accompanies this distribution, and is available at
 * http://apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *     Arrow Electronics, Inc.
 *     Konexios, Inc.
 *******************************************************************************/
package com.arrow.acn.client.cloud.aws;

import java.io.StringReader;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

import com.arrow.acn.AwsMqttConstants;
import com.arrow.acn.client.IotParameters;
import com.arrow.acn.client.api.AcnClient;
import com.arrow.acn.client.cloud.CloudConnectorAbstract;
import com.arrow.acn.client.cloud.CustomMqttClient;
import com.arrow.acn.client.cloud.TransferMode;
import com.arrow.acn.client.model.AwsConfigModel;
import com.arrow.acn.client.model.DeviceStateRequestModel;
import com.arrow.acn.client.model.DeviceStateUpdateModel;
import com.arrow.acn.client.utils.Utils;
import com.arrow.acs.AcsLogicalException;
import com.arrow.acs.AcsRuntimeException;
import com.arrow.acs.AcsUtils;
import com.arrow.acs.JsonUtils;
import com.arrow.acs.client.api.MqttHttpChannel;
import com.arrow.acs.client.model.CloudRequestModel;
import com.arrow.acs.client.model.CloudResponseModel;

public class AwsConnector extends CloudConnectorAbstract implements MqttHttpChannel {

	private static final int DEFAULT_SHADOW_REQUEST_MONITOR_INTERVAL_SECS = 10;
	private static final int DEFAULT_CONNECTION_RETRY_INTERVAL_SECS = 10;
	private static final int DEFAULT_MESSAGE_RETRY_INTERVAL_SECS = 5;
	private static final int DEFAULT_PUBLISH_QOS = 1;

	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	private final AwsConfigModel model;
	private CustomMqttClient client;
	private Map<String, CloudResponseWrapper> responseMap = new HashMap<>();
	private Set<String> shadowRequestTopics = new HashSet<>();

	private SSLContext sslContext;
	private MqttConnectOptions mqttConnectOptions;

	private Timer shadowRequestMonitorTimer;

	private int shadowRequestMonitorIntervalSecs = DEFAULT_SHADOW_REQUEST_MONITOR_INTERVAL_SECS;
	private int connectionRetryIntervalSecs = DEFAULT_CONNECTION_RETRY_INTERVAL_SECS;
	private int messageRetryIntervalSecs = DEFAULT_MESSAGE_RETRY_INTERVAL_SECS;

	private AtomicBoolean connected = new AtomicBoolean(false);
	private AtomicBoolean connecting = new AtomicBoolean(false);

	private int publishQos = DEFAULT_PUBLISH_QOS;

	public AwsConnector(AwsConfigModel model, String gatewayHid, AcnClient acnClient) {
		super(acnClient);
		this.model = model;
		setGatewayHid(gatewayHid);
	}

	@Override
	public void start() {
		AcsUtils.notNull(model, "model is NULL");
		AcsUtils.notEmpty(getGatewayHid(), "gatewayHid is NULL");
		connectClient();
	}

	private void connectClient() {
		String method = "connectClient";

		if (!connecting.compareAndSet(false, true)) {
			logInfo(method, "connecting is already in progress ...");
		}

		while (true) {
			try {
				connected.set(false);
				closeClient();

				logInfo(method, "instantiating AWS client, host: %s, clientId: %s", model.getHost(), getGatewayHid());
				client = new CustomMqttClient("ssl://" + model.getHost() + ":8883", getGatewayHid());
				client.setOptions(getMqttConnectOptions());
				client.setListener(this::processMessage);

				logInfo(method, "connecting to endpoint: %s", model.getHost());
				client.connect(false, true);
				logInfo(method, "connected successfully!");

				String commandTopic = AwsMqttConstants.COMMAND_TOPIC.replace("<gatewayHid>", getGatewayHid());
				String apiResponseTopic = AwsMqttConstants.API_RESPONSE_TOPIC.replace("<gatewayHid>", getGatewayHid());
				client.subscribe(new String[] { commandTopic, apiResponseTopic });

				// subscribe once
				subscribeShadowRequestTopics();

				// start timer to monitor new device
				startShadowRequestMonitorTimer();

				// route API calls to MQTT
				acnClient.setMqttHttpChannel(this);

				logInfo(method, "client is ready!");
				connected.set(true);

				break;
			} catch (Exception e) {
				logError(method, "Error connecting to AWS, retrying in " + connectionRetryIntervalSecs, e);
				Utils.sleep(connectionRetryIntervalSecs * 1000);
			}
		}
		connecting.set(false);
	}

	@Override
	public void stop() {
		super.stop();
		clearShadowRequestMonitorTimer();
		closeClient();
	}

	public void processMessage(String topic, byte[] payload) {
		String method = "processMessage";
		logInfo(method, "topic: %s, payload size: %d", topic, payload.length);

		if (topic.startsWith(AwsMqttConstants.COMMAND_TOPIC_PREFIX_MATCH)) {
			try {
				service.submit(() -> {
					logInfo(method, "topic: %s", topic);
					validateAndProcessEvent(topic, payload);
				});
			} catch (Exception e) {
				logError(method, e);
			}
		} else if (topic.startsWith(AwsMqttConstants.API_RESPONSE_TOPIC_PREFIX_MATCH)) {
			service.submit(() -> {
				try {
					logInfo(method, "topic: %s, data size: %d", topic, payload.length);

					CloudResponseModel responseModel = JsonUtils.fromJsonBytes(payload, CloudResponseModel.class);
					logDebug(method, "responseModel: %s", JsonUtils.toJson(responseModel));

					CloudResponseWrapper wrapper = responseMap.get(responseModel.getRequestId());
					if (wrapper != null) {
						logDebug(method, "marking request complete: %s", responseModel.getRequestId());
						wrapper.complete(responseModel);
					}
				} catch (Exception e) {
					logError(method, e);
				}
			});
		} else if (topic.endsWith(AwsMqttConstants.SHADOW_UPDATE_DELTA_TOPIC_SUFFIX_MATCH)) {
			try {
				DeviceStateRequestModel request = JsonUtils.fromJsonBytes(payload, ShadowDelta.class).toRequestModel();
				logInfo(method, "topic: %s, request: %s", topic, JsonUtils.toJson(request));
				service.submit(() -> {
					String deviceHid = StringUtils.split(topic, "/")[2];
					receiveDeviceStateRequest(deviceHid, request);
				});
			} catch (Exception e) {
				logError(method, e);
			}
		} else {
			logError(method, "unsupported topic: %s", topic);
		}
	}

	@Override
	public void send(IotParameters payload) {
		String method = "send";
		if (!terminating && checkConnection()) {
			try {
				byte[] data = JsonUtils.toJsonBytes(payload);
				String topic = AwsMqttConstants.TELEMETRY_TOPIC.replace("<gatewayHid>", getGatewayHid())
						.replace("<deviceHid>", payload.getDeviceHid());
				logDebug(method, "sending %d bytes to topic: %s", data.length, topic);
				client.publish(topic, data, publishQos);
			} catch (AcsRuntimeException e) {
				throw e;
			} catch (Throwable t) {
				throw new AcsRuntimeException("unable to send data to AWS", t);
			}
		}
	}

	@Override
	public void sendBatch(List<IotParameters> batch, TransferMode transferMode) {
		if (!terminating && checkConnection()) {
			if (transferMode == TransferMode.GZIP_BATCH) {
				throw new AcsLogicalException(
						"TransferMode not supported for AWS integration: " + TransferMode.GZIP_BATCH.name());
			}
			if (batch != null && batch.size() > 0) {
				batch.forEach(this::send);
			}
		}
	}

	@Override
	public CloudResponseModel sendRequest(CloudRequestModel request, long timeoutSecs) {
		String method = "sendRequest";
		if (!terminating && checkConnection()) {
			try {
				byte[] data = JsonUtils.toJsonBytes(request);
				String topic = AwsMqttConstants.API_REQUEST_TOPIC.replace("<gatewayHid>", getGatewayHid());

				CloudResponseWrapper wrapper = new CloudResponseWrapper();
				responseMap.put(request.getRequestId(), wrapper);

				logDebug(method, "sending %d bytes to topic: %s", data.length, topic);
				client.publish(topic, data, publishQos);

				CloudResponseModel response = wrapper.waitForResponse(timeoutSecs);
				if (response == null) {
					throw new AcsLogicalException("Timeout waiting for response from MQTT channel");
				}
				return response;
			} catch (AcsRuntimeException e) {
				logError(method, e);
				throw e;
			} catch (Exception e) {
				logError(method, e);
				throw new AcsLogicalException("sendRequest failed", e);
			} finally {
				responseMap.remove(request.getRequestId());
			}
		} else {
			throw new AcsLogicalException("connector is terminating!");
		}
	}

	@Override
	protected void sendDeviceStateUpdate(String deviceHid, DeviceStateUpdateModel model) {
		String method = "sendDeviceStateUpdate";
		if (!terminating) {
			checkConnection();
			try {
				String topic = AwsMqttConstants.SHADOW_UPDATE_TOPIC.replace("<deviceHid>", deviceHid);
				ShadowRequest update = ShadowRequest.fromUpdateModel(model);
				logDebug(method, "sending shadow update to topic: %s, data: %s", topic, JsonUtils.toJson(update));
				client.publish(topic, JsonUtils.toJsonBytes(update), publishQos);
			} catch (AcsRuntimeException e) {
				logError(method, e);
				throw e;
			} catch (Exception e) {
				logError(method, e);
				throw new AcsLogicalException("sendDeviceStateUpdate failed", e);
			}
		}
	}

	private boolean checkConnection() {
		String method = "checkConnection";
		while (!connected.get() && !terminating) {
			logWarn(method, "not connected, will check back later ...");
			Utils.sleep(messageRetryIntervalSecs * 1000);
		}
		return connected.get();
	}

	private synchronized MqttConnectOptions getMqttConnectOptions() {
		if (mqttConnectOptions == null) {
			mqttConnectOptions = new MqttConnectOptions();
			mqttConnectOptions.setAutomaticReconnect(false);
			mqttConnectOptions.setCleanSession(false);
			mqttConnectOptions.setSocketFactory(getSslContext().getSocketFactory());
		}
		return mqttConnectOptions;
	}

	private synchronized SSLContext getSslContext() {
		if (sslContext == null) {
			try {
				String keystorePassword = new BigInteger(128,
						new SecureRandom(String.valueOf(System.currentTimeMillis()).getBytes())).toString(32);

				KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
				keystore.load(null, null);

				// load CA cert
				X509Certificate caCert = new JcaX509CertificateConverter()
						.setProvider(BouncyCastleProvider.PROVIDER_NAME)
						.getCertificate((X509CertificateHolder) new PEMParser(new StringReader(model.getCaCert()))
								.readObject());
				keystore.setCertificateEntry("ca-cert", caCert);

				// load client cert
				X509Certificate clientCert = new JcaX509CertificateConverter()
						.setProvider(BouncyCastleProvider.PROVIDER_NAME)
						.getCertificate((X509CertificateHolder) new PEMParser(new StringReader(model.getClientCert()))
								.readObject());
				keystore.setCertificateEntry("client-cert", clientCert);

				PrivateKey privateKey = new JcaPEMKeyConverter().setProvider(BouncyCastleProvider.PROVIDER_NAME)
						.getPrivateKey(
								((PEMKeyPair) new PEMParser(new StringReader(model.getPrivateKey())).readObject())
										.getPrivateKeyInfo());

				// load private key
				keystore.setKeyEntry("private-key", privateKey, keystorePassword.toCharArray(),
						new Certificate[] { clientCert });

				// dummy trust manager
				TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						return null;
					}

					public void checkClientTrusted(X509Certificate[] certs, String authType) {
					}

					public void checkServerTrusted(X509Certificate[] certs, String authType) {
					}
				} };

				KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
				kmf.init(keystore, keystorePassword.toCharArray());

				sslContext = SSLContext.getInstance("TLSv1.2");
				sslContext.init(kmf.getKeyManagers(), trustAllCerts, new SecureRandom());
			} catch (Exception e) {
				throw new AcsLogicalException("unable to prepare keystore", e);
			}
		}
		return sslContext;
	}

	private void startShadowRequestMonitorTimer() {
		String method = "startShadowRequestMonitorTimer";

		clearShadowRequestMonitorTimer();

		shadowRequestMonitorTimer = new Timer(true);
		shadowRequestMonitorTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				subscribeShadowRequestTopics();
			}
		}, 0L, shadowRequestMonitorIntervalSecs * 1000);
		logDebug(method, "started!");
	}

	private void clearShadowRequestMonitorTimer() {
		if (shadowRequestMonitorTimer != null) {
			shadowRequestMonitorTimer.cancel();
			shadowRequestMonitorTimer = null;
			shadowRequestTopics.clear();
		}
	}

	private void closeClient() {
		if (client != null) {
			try {
				client.disconnect();
			} catch (Exception e) {
			} finally {
				client = null;
			}
		}
	}

	private void subscribeShadowRequestTopics() {
		String method = "subscribeShadowRequestTopics";
		getDeviceHids().forEach(deviceHid -> {
			try {
				String topic = AwsMqttConstants.SHADOW_UPDATE_DELTA_TOPIC.replace("<deviceHid>", deviceHid);
				if (shadowRequestTopics.add(topic)) {
					logInfo(method, "subscribing to topic: %s", topic);
					client.subscribe(topic);
				}
			} catch (Exception e) {
				logError(method, e);
			}
		});
	}

	public int getShadowRequestMonitorIntervalSecs() {
		return shadowRequestMonitorIntervalSecs;
	}

	public void setShadowRequestMonitorIntervalSecs(int shadowRequestMonitorIntervalSecs) {
		this.shadowRequestMonitorIntervalSecs = shadowRequestMonitorIntervalSecs;
	}

	public int getConnectionRetryIntervalSecs() {
		return connectionRetryIntervalSecs;
	}

	public void setConnectionRetryIntervalSecs(int connectionRetryIntervalSecs) {
		this.connectionRetryIntervalSecs = connectionRetryIntervalSecs;
	}

	public int getMessageRetryIntervalSecs() {
		return messageRetryIntervalSecs;
	}

	public void setMessageRetryIntervalSecs(int messageRetryIntervalSecs) {
		this.messageRetryIntervalSecs = messageRetryIntervalSecs;
	}

	public int getPublishQos() {
		return publishQos;
	}

	public void setPublishQos(int publishQos) {
		this.publishQos = publishQos;
	}

	private class CloudResponseWrapper {
		CloudResponseModel response;
		boolean ready = false;

		synchronized void complete(CloudResponseModel response) {
			if (!ready) {
				this.response = response;
				this.ready = true;
			}
			this.notifyAll();
		}

		synchronized CloudResponseModel waitForResponse(long timeoutSecs) {
			long timeout = System.currentTimeMillis() + timeoutSecs;
			while (!ready && System.currentTimeMillis() < timeout) {
				try {
					this.wait(timeoutSecs * 1000);
				} catch (InterruptedException e) {
				}
			}
			return response;
		}
	}
}
