package com.arrow.acn.client.cloud.aws;

import com.arrow.acn.client.cloud.aws.job.JobExecutionPayload;

public interface AwsJobListener {
	void notifyNext(String deviceHid, JobExecutionPayload payload);
}
