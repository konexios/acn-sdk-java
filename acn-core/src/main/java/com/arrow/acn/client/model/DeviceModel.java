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

import java.util.Map;

import com.arrow.acs.client.model.AuditableDocumentModelAbstract;

public class DeviceModel extends AuditableDocumentModelAbstract<DeviceModel> {
	private static final long serialVersionUID = 2652754792835097934L;

	private String uid;
	private String name;
	private String type;
	private String userHid;
	private String gatewayHid;
	private boolean enabled;
	private String createdDate;
	private Map<String, String> info;
	private Map<String, String> properties;

	@Override
	protected DeviceModel self() {
		return this;
	}

	public DeviceModel withUid(String uid) {
		setUid(uid);
		return this;
	}

	public DeviceModel withName(String name) {
		setName(name);
		return this;
	}

	public DeviceModel withType(String type) {
		setType(type);
		return this;
	}

	public DeviceModel withUserHid(String userHid) {
		setUserHid(userHid);
		return this;
	}

	public DeviceModel withGatewayHid(String gatewayHid) {
		setGatewayHid(gatewayHid);
		return this;
	}

	public DeviceModel withEnabled(boolean enabled) {
		setEnabled(enabled);
		return this;
	}

	public DeviceModel withCreatedDate(String createdDate) {
		setCreatedDate(createdDate);
		return this;
	}

	public DeviceModel withInfo(Map<String, String> info) {
		setInfo(info);
		return this;
	}

	public DeviceModel withProperties(Map<String, String> properties) {
		setProperties(properties);
		return this;
	}

	public Map<String, String> getInfo() {
		return info;
	}

	public void setInfo(Map<String, String> info) {
		this.info = info;
	}

	public String getUserHid() {
		return userHid;
	}

	public void setUserHid(String userHid) {
		this.userHid = userHid;
	}

	public String getGatewayHid() {
		return gatewayHid;
	}

	public void setGatewayHid(String gatewayHid) {
		this.gatewayHid = gatewayHid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
}
