package com.arrow.acn.client.cloud.aws;

import java.io.Serializable;
import java.util.Map;

public class ShadowMetadata implements Serializable {
	private static final long serialVersionUID = -6266452343113284442L;

	private Map<String, ShadowTimestamp> desired;
	private Map<String, ShadowTimestamp> reported;

	public ShadowMetadata withDesired(Map<String, ShadowTimestamp> desired) {
		setDesired(desired);
		return this;
	}

	public ShadowMetadata withReported(Map<String, ShadowTimestamp> reported) {
		setReported(reported);
		return this;
	}

	public Map<String, ShadowTimestamp> getDesired() {
		return desired;
	}

	public void setDesired(Map<String, ShadowTimestamp> desired) {
		this.desired = desired;
	}

	public Map<String, ShadowTimestamp> getReported() {
		return reported;
	}

	public void setReported(Map<String, ShadowTimestamp> reported) {
		this.reported = reported;
	}
}
