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

public class DeviceConfigBackupModel extends BaseDeviceConfigBackupModel {

	private static final long serialVersionUID = 3689405692628372427L;

	private String gatewayHid;
	private String applicationEngineHid;
	private List<DeviceActionModel> actions = new ArrayList<>();

	public DeviceConfigBackupModel withGatewayHid(String gatewayHid) {
		setGatewayHid(gatewayHid);
		return this;
	}

	public DeviceConfigBackupModel withApplicationEngineHid(String applicationEngineHid) {
		setApplicationEngineHid(applicationEngineHid);
		return this;
	}

	public DeviceConfigBackupModel withActions(List<DeviceActionModel> actions) {
		setActions(actions);
		return this;
	}

	public String getGatewayHid() {
		return gatewayHid;
	}

	public void setGatewayHid(String gatewayHid) {
		this.gatewayHid = gatewayHid;
	}

	public String getApplicationEngineHid() {
		return applicationEngineHid;
	}

	public void setApplicationEngineHid(String applicationEngineHid) {
		this.applicationEngineHid = applicationEngineHid;
	}

	public List<DeviceActionModel> getActions() {
		return actions;
	}

	public void setActions(List<DeviceActionModel> actions) {
		this.actions = actions;
	}
}
