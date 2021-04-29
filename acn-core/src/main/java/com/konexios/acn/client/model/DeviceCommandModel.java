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

import com.konexios.acs.AcsUtils;

public class DeviceCommandModel implements Serializable {

	private static final long serialVersionUID = -4014798127395307414L;

	private String deviceHid;
	private String command;
	private String payload;
	private Long messageExpiration;

	public void trim() {
		deviceHid = AcsUtils.trimToNull(deviceHid);
		command = AcsUtils.trimToNull(command);
		payload = AcsUtils.trimToNull(payload);
	}

	public DeviceCommandModel withDeviceHid(String deviceHid) {
		setDeviceHid(deviceHid);
		return this;
	}

	public DeviceCommandModel withCommand(String command) {
		setCommand(command);
		return this;
	}

	public DeviceCommandModel withPayload(String payload) {
		setPayload(payload);
		return this;
	}

	public DeviceCommandModel withMessageExpiration(Long expiration) {
		setMessageExpiration(expiration);
		return this;
	}

	public String getDeviceHid() {
		return deviceHid;
	}

	public void setDeviceHid(String deviceHid) {
		this.deviceHid = deviceHid;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public Long getMessageExpiration() {
		return messageExpiration;
	}

	public void setMessageExpiration(Long expiration) {
		this.messageExpiration = expiration;
	}
}
