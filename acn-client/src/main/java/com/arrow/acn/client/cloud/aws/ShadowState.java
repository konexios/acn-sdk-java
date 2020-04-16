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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((delta == null) ? 0 : delta.hashCode());
		result = prime * result + ((desired == null) ? 0 : desired.hashCode());
		result = prime * result + ((reported == null) ? 0 : reported.hashCode());
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
		ShadowState other = (ShadowState) obj;
		if (delta == null) {
			if (other.delta != null)
				return false;
		} else if (!delta.equals(other.delta))
			return false;
		if (desired == null) {
			if (other.desired != null)
				return false;
		} else if (!desired.equals(other.desired))
			return false;
		if (reported == null) {
			if (other.reported != null)
				return false;
		} else if (!reported.equals(other.reported))
			return false;
		return true;
	}
}
