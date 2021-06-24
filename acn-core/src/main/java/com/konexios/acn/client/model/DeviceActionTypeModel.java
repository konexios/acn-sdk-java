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

import java.util.HashMap;
import java.util.Map;

import com.konexios.acs.client.model.DefinitionModelAbstract;

public class DeviceActionTypeModel extends DefinitionModelAbstract<DeviceActionTypeModel> {

	private static final long serialVersionUID = 4188855459940428628L;

	private boolean enabled;
	private String systemName;
	private String applicationHid;
	private Map<String, String> parameters = new HashMap<>();

	@Override
	protected DeviceActionTypeModel self() {
		return this;
	}

	public DeviceActionTypeModel withEnabled(boolean enabled) {
		setEnabled(enabled);
		return this;
	}

	public DeviceActionTypeModel withSystemName(String systemName) {
		setSystemName(systemName);
		return this;
	}

	public DeviceActionTypeModel withApplicationHid(String applicationHid) {
		setApplicationHid(applicationHid);
		return this;
	}

	public DeviceActionTypeModel withParameters(Map<String, String> parameters) {
		setParameters(parameters);
		return this;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getApplicationHid() {
		return applicationHid;
	}

	public void setApplicationHid(String applicationHid) {
		this.applicationHid = applicationHid;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

}
