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

import com.konexios.acs.client.model.DefinitionModelAbstract;

public class TelemetryUnitModel extends DefinitionModelAbstract<TelemetryUnitModel> {

	private static final long serialVersionUID = -3853274969328395791L;

	private String systemName;

	@Override
	protected TelemetryUnitModel self() {
		return this;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public TelemetryUnitModel withSystemName(String systemName) {
		setSystemName(systemName);
		return this;
	}
}
