package com.arrow.acn.client.cloud.aws.shadow;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (timestamp ^ (timestamp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShadowTimestamp other = (ShadowTimestamp) obj;
		if (timestamp != other.timestamp)
			return false;
		return true;
	}
}
