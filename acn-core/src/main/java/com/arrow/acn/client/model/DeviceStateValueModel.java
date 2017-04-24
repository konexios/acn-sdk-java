package com.arrow.acn.client.model;

import java.io.Serializable;
import java.time.Instant;

public class DeviceStateValueModel implements Serializable {
	private static final long serialVersionUID = -2418393009765372079L;

	private String value;
	private String timestamp;

	public DeviceStateValueModel withValue(String value) {
		setValue(value);
		return this;
	}

	public DeviceStateValueModel withTimestamp(Instant timestamp) {
		setTimestamp(timestamp.toString());
		return this;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
