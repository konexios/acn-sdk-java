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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GlobalActionDetailsModel implements Serializable {
	private static final long serialVersionUID = -6221877176841062481L;

	private String globalActionTypeHid;
	private String systemName;
	private String name;
	private String description;
	private boolean enabled;
	private List<GlobalActionPropertyModel> properties = new ArrayList<>();
	private List<GlobalActionInputModel> input = new ArrayList<>();

	public String getGlobalActionTypeHid() {
		return globalActionTypeHid;
	}

	public void setGlobalActionTypeHid(String globalActionTypeId) {
		this.globalActionTypeHid = globalActionTypeId;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<GlobalActionPropertyModel> getProperties() {
		return properties;
	}

	public void setProperties(List<GlobalActionPropertyModel> properties) {
		this.properties = properties;
	}

	public List<GlobalActionInputModel> getInput() {
		return input;
	}

	public void setInput(List<GlobalActionInputModel> input) {
		this.input = input;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
