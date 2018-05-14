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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

import com.arrow.acn.Utils;
import com.arrow.acn.client.ClientConstants.Mqtt;
import com.arrow.acn.client.IotParameters;
import com.arrow.acn.client.api.AcnClient;
import com.arrow.acs.AcsUtils;
import com.arrow.acs.JsonUtils;
import com.arrow.acs.client.model.CloudRequestModel;

public abstract class MqttConnectorAbstract extends CloudConnectorAbstract implements MessageListener {
	private int qos;
	private CustomMqttClient client;
	private String cloudResponseTopic;

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
		String topic = subscriberTopic();
		String cloudResponseTopic = subscriberCloudResponseTopic();
		List<String> topics = new ArrayList<>();
		if (!AcsUtils.isEmpty(topic)) {
			topics.add(topic);
		} else {
			logWarn(method, "no topic to subscribe!");
		}
		if (!AcsUtils.isEmpty(cloudResponseTopic)) {
			this.cloudResponseTopic = cloudResponseTopic;
			topics.add(cloudResponseTopic);
		} else {
			logWarn(method, "no cloud response topic to subscribe!");
		}
		client.setTopics(topics.toArray(new String[topics.size()]));
		client.setListener(this);
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

	@Override
	public void processMessage(String topic, byte[] payload) {
		if (topic.equals(cloudResponseTopic)) {
			validateAndProcessCloudResponse(topic, payload);
		} else {
			validateAndProcessEvent(topic, payload);
		}
	}

	@Override
	public void sendCloudRequest(CloudRequestModel cloudRequest) {
		client.publish(publisherCloudRequestTopic(), JsonUtils.toJsonBytes(cloudRequest), getQos());
	}

	protected abstract String publisherTopic(IotParameters payload);

	protected abstract String publisherBatchTopic(List<IotParameters> payload);

	protected abstract String publisherGzipBatchTopic(List<IotParameters> payload);

	protected abstract String publisherCloudRequestTopic();

	protected abstract String subscriberTopic();

	protected abstract String subscriberCloudResponseTopic();
}
