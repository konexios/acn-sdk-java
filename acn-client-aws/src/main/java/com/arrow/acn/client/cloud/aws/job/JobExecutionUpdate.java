package com.arrow.acn.client.cloud.aws.job;

public class JobExecutionUpdate {
	private String status;
	private Long expectedVersion;
	private Long executionNumber;
	private Boolean includeJobExecutionState;
	private Boolean includeJobDocument;
	private Long stepTimeoutInMinutes;
	private String clientToken;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getExpectedVersion() {
		return expectedVersion;
	}

	public void setExpectedVersion(Long expectedVersion) {
		this.expectedVersion = expectedVersion;
	}

	public Long getExecutionNumber() {
		return executionNumber;
	}

	public void setExecutionNumber(Long executionNumber) {
		this.executionNumber = executionNumber;
	}

	public Boolean getIncludeJobExecutionState() {
		return includeJobExecutionState;
	}

	public void setIncludeJobExecutionState(Boolean includeJobExecutionState) {
		this.includeJobExecutionState = includeJobExecutionState;
	}

	public Boolean getIncludeJobDocument() {
		return includeJobDocument;
	}

	public void setIncludeJobDocument(Boolean includeJobDocument) {
		this.includeJobDocument = includeJobDocument;
	}

	public Long getStepTimeoutInMinutes() {
		return stepTimeoutInMinutes;
	}

	public void setStepTimeoutInMinutes(Long stepTimeoutInMinutes) {
		this.stepTimeoutInMinutes = stepTimeoutInMinutes;
	}

	public String getClientToken() {
		return clientToken;
	}

	public void setClientToken(String clientToken) {
		this.clientToken = clientToken;
	}
}
