package com.arrow.acn.client.model.aws;

import java.io.Serializable;

import com.arrow.acs.AcsUtils;

public class BaseModel implements Serializable {
	private static final long serialVersionUID = -4372773884671466649L;

	private String arn;

	public BaseModel withArn(String arn) {
		setArn(arn);
		return this;
	}

	public String getArn() {
		return arn;
	}

	public void setArn(String arn) {
		this.arn = arn;
	}

	public boolean hasData() {
		return !AcsUtils.isEmpty(arn) && arn.startsWith("arn:aws");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arn == null) ? 0 : arn.hashCode());
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
		BaseModel other = (BaseModel) obj;
		if (arn == null) {
			if (other.arn != null)
				return false;
		} else if (!arn.equals(other.arn))
			return false;
		return true;
	}
}
