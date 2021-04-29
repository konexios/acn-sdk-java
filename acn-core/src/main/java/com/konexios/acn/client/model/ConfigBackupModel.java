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

public class ConfigBackupModel extends TsModelAbstract<ConfigBackupModel> {

	private static final long serialVersionUID = 6313590525521543445L;

	public enum Type {
		DEVICE, GATEWAY
	}

	private String name;
	private Type type;
	private String objectHid;
	private DeviceConfigBackupModel deviceConfig;
	private GatewayConfigBackupModel gatewayConfig;

	@Override
	protected ConfigBackupModel self() {
		return this;
	}

	public ConfigBackupModel withName(String name) {
		setName(name);
		return this;
	}

	public ConfigBackupModel withType(Type type) {
		setType(type);
		return this;
	}

	public ConfigBackupModel withDeviceConfig(DeviceConfigBackupModel deviceConfig) {
		setDeviceConfig(deviceConfig);
		return this;
	}

	public ConfigBackupModel withGatewayConfig(GatewayConfigBackupModel gatewayConfig) {
		setGatewayConfig(gatewayConfig);
		return this;
	}

	public ConfigBackupModel withObjectHid(String objectHid) {
		setObjectHid(objectHid);
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getObjectHid() {
		return objectHid;
	}

	public void setObjectHid(String objectHid) {
		this.objectHid = objectHid;
	}

	public DeviceConfigBackupModel getDeviceConfig() {
		return deviceConfig;
	}

	public void setDeviceConfig(DeviceConfigBackupModel deviceConfig) {
		this.deviceConfig = deviceConfig;
	}

	public GatewayConfigBackupModel getGatewayConfig() {
		return gatewayConfig;
	}

	public void setGatewayConfig(GatewayConfigBackupModel gatewayConfig) {
		this.gatewayConfig = gatewayConfig;
	}
}
