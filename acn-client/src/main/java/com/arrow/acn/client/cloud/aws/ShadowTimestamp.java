package com.arrow.acn.client.cloud.aws;

import java.io.Serializable;

public class ShadowTimestamp implements Serializable {
	private static final long serialVersionUID = 5312428554404987433L;

	private long timestamp;

	public ShadowTimestamp withTimestamp(long timestamp) {
		setTimestamp(timestamp);
		return this;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
