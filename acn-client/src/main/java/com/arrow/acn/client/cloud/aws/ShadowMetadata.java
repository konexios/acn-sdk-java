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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		ShadowMetadata other = (ShadowMetadata) obj;
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
