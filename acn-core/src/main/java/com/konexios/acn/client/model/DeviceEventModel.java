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

import com.konexios.acs.client.model.TsModelAbstract;

public class DeviceEventModel extends TsModelAbstract<DeviceEventModel> {

	private static final long serialVersionUID = -6550295267735297943L;

	private String deviceActionTypeName;
	private String criteria;
	private String status;

	@Override
	protected DeviceEventModel self() {
		return this;
	}

	public DeviceEventModel withDeviceActionTypeName(String deviceActionTypeName) {
		setDeviceActionTypeName(deviceActionTypeName);
		return this;
	}

	public DeviceEventModel withCriteria(String criteria) {
		setCriteria(criteria);
		return this;
	}

	public DeviceEventModel withStatus(String status) {
		setStatus(status);
		return this;
	}

	public String getDeviceActionTypeName() {
		return deviceActionTypeName;
	}

	public void setDeviceActionTypeName(String deviceActionTypeName) {
		this.deviceActionTypeName = deviceActionTypeName;
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
