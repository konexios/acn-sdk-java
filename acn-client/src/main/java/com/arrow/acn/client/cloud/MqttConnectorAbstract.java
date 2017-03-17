/*******************************************************************************
 * Copyright (c) 2017 Arrow Electronics, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License 2.0
 * which accompanies this distribution, and is available at
 * http://apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *     Arrow Electronics, Inc.
 *******************************************************************************/
package com.arrow.acn.client.cloud;

import java.util.List;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

import com.arrow.acn.Utils;
import com.arrow.acn.client.ClientConstants.Mqtt;
import com.arrow.acn.client.IotParameters;
import com.arrow.acs.JsonUtils;

public abstract class MqttConnectorAbstract extends CloudConnectorAbstract {
	private int qos;
	private CustomMqttClient client;

	protected MqttConnectorAbstract(String url) {
		client = new CustomMqttClient(url);
	}

	protected MqttConnectorAbstract(String url, String gatewayHid) {
		client = new CustomMqttClient(url, gatewayHid);
		setGatewayHid(gatewayHid);
	}

	@Override
	public void start() {
		client.setOptions(mqttConnectOptions());
		client.setTopics(subscriberTopic());
		client.connect(false);
	}

	@Override
	public void stop() {
		client.disconnect();
	}

	@Override
	public void sendBatch(List<IotParameters> batch, TransferMode transferMode) {
		byte[] input = JsonUtils.toJsonBytes(batch);
		if (input != null && input.length > 0) {
			if (transferMode == TransferMode.GZIP_BATCH) {
				client.publish(publisherGzipBatchTopic(batch), Utils.gzip(input), getQos());
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

	@Override
	public void setListener(MessageListener listener) {
		client.setListener(listener);
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
		return options;
	}

	protected abstract String publisherTopic(IotParameters payload);

	protected abstract String publisherBatchTopic(List<IotParameters> payload);

	protected abstract String publisherGzipBatchTopic(List<IotParameters> payload);

	protected abstract String subscriberTopic();
}
