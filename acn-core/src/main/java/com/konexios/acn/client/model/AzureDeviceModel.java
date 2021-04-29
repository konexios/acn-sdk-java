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

import com.konexios.acs.client.model.AuditableDocumentModelAbstract;

public class AzureDeviceModel extends AuditableDocumentModelAbstract<AzureDeviceModel> {

	private static final long serialVersionUID = 8960101654526157966L;

	private String azureAccountHid;
	private String gatewayHid;
	private String deviceHid;
	private String iotHubConnectionString;
	private boolean enabled;

	@Override
	protected AzureDeviceModel self() {
		return this;
	}

	public AzureDeviceModel withAzureAccountHid(String azureAccountHid) {
		setAzureAccountHid(azureAccountHid);
		return this;
	}

	public AzureDeviceModel withGatewayHid(String gatewayHid) {
		setGatewayHid(gatewayHid);
		return this;
	}

	public AzureDeviceModel withIotHubConnectionString(String iotHubConnectionString) {
		setIotHubConnectionString(iotHubConnectionString);
		return this;
	}

	public AzureDeviceModel withEnabled(boolean enabled) {
		setEnabled(enabled);
		return this;
	}

	public String getAzureAccountHid() {
		return azureAccountHid;
	}

	public void setAzureAccountHid(String azureAccountHid) {
		this.azureAccountHid = azureAccountHid;
	}

	public String getGatewayHid() {
		return gatewayHid;
	}

	public void setGatewayHid(String gatewayHid) {
		this.gatewayHid = gatewayHid;
	}

	public String getDeviceHid() {
		return deviceHid;
	}

	public void setDeviceHid(String deviceHid) {
		this.deviceHid = deviceHid;
	}

	public String getIotHubConnectionString() {
		return iotHubConnectionString;
	}

	public void setIotHubConnectionString(String iotHubConnectionString) {
		this.iotHubConnectionString = iotHubConnectionString;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
