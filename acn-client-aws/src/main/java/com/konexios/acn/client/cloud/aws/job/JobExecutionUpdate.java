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
