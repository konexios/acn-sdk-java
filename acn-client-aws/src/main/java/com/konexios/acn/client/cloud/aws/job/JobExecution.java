/*******************************************************************************
 * Copyright 2021 Konexios, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.konexios.acn.client.cloud.aws.job;

import com.fasterxml.jackson.databind.JsonNode;

public class JobExecution {
	private Long approximateSecondsBeforeTimedOut;
	private Long executionNumber;
	private boolean forceCanceled;
	private String jobId;
	private Long lastUpdatedAt;
	private Long queuedAt;
	private Long startedAt;
	private String status;
	private String thingArn;
	private Long versionNumber;
	private JsonNode jobDocument;

	public Long getApproximateSecondsBeforeTimedOut() {
		return approximateSecondsBeforeTimedOut;
	}

	public void setApproximateSecondsBeforeTimedOut(Long approximateSecondsBeforeTimedOut) {
		this.approximateSecondsBeforeTimedOut = approximateSecondsBeforeTimedOut;
	}

	public Long getExecutionNumber() {
		return executionNumber;
	}

	public void setExecutionNumber(Long executionNumber) {
		this.executionNumber = executionNumber;
	}

	public boolean isForceCanceled() {
		return forceCanceled;
	}

	public void setForceCanceled(boolean forceCanceled) {
		this.forceCanceled = forceCanceled;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public Long getLastUpdatedAt() {
		return lastUpdatedAt;
	}

	public void setLastUpdatedAt(Long lastUpdatedAt) {
		this.lastUpdatedAt = lastUpdatedAt;
	}

	public Long getQueuedAt() {
		return queuedAt;
	}

	public void setQueuedAt(Long queuedAt) {
		this.queuedAt = queuedAt;
	}

	public Long getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(Long startedAt) {
		this.startedAt = startedAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getThingArn() {
		return thingArn;
	}

	public void setThingArn(String thingArn) {
		this.thingArn = thingArn;
	}

	public Long getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}

	public JsonNode getJobDocument() {
		return jobDocument;
	}

	public void setJobDocument(JsonNode jobDocument) {
		this.jobDocument = jobDocument;
	}
}
