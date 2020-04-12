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
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import com.amazonaws.services.iot.client.AWSIotMessage;
import com.amazonaws.services.iot.client.AWSIotMqttClient;
import com.amazonaws.services.iot.client.AWSIotQos;
import com.amazonaws.services.iot.client.AWSIotTopic;
import com.arrow.acn.AwsMqttConstants;
import com.arrow.acn.client.IotParameters;
import com.arrow.acn.client.api.AcnClient;
import com.arrow.acn.client.cloud.CloudConnectorAbstract;
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
	private static final int DEFAULT_NUMBER_OF_CLIENT_THREADS = 20;
	private static final int DEFAULT_CONNECTION_RETRY_INTERVAL_SECS = 10;
	private static final int DEFAULT_MESSAGE_RETRY_INTERVAL_SECS = 5;
	private static final AWSIotQos DEFAULT_PUBLISH_QOS = AWSIotQos.QOS1;
	private static final AWSIotQos DEFAULT_SUBSCRIBE_QOS = AWSIotQos.QOS1;

	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	private final AwsConfigModel model;
	private AWSIotMqttClient client;
	private Map<String, CloudResponseWrapper> responseMap = new HashMap<>();
	private Map<String, ShadowRequestTopic> shadowRequestTopics = new HashMap<>();

	private KeyStore keystore;
	private String keystorePassword;

	private Timer shadowRequestMonitorTimer;

	private int numberOfClientThreads = DEFAULT_NUMBER_OF_CLIENT_THREADS;
	private int shadowRequestMonitorIntervalSecs = DEFAULT_SHADOW_REQUEST_MONITOR_INTERVAL_SECS;
	private int connectionRetryIntervalSecs = DEFAULT_CONNECTION_RETRY_INTERVAL_SECS;
	private int messageRetryIntervalSecs = DEFAULT_MESSAGE_RETRY_INTERVAL_SECS;

	private AtomicBoolean connected = new AtomicBoolean(false);
	private AtomicBoolean connecting = new AtomicBoolean(false);

	private AWSIotQos publishQos = DEFAULT_PUBLISH_QOS;
	private AWSIotQos subscribeQos = DEFAULT_SUBSCRIBE_QOS;

	public AwsConnector(AwsConfigModel model, String gatewayHid, AcnClient acnClient) {
		super(acnClient);
		this.model = model;
		setGatewayHid(gatewayHid);
	}

	@Override
	public void start() {
		AcsUtils.notNull(model, "model is NULL");
		AcsUtils.notEmpty(getGatewayHid(), "gatewayHid is NULL");

		prepareKeystore();
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
				client = new AWSIotMqttClient(model.getHost(), getGatewayHid(), keystore, keystorePassword) {
					@Override
					public void onConnectionClosed() {
						super.onConnectionClosed();
						String method = "onConnectionClosed";
						logError(method, "host: %s", model.getHost());
						Utils.sleep(DEFAULT_CONNECTION_RETRY_INTERVAL_SECS * 1000);
						new Thread(() -> connectClient()).start();
					}

					@Override
					public void onConnectionFailure() {
						super.onConnectionFailure();
						String method = "onConnectionFailure";
						logError(method, "host: %s", model.getHost());
					}

					@Override
					public void onConnectionSuccess() {
						super.onConnectionSuccess();
						String method = "onConnectionSuccess";
						logDebug(method, "host: %s", model.getHost());
					}
				};
				client.setCleanSession(false);
				client.setNumOfClientThreads(numberOfClientThreads);

				// connect to AWS
				client.connect();
				logDebug(method, "connected!");

				// subscribe to commmand topic
				client.subscribe(new CommandTopic());

				// subscribe to api response topic
				client.subscribe(new ApiResponseTopic());

				// route API calls to MQTT
				acnClient.setMqttHttpChannel(this);

				// subscribe once
				subscribeShadowRequestTopics();

				// then continue to monitor for new devices
				startShadowRequestMonitorTimer();

				logInfo(method, "AWS client is ready!");
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

	@Override
	public void send(IotParameters payload) {
		String method = "send";
		if (!terminating && checkConnection()) {
			try {
				byte[] data = JsonUtils.toJsonBytes(payload);
				String topic = AwsMqttConstants.TELEMETRY_TOPIC.replace("<gatewayHid>", getGatewayHid())
						.replace("<deviceHid>", payload.getDeviceHid());
				logDebug(method, "sending %d bytes to topic: %s", data.length, topic);
				client.publish(topic, publishQos, data);
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
				client.publish(topic, publishQos, data);

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
				client.publish(topic, AWSIotQos.QOS1, JsonUtils.toJsonBytes(update));
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

	private void prepareKeystore() {
		if (keystore != null)
			return;
		try {
			keystorePassword = new BigInteger(128,
					new SecureRandom(String.valueOf(System.currentTimeMillis()).getBytes())).toString(32);

			keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			keystore.load(null, null);

			// load CA cert
			X509Certificate caCert = new JcaX509CertificateConverter().setProvider(BouncyCastleProvider.PROVIDER_NAME)
					.getCertificate(
							(X509CertificateHolder) new PEMParser(new StringReader(model.getCaCert())).readObject());
			keystore.setCertificateEntry("ca-cert", caCert);

			// load client cert
			X509Certificate clientCert = new JcaX509CertificateConverter()
					.setProvider(BouncyCastleProvider.PROVIDER_NAME)
					.getCertificate((X509CertificateHolder) new PEMParser(new StringReader(model.getClientCert()))
							.readObject());
			keystore.setCertificateEntry("client-cert", clientCert);

			PrivateKey privateKey = new JcaPEMKeyConverter().setProvider(BouncyCastleProvider.PROVIDER_NAME)
					.getPrivateKey(((PEMKeyPair) new PEMParser(new StringReader(model.getPrivateKey())).readObject())
							.getPrivateKeyInfo());

			// load private key
			keystore.setKeyEntry("private-key", privateKey, keystorePassword.toCharArray(),
					new Certificate[] { clientCert });
		} catch (Exception e) {
			throw new AcsLogicalException("unable to prepare keystore", e);
		}
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
				if (!shadowRequestTopics.containsKey(deviceHid)) {
					ShadowRequestTopic topic = new ShadowRequestTopic(deviceHid);
					logInfo(method, "subscribing to topic: %s", topic.getTopic());
					client.subscribe(topic);
					shadowRequestTopics.put(deviceHid, topic);
				}
			} catch (Exception e) {
				logError(method, e);
			}
		});
	}

	public int getNumberOfClientThreads() {
		return numberOfClientThreads;
	}

	public void setNumberOfClientThreads(int numberOfClientThreads) {
		this.numberOfClientThreads = numberOfClientThreads;
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

	public AWSIotQos getPublishQos() {
		return publishQos;
	}

	public void setPublishQos(AWSIotQos publishQos) {
		this.publishQos = publishQos;
	}

	public AWSIotQos getSubscribeQos() {
		return subscribeQos;
	}

	public void setSubscribeQos(AWSIotQos subscribeQos) {
		this.subscribeQos = subscribeQos;
	}

	private class CommandTopic extends AWSIotTopic {
		public CommandTopic() {
			super(AwsMqttConstants.COMMAND_TOPIC.replace("<gatewayHid>", getGatewayHid()), subscribeQos);
		}

		@Override
		public void onMessage(AWSIotMessage message) {
			String method = "CommandTopic.onMessage";
			try {
				service.submit(() -> {
					logInfo(method, "topic: %s", message.getTopic());
					validateAndProcessEvent(message.getTopic(), message.getPayload());
				});
			} catch (Exception e) {
				logError(method, e);
			}
		}

		@Override
		public void onSuccess() {
			super.onSuccess();
			String method = "CommandTopic.onSuccess";
			logDebug(method, "success: %s", getTopic());
		}

		@Override
		public void onFailure() {
			String method = "CommandTopic.onFailure";
			super.onFailure();
			logError(method, "failure: %s", getTopic());
		}
	}

	private class ApiResponseTopic extends AWSIotTopic {
		public ApiResponseTopic() {
			super(AwsMqttConstants.API_RESPONSE_TOPIC.replace("<gatewayHid>", getGatewayHid()), subscribeQos);
		}

		@Override
		public void onMessage(AWSIotMessage message) {
			String method = "ApiResponseTopic.onMessage";
			service.submit(() -> {
				try {
					byte[] data = message.getPayload();
					logInfo(method, "topic: %s, data size: %d", message.getTopic(), data.length);

					CloudResponseModel responseModel = JsonUtils.fromJsonBytes(data, CloudResponseModel.class);
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
		}

		@Override
		public void onSuccess() {
			super.onSuccess();
			String method = "ApiResponseTopic.onSuccess";
			logDebug(method, "success: %s", getTopic());
		}

		@Override
		public void onFailure() {
			String method = "ApiResponseTopic.onFailure";
			super.onFailure();
			logError(method, "failure: %s", getTopic());
		}
	}

	private class ShadowRequestTopic extends AWSIotTopic {
		private String deviceHid;

		public ShadowRequestTopic(String deviceHid) {
			super(AwsMqttConstants.SHADOW_UPDATE_DELTA_TOPIC.replace("<deviceHid>", deviceHid), subscribeQos);
			this.deviceHid = deviceHid;
		}

		@Override
		public void onMessage(AWSIotMessage message) {
			String method = "ShadowRequestTopic.onMessage";
			try {
				DeviceStateRequestModel request = JsonUtils.fromJsonBytes(message.getPayload(), ShadowDelta.class)
						.toRequestModel();
				logInfo(method, "topic: %s, request: %s", message.getTopic(), JsonUtils.toJson(request));
				service.submit(() -> {
					receiveDeviceStateRequest(deviceHid, request);
				});
			} catch (Exception e) {
				logError(method, e);
			}
		}

		@Override
		public void onSuccess() {
			super.onSuccess();
			String method = "ShadowRequestTopic.onSuccess";
			logDebug(method, "success: %s", getTopic());
		}

		@Override
		public void onFailure() {
			String method = "ShadowRequestTopic.onFailure";
			super.onFailure();
			logError(method, "failure: %s", getTopic());
		}
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
