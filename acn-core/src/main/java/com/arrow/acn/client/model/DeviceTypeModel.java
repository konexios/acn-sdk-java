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

import java.util.ArrayList;
import java.util.List;

import com.arrow.acs.client.model.DefinitionModelAbstract;

public class DeviceTypeModel extends DefinitionModelAbstract<DeviceTypeModel> {

	private static final long serialVersionUID = -8123803738558398279L;

	private List<DeviceTypeTelemetryModel> telemetries = new ArrayList<>();

	@Override
	protected DeviceTypeModel self() {
		return this;
	}

	public DeviceTypeModel withTelemetries(List<DeviceTypeTelemetryModel> telemetries) {
		setTelemetries(telemetries);
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
}
