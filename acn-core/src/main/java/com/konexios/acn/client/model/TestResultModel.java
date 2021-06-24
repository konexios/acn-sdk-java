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
package com.konexios.acn.client.model;

import java.util.ArrayList;
import java.util.List;

import com.konexios.acs.client.model.AuditableDocumentModelAbstract;

public class TestResultModel extends AuditableDocumentModelAbstract<TestResultModel> {
	private static final long serialVersionUID = -8647007345276915564L;

	// private DeviceCategory category;
	private String objectHid;
	private String status;
	private String testProcedureHid;
	private List<TestResultStepModel> steps = new ArrayList<>();
	private String hid;
	private String started;
	private String ended;

	@Override
	protected TestResultModel self() {
		return this;
	}

	public String getTestProcedureHid() {
		return testProcedureHid;
	}

	public void setTestProcedureHid(String testProcedureId) {
		this.testProcedureHid = testProcedureId;
	}

	public TestResultModel withTestProcedureHid(String testProcedureId) {
		this.testProcedureHid = testProcedureId;
		return this;
	}

	public String getObjectHid() {
		return objectHid;
	}

	public void setObjectHid(String objectId) {
		this.objectHid = objectId;
	}

	public TestResultModel withObjectHid(String objectId) {
		this.objectHid = objectId;
		return this;
	}

	/*
	 * public DeviceCategory getCategory() { return category; }
	 * 
	 * public void setCategory(DeviceCategory category) { this.category = category;
	 * }
	 * 
	 * public TestResultCommonModel withCategory(DeviceCategory category) {
	 * this.category = category; return this; }
	 */

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TestResultModel withStatus(String status) {
		setStatus(status);
		return this;
	}

	public List<TestResultStepModel> getSteps() {
		return steps;
	}

	public void setSteps(List<TestResultStepModel> steps) {
		this.steps = steps;
	}

	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}

	public TestResultModel withHid(String hid) {
		setHid(hid);
		return this;
	}

	public String getStarted() {
		return started;
	}

	public void setStarted(String started) {
		this.started = started;
	}

	public TestResultModel withStarted(String started) {
		setStarted(started);
		return this;
	}

	public String getEnded() {
		return ended;
	}

	public void setEnded(String ended) {
		this.ended = ended;
	}

	public TestResultModel withEnded(String ended) {
		setEnded(ended);
		return this;
	}
}
