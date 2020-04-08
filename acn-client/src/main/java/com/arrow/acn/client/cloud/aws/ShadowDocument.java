package com.arrow.acn.client.cloud.aws;

import java.io.Serializable;

public class ShadowDocument implements Serializable {
	private static final long serialVersionUID = 6461967977077487220L;

	private long messageNumber;
	private ShadowRequest payload;
	private int qos;
	private long timestamp;
	private String topic;

	public long getMessageNumber() {
		return messageNumber;
	}

	public void setMessageNumber(long messageNumber) {
		this.messageNumber = messageNumber;
	}

	public ShadowRequest getPayload() {
		return payload;
	}

	public void setPayload(ShadowRequest payload) {
		this.payload = payload;
	}

	public int getQos() {
		return qos;
	}

	public void setQos(int qos) {
		this.qos = qos;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
}
