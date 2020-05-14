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
			result.setTimestamp(
					Instant.ofEpochSecond(metadata.values().stream().findFirst().get().getTimestamp()).toString());
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((metadata == null) ? 0 : metadata.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + (int) (timestamp ^ (timestamp >>> 32));
		result = prime * result + (int) (version ^ (version >>> 32));
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
		ShadowDelta other = (ShadowDelta) obj;
		if (metadata == null) {
			if (other.metadata != null)
				return false;
		} else if (!metadata.equals(other.metadata))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (timestamp != other.timestamp)
			return false;
		if (version != other.version)
			return false;
		return true;
	}
}
