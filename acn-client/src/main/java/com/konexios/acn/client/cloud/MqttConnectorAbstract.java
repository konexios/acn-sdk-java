/*******************************************************************************
 * Copyright 2021 Konexios, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.konexios.acn.client.cloud;

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

import com.konexios.acn.client.ClientConstants.Mqtt;
import com.konexios.acn.client.IotParameters;
import com.konexios.acn.client.api.AcnClient;
import com.konexios.acs.AcsLogicalException;
import com.konexios.acs.AcsUtils;
import com.konexios.acs.JsonUtils;

public abstract class MqttConnectorAbstract extends CloudConnectorAbstract implements MessageListener {
	private int qos;
	private CustomMqttClient client;
	private final String url;

	protected MqttConnectorAbstract(AcnClient acnClient, String gatewayHid, String url) {
		super(acnClient, gatewayHid);
		this.url = url;
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
		String method = "mqttConnectOptions";
		MqttConnectOptions options = new MqttConnectOptions();
		options.setConnectionTimeout(Mqtt.DEFAULT_CONNECTION_TIMEOUT_SECS);
		options.setKeepAliveInterval(Mqtt.DEFAULT_KEEP_ALIVE_INTERVAL_SECS);

		if (url.toLowerCase().startsWith("ssl://")) {
			logInfo(method, "SSL detected in URL, setting SSL context ...");
			options.setSocketFactory(sslContext().getSocketFactory());
		}
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

	protected String getUrl() {
		return url;
	}

	@Override
	public void processMessage(String topic, byte[] payload) {
		String method = "processMessage";
		service.submit(() -> {
			logInfo(method, "...");
			validateAndProcessEvent(topic, payload);
		});
	}

	protected abstract String publisherTopic(IotParameters payload);

	protected abstract String publisherBatchTopic(List<IotParameters> payload);

	protected abstract String publisherGzipBatchTopic(List<IotParameters> payload);

	protected abstract String subscriberTopic();
}
