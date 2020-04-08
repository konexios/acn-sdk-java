package com.arrow.acn.client.cloud.aws;

import java.io.Serializable;
import java.util.Map;

public class ShadowState implements Serializable {
	private static final long serialVersionUID = -3723359783862552711L;

	private Map<String, String> desired;
	private Map<String, String> reported;
	private Map<String, String> delta;

	public ShadowState withDesired(Map<String, String> desired) {
		setDesired(desired);
		return this;
	}

	public ShadowState withReported(Map<String, String> reported) {
		setReported(reported);
		return this;
	}

	public ShadowState withDelta(Map<String, String> delta) {
		setDelta(delta);
		return this;
	}

	public Map<String, String> getDesired() {
		return desired;
	}

	public void setDesired(Map<String, String> desired) {
		this.desired = desired;
	}

	public Map<String, String> getReported() {
		return reported;
	}

	public void setReported(Map<String, String> reported) {
		this.reported = reported;
	}

	public Map<String, String> getDelta() {
		return delta;
	}

	public void setDelta(Map<String, String> delta) {
		this.delta = delta;
	}
}
