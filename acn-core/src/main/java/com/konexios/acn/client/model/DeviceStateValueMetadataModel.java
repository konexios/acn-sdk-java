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

public class DeviceStateValueMetadataModel implements Serializable {
	private static final long serialVersionUID = 2160270507833662715L;

	private String name;
	private String description;
	private DeviceStateValueType type = DeviceStateValueType.String;

	public DeviceStateValueMetadataModel withName(String name) {
		setName(name);
		return this;
	}

	public DeviceStateValueMetadataModel withDescription(String description) {
		setDescription(description);
		return this;
	}

	public DeviceStateValueMetadataModel withType(DeviceStateValueType type) {
		setType(type);
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DeviceStateValueType getType() {
		return type;
	}

	public void setType(DeviceStateValueType type) {
		this.type = type;
	}
}
