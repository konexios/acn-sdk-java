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

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.eclipse.paho.client.mqttv3.MqttClient;

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
import com.arrow.acn.client.model.DeviceStateUpdateModel;
import com.arrow.acs.AcsLogicalException;
import com.arrow.acs.AcsRuntimeException;
import com.arrow.acs.AcsSystemException;
import com.arrow.acs.AcsUtils;
import com.arrow.acs.JsonUtils;
import com.arrow.acs.client.api.MqttHttpChannel;
import com.arrow.acs.client.model.CloudRequestModel;
import com.arrow.acs.client.model.CloudResponseModel;

public class AwsConnector extends CloudConnectorAbstract implements MqttHttpChannel {
	private static final long DEFAULT_SHADOW_REQUEST_MONITOR_INTERVAL_SECS = 10L;

	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	private final AwsConfigModel model;
	private AWSIotMqttClient client;
	private Map<String, CloudResponseWrapper> responseMap = new HashMap<>();
	private Map<String, ShadowRequestTopic> shadowRequestTopics = new HashMap<>();

	private Timer shadowRequestMonitorTimer;

	public AwsConnector(AwsConfigModel model, String gatewayHid, AcnClient acnClient) {
		super(acnClient);
		this.model = model;
		setGatewayHid(gatewayHid);
	}

	@Override
	public void start() {
		String method = "start";
		AcsUtils.notNull(model, "model is NULL");
		AcsUtils.notEmpty(getGatewayHid(), "gatewayHid is NULL");

		try {
			String password = new BigInteger(128,
					new SecureRandom(String.valueOf(System.currentTimeMillis()).getBytes())).toString(32);

			KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
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
			keystore.setKeyEntry("private-key", privateKey, password.toCharArray(), new Certificate[] { clientCert });

			logInfo(method, "instantiating client, host: %s", model.getHost());
			client = new AWSIotMqttClient(model.getHost(), MqttClient.generateClientId(), keystore, password);

			// connect to AWS
			client.connect();

			// subscribe to commmand topic
			client.subscribe(new CommandTopic());

			// subscribe to api response topic
			client.subscribe(new ApiResponseTopic());

			// route API calls to MQTT
			acnClient.setMqttHttpChannel(this);

			startShadowRequestMonitorTimer();

			logInfo(method, "AWSIotMqttClient is ready!");
		} catch (AcsRuntimeException e) {
			throw e;
		} catch (Throwable t) {
			throw new AcsSystemException("Unable to start aws connector", t);
		}
	}

	@Override
	public void stop() {
		String method = "stop";
		if (shadowRequestMonitorTimer != null) {
			logInfo(method, "stopping shadowRequestMonitorTimer ...");
			shadowRequestMonitorTimer.cancel();
			shadowRequestMonitorTimer = null;
		}
		if (client != null) {
			try {
				logInfo(method, "stopping client ...");
				client.disconnect();
			} catch (Throwable e) {
			}
			client = null;
		}
	}

	@Override
	public void send(IotParameters payload) {
		String method = "send";
		if (client != null) {
			try {
				byte[] data = JsonUtils.toJsonBytes(payload);
				String topic = AwsMqttConstants.TELEMETRY_TOPIC.replace("<gatewayHid>", getGatewayHid())
						.replace("<deviceHid>", payload.getDeviceHid());
				logInfo(method, "sending %d bytes to topic: %s", data.length, topic);
				client.publish(topic, AWSIotQos.QOS1, data);
			} catch (AcsRuntimeException e) {
				throw e;
			} catch (Throwable t) {
				throw new AcsRuntimeException("unable to send data to AWS", t);
			}
		}
	}

