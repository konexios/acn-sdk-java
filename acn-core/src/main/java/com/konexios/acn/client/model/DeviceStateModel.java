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

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import com.konexios.acs.client.model.ModelAbstract;

public class DeviceStateModel extends ModelAbstract<DeviceStateModel> {
	private static final long serialVersionUID = -7961247436531023595L;

	private String deviceHid;
	private Map<String, DeviceStateValueModel> states = new HashMap<>();

	public DeviceStateModel withDeviceHid(String deviceHid) {
		setDeviceHid(deviceHid);
		return this;
	}

	public DeviceStateModel withStates(Map<String, DeviceStateValueModel> states) {
		setStates(states);
		return this;
	}

	public DeviceStateModel withState(String name, String value) {
		states.put(name, new DeviceStateValueModel().withValue(value).withTimestamp(Instant.now()));
		return this;
	}

	public String getDeviceHid() {
		return deviceHid;
	}

	public void setDeviceHid(String deviceHid) {
		this.deviceHid = deviceHid;
	}

	public Map<String, DeviceStateValueModel> getStates() {
		return states;
	}

	public void setStates(Map<String, DeviceStateValueModel> states) {
		this.states = states;
	}

	@Override
	protected DeviceStateModel self() {
		return this;
	}
}
