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

import java.util.List;

import com.konexios.acs.client.model.DefinitionModelAbstract;

public class GlobalActionTypeModel extends DefinitionModelAbstract<GlobalActionTypeModel> {

	private static final long serialVersionUID = -8974718291650615443L;

	private String applicationHid;
	private String systemName;
	private boolean editable;
	private List<GlobalActionTypeParameterModel> parameters;

	@Override
	protected GlobalActionTypeModel self() {
		return this;
	}

	public String getApplicationHid() {
		return applicationHid;
	}

	public void setApplicationHid(String applicationHid) {
		this.applicationHid = applicationHid;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public List<GlobalActionTypeParameterModel> getParameters() {
		return parameters;
	}

	public void setParameters(List<GlobalActionTypeParameterModel> parameters) {
		this.parameters = parameters;
	}
}
