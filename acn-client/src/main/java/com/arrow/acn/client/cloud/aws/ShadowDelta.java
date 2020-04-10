package com.arrow.acn.client.cloud.aws;

import java.io.Serializable;
import java.time.Instant;
import java.util.Map;

import com.arrow.acn.client.model.DeviceStateRequestModel;

public class ShadowDelta implements Serializable {
	private static final long serialVersionUID = -4181249647366766554L;

	private Map<String, String> state;
	private Map<String, ShadowTimestamp> metadata;
	private long version;
	private long timestamp;

	public DeviceStateRequestModel toRequestModel() {
		DeviceStateRequestModel result = new DeviceStateRequestModel().withStates(state);
		if (metadata != null && metadata.size() > 0) {
			result.setTimestamp(Instant.ofEpochMilli(metadata.get(0).getTimestamp()).toString());
		} else {
			result.setTimestamp(Instant.ofEpochSecond(timestamp).toString());
		}
		return result;
	}

	public Map<String, String> getState() {
		return state;
	}

	public void setState(Map<String, String> state) {
		this.state = state;
	}

	public Map<String, ShadowTimestamp> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, ShadowTimestamp> metadata) {
		this.metadata = metadata;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
