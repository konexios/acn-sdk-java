package com.arrow.acn.client.model;

import java.io.Serializable;

import com.arrow.acn.data.DeviceStateValueType;

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
