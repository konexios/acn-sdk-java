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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.konexios.acs.client.model.DefinitionModelAbstract;

public class DeviceTypeModel extends DefinitionModelAbstract<DeviceTypeModel> {
	private static final long serialVersionUID = -658863900901932192L;

	private List<DeviceTypeTelemetryModel> telemetries = new ArrayList<>();
	private Map<String, DeviceStateValueMetadataModel> stateMetadata = new HashMap<>();
	private AcnDeviceCategory deviceCategory;

	@Override
	protected DeviceTypeModel self() {
		return this;
	}

	public DeviceTypeModel withTelemetries(List<DeviceTypeTelemetryModel> telemetries) {
		setTelemetries(telemetries);
		return this;
	}

	public DeviceTypeModel withStateMetadata(Map<String, DeviceStateValueMetadataModel> stateMetadata) {
		setStateMetadata(stateMetadata);
		return this;
	}

	public DeviceTypeModel withDeviceCategory(AcnDeviceCategory deviceCategory) {
		setDeviceCategory(deviceCategory);
		return this;
	}

	public void setTelemetries(List<DeviceTypeTelemetryModel> telemetries) {
		if (telemetries != null) {
			this.telemetries.addAll(telemetries);
		}
	}

	public List<DeviceTypeTelemetryModel> getTelemetries() {
		return telemetries;
	}

	public Map<String, DeviceStateValueMetadataModel> getStateMetadata() {
		return stateMetadata;
	}

	public void setStateMetadata(Map<String, DeviceStateValueMetadataModel> stateMetadata) {
		this.stateMetadata = stateMetadata;
	}

	public AcnDeviceCategory getDeviceCategory() {
		return deviceCategory;
	}

	public void setDeviceCategory(AcnDeviceCategory deviceCategory) {
		this.deviceCategory = deviceCategory;
	}
}
