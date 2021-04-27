package com.arrow.acn.client.cloud.aws.job;

public enum JobExecutionStatus {
	QUEUED, IN_PROGRESS, FAILED, SUCCEEDED, CANCELED, TIMED_OUT, REJECTED, REMOVED
}