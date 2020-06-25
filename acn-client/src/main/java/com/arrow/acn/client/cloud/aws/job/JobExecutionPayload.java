package com.arrow.acn.client.cloud.aws.job;

public class JobExecutionPayload {
	private Long timestamp;
	private JobExecution execution;
	private String clientToken;

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public JobExecution getExecution() {
		return execution;
	}

	public void setExecution(JobExecution execution) {
		this.execution = execution;
	}

	public String getClientToken() {
		return clientToken;
	}

	public void setClientToken(String clientToken) {
		this.clientToken = clientToken;
	}
}
