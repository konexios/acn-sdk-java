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
package com.konexios.acn.client;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;

import com.konexios.acn.client.model.TelemetryItemType;

public class IotParameters extends HashMap<String, String> {
	private static final long serialVersionUID = 9127332851010386582L;

	private String deviceType;
	private String externalId;
	private boolean dirty = false;

	public IotParameters() {
		put(TelemetryItemType.System.buildName(Telemetry.TIMESTAMP), String.valueOf(Instant.now().toEpochMilli()));
	}

	public void setDeviceHid(String deviceHid) {
		put(TelemetryItemType.System.buildName(Telemetry.DEVICE_HID), deviceHid);
	}

	public void setString(String name, String value) {
		put(TelemetryItemType.String.buildName(name), value);
		dirty = true;
	}

	public void setInteger(String name, Integer value) {
		setLong(name, Long.valueOf(value));
		dirty = true;
	}

	public void setLong(String name, Long value) {
		put(TelemetryItemType.Integer.buildName(name), String.valueOf(value));
		dirty = true;
	}

	public void setFloat(String name, Float value, String format) {
		setDouble(name, new Double(value), format);
		dirty = true;
	}

	public void setDouble(String name, Double value, String format) {
		put(TelemetryItemType.Float.buildName(name), String.format(format, value));
		dirty = true;
	}

	public void setBoolean(String name, boolean value) {
		put(TelemetryItemType.Boolean.buildName(name), String.valueOf(value));
		dirty = true;
	}

	public void setDate(String name, LocalDate date) {
		put(TelemetryItemType.Date.buildName(name), date.toString());
		dirty = true;
	}

	public void setDateTime(String name, LocalDateTime datetime) {
		put(TelemetryItemType.Date.buildName(name), datetime.toString());
		dirty = true;
	}

	public void setIntegerSquare(String name, Integer x, Integer y) {
		setLongSquare(name, Long.valueOf(x), Long.valueOf(y));
		dirty = true;
	}

	public void setLongSquare(String name, Long x, Long y) {
		put(TelemetryItemType.IntegerSquare.buildName(name), String.format("%d|%d", x, y));
		dirty = true;
	}

	public void setIntegerCube(String name, Integer x, Integer y, Integer z) {
		setLongCube(name, Long.valueOf(x), Long.valueOf(y), Long.valueOf(z));
		dirty = true;
	}

	public void setLongCube(String name, Long x, Long y, Long z) {
		put(TelemetryItemType.IntegerCube.buildName(name), String.format("%d|%d|%d", x, y, z));
		dirty = true;
	}

	public void setFloatSquare(String name, Float x, Float y, String format) {
		setDoubleSquare(name, new Double(x), new Double(y), format);
		dirty = true;
	}

	public void setDoubleSquare(String name, Double x, Double y, String format) {
		put(TelemetryItemType.IntegerSquare.buildName(name),
				String.format("%s|%s", String.format(format, x), String.format(format, y)));
		dirty = true;
	}

	public void setFloatCube(String name, Float x, Float y, Float z, String format) {
		setDoubleCube(name, new Double(x), new Double(y), new Double(z), format);
		dirty = true;
	}

	public void setDoubleCube(String name, Double x, Double y, Double z, String format) {
		put(TelemetryItemType.FloatCube.buildName(name), String.format("%s|%s|%s", String.format(format, x),
				String.format(format, y), String.format(format, z)));
		dirty = true;
	}

	public void setBinary(String name, byte[] value) {
		if (value == null) {
			value = new byte[0];
		}
		put(TelemetryItemType.Binary.buildName(name), Base64.getEncoder().encodeToString(value));
		dirty = true;
	}

	public String getDeviceHid() {
		return get(TelemetryItemType.System.buildName(Telemetry.DEVICE_HID));
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public boolean isDirty() {
		return dirty;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}
}