	@Override
	public void sendBatch(List<IotParameters> batch, TransferMode transferMode) {
		if (client != null) {
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
		try {
			byte[] data = JsonUtils.toJsonBytes(request);
			String topic = AwsMqttConstants.API_REQUEST_TOPIC.replace("<gatewayHid>", getGatewayHid());

			CloudResponseWrapper wrapper = new CloudResponseWrapper();
			responseMap.put(request.getRequestId(), wrapper);

			logInfo(method, "sending %d bytes to topic: %s", data.length, topic);
			client.publish(topic, AWSIotQos.QOS1, data);

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
	}

	@Override
	protected void sendDeviceStateUpdate(String deviceHid, DeviceStateUpdateModel model) {
		String method = "sendDeviceStateUpdate";
		try {
			String topic = AwsMqttConstants.SHADOW_UPDATE_TOPIC.replace("<deviceHid>", deviceHid);
			ShadowRequest update = ShadowRequest.fromUpdateModel(model);
			logInfo(method, "sending shadow update to topic: %s, data: %s", topic, JsonUtils.toJson(update));
			client.publish(topic, AWSIotQos.QOS1, JsonUtils.toJsonBytes(update));
		} catch (AcsRuntimeException e) {
			logError(method, e);
			throw e;
		} catch (Exception e) {
			logError(method, e);
			throw new AcsLogicalException("sendDeviceStateUpdate failed", e);
		}
	}

	private void startShadowRequestMonitorTimer() {
		String method = "startShadowRequestMonitorTimer";
		if (shadowRequestMonitorTimer == null) {
			shadowRequestMonitorTimer = new Timer(true);
			shadowRequestMonitorTimer.scheduleAtFixedRate(new ShadowRequestTopicMonitor(), 0L,
					DEFAULT_SHADOW_REQUEST_MONITOR_INTERVAL_SECS * 1000);
			logInfo(method, "started!");
		}
	}

	private class CommandTopic extends AWSIotTopic {
		public CommandTopic() {
			super(AwsMqttConstants.COMMAND_TOPIC.replace("<gatewayHid>", getGatewayHid()),
					AWSIotQos.valueOf(AwsMqttConstants.QOS));
		}

		@Override
		public void onMessage(AWSIotMessage message) {
			String method = "CommandTopic.onMessage";
			logInfo(method, "topic: %s", message.getTopic());
			validateAndProcessEvent(message.getTopic(), message.getPayload());
		}
	}

	private class ApiResponseTopic extends AWSIotTopic {
		public ApiResponseTopic() {
			super(AwsMqttConstants.API_RESPONSE_TOPIC.replace("<gatewayHid>", getGatewayHid()),
					AWSIotQos.valueOf(AwsMqttConstants.QOS));
		}

		@Override
		public void onMessage(AWSIotMessage message) {
			String method = "ApiResponseTopic.onMessage";
			byte[] data = message.getPayload();
			logInfo(method, "topic: %s, data size: %d", message.getTopic(), data.length);

			CloudResponseModel responseModel = JsonUtils.fromJsonBytes(data, CloudResponseModel.class);
			logInfo(method, "responseModel: %s", JsonUtils.toJson(responseModel));

			CloudResponseWrapper wrapper = responseMap.get(responseModel.getRequestId());
			if (wrapper != null) {
				logInfo(method, "marking request complete: %s", responseModel.getRequestId());
				wrapper.complete(responseModel);
			}
		}
	}

	private class ShadowRequestTopic extends AWSIotTopic {
		private String deviceHid;

		public ShadowRequestTopic(String deviceHid) {
			super(AwsMqttConstants.SHADOW_UPDATE_DELTA_TOPIC.replace("<deviceHid>", deviceHid),
					AWSIotQos.valueOf(AwsMqttConstants.QOS));
			this.deviceHid = deviceHid;
		}

		@Override
		public void onMessage(AWSIotMessage message) {
			String method = "ShadowRequestTopic.onMessage";
			byte[] data = message.getPayload();
			logInfo(method, "topic: %s, data size: %d", message.getTopic(), data.length);

			receiveDeviceStateRequest(deviceHid,
					JsonUtils.fromJsonBytes(data, ShadowDocument.class).getPayload().toRequestModel());
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

	private class ShadowRequestTopicMonitor extends TimerTask {
		@Override
		public void run() {
			String method = "ShadowRequestTopicMonitor.run";
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
	}
}
