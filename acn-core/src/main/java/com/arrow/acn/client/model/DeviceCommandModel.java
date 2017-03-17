/*******************************************************************************
 * Copyright (c) 2017 Arrow Electronics, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License 2.0
 * which accompanies this distribution, and is available at
 * http://apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *     Arrow Electronics, Inc.
 *******************************************************************************/
package com.arrow.acn.client.model;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class DeviceCommandModel implements Serializable {

	private static final long serialVersionUID = -4014798127395307414L;

	private String deviceHid;
	private String command;
	private String payload;

	public void trim() {
		deviceHid = StringUtils.trimToNull(deviceHid);
		command = StringUtils.trimToNull(command);
		payload = StringUtils.trimToNull(payload);
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

}
