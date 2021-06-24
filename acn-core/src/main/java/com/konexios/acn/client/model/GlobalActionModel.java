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

public class GlobalActionModel extends DefinitionModelAbstract<GlobalActionModel> {

	private static final long serialVersionUID = 852549552944909034L;

	private String globalActionTypeHid;
	private String applicationHid;
	private String systemName;
	private List<GlobalActionPropertyModel> properties = new ArrayList<>();
	private List<GlobalActionInputModel> input = new ArrayList<>();

	@Override
	protected GlobalActionModel self() {
		return this;
	}

	public String getGlobalActionTypeHid() {
		return globalActionTypeHid;
	}

	public void setGlobalActionTypeHid(String globalActionTypeHid) {
		this.globalActionTypeHid = globalActionTypeHid;
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
}
