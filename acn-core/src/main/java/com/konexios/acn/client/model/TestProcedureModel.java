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

import com.konexios.acs.client.model.DefinitionModelAbstract;

public class TestProcedureModel extends DefinitionModelAbstract<TestProcedureModel> {
	private static final long serialVersionUID = 2938213963854262466L;
	
	private String deviceTypeHid;
	private List<TestProcedureStepModel> steps = new ArrayList<>();

	@Override
	protected TestProcedureModel self() {
		return this;
	}

	public List<TestProcedureStepModel> getSteps() {
		return steps;
	}

	public void setSteps(List<TestProcedureStepModel> testProcedureSteps) {
		this.steps = testProcedureSteps;
	}

	public String getDeviceTypeHid() {
		return deviceTypeHid;
	}

	public void setDeviceTypeHid(String deviceTypeHid) {
		this.deviceTypeHid = deviceTypeHid;
	}
}
