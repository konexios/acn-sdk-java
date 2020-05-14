/*******************************************************************************
 * Copyright (c) 2018 Arrow Electronics, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License 2.0
 * which accompanies this distribution, and is available at
 * http://apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *     Arrow Electronics, Inc.
 *******************************************************************************/
package com.arrow.acn.client.cloud;

import java.math.BigInteger;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

import com.arrow.acn.client.ClientConstants.Mqtt;
import com.arrow.acn.client.IotParameters;
import com.arrow.acn.client.api.AcnClient;
import com.arrow.acs.AcsLogicalException;
import com.arrow.acs.AcsUtils;
import com.arrow.acs.JsonUtils;

public abstract class MqttConnectorAbstract extends CloudConnectorAbstract implements MessageListener {
	private int qos;
	private CustomMqttClient client;

	protected MqttConnectorAbstract(String url, AcnClient acnClient) {
		super(acnClient);
		client = new CustomMqttClient(url);
	}

	protected MqttConnectorAbstract(String url, String gatewayHid, AcnClient acnClient) {
		super(acnClient);
		client = new CustomMqttClient(url, gatewayHid);
		setGatewayHid(gatewayHid);
	}

	@Override
	public void start() {
		String method = "MqttConnectorAbstract.start";
		client.setOptions(mqttConnectOptions());
		client.setListener(this);
		client.connect(true);
		String topic = subscriberTopic();
		if (!AcsUtils.isEmpty(topic)) {
			client.subscribe(topic);
		} else {
			logWarn(method, "no topic to subscribe!");
		}
	}

	@Override
	public void stop() {
		client.disconnect();
		super.stop();
	}

	@Override
	public void sendBatch(List<IotParameters> batch, TransferMode transferMode) {
		byte[] input = JsonUtils.toJsonBytes(batch);
		if (input != null && input.length > 0) {
			if (transferMode == TransferMode.GZIP_BATCH) {
				client.publish(publisherGzipBatchTopic(batch), AcsUtils.gzip(input), getQos());
			} else {
				client.publish(publisherBatchTopic(batch), input, getQos());
			}
		}
	}

	@Override
	public void send(IotParameters payload) {
		if (payload != null) {
			client.publish(publisherTopic(payload), JsonUtils.toJsonBytes(payload), getQos());
		}
	}

	protected int getQos() {
		return qos;
	}

	protected void setQos(int qos) {
		this.qos = qos;
	}

	protected MqttConnectOptions mqttConnectOptions() {
		MqttConnectOptions options = new MqttConnectOptions();
		options.setConnectionTimeout(Mqtt.DEFAULT_CONNECTION_TIMEOUT_SECS);
		options.setKeepAliveInterval(Mqtt.DEFAULT_KEEP_ALIVE_INTERVAL_SECS);
		options.setSocketFactory(sslContext().getSocketFactory());
		return options;
	}

	protected SSLContext sslContext() {
		SSLContext sslContext = null;
		try {
			String keystorePassword = new BigInteger(128,
					new SecureRandom(String.valueOf(System.currentTimeMillis()).getBytes())).toString(32);

			// empty keystore
			KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			keystore.load(null, null);

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
		return sslContext;
	}

	@Override
	public void processMessage(String topic, byte[] payload) {
		validateAndProcessEvent(topic, payload);
	}

	protected abstract String publisherTopic(IotParameters payload);

	protected abstract String publisherBatchTopic(List<IotParameters> payload);

	protected abstract String publisherGzipBatchTopic(List<IotParameters> payload);

	protected abstract String subscriberTopic();
}
