package com.arrow.acn.client.model;

import java.io.Serializable;

public class TestResultStepStatusModel implements Serializable {

	private static final long serialVersionUID = 8635999788974235648L;

	private String status;
	private String error;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
