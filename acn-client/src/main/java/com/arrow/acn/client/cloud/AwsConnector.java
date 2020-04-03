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
package com.arrow.acn.client.cloud;

import java.io.StringReader;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;

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
import com.arrow.acn.client.model.AwsConfigModel;
import com.arrow.acs.AcsLogicalException;
import com.arrow.acs.AcsRuntimeException;
import com.arrow.acs.AcsSystemException;
import com.arrow.acs.AcsUtils;
import com.arrow.acs.JsonUtils;

public class AwsConnector extends CloudConnectorAbstract {

	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	private final AwsConfigModel model;
	private AWSIotMqttClient client;

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

	private class CommandTopic extends AWSIotTopic {
		public CommandTopic() {
			super(AwsMqttConstants.COMMAND_TOPIC.replace("<gatewayHid>", getGatewayHid()),
					AWSIotQos.valueOf(AwsMqttConstants.QOS));
		}

		@Override
		public void onMessage(AWSIotMessage message) {
			String method = "onMessage";
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
			String method = "onMessage";
			logInfo(method, "topic: %s", message.getTopic());
			validateAndProcessEvent(message.getTopic(), message.getPayload());
		}
	}
}
