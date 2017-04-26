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

import com.arrow.acs.client.model.AuditableDocumentModelAbstract;

public class GatewayModel extends AuditableDocumentModelAbstract<GatewayModel> {
	private static final long serialVersionUID = 6827224663160186991L;

	public enum GatewayType {
		Local, Cloud, Mobile
	}

	private String uid;
	private String name;
	private GatewayType type;
	private String userHid;
	private String osName;
	private String softwareName;
	private String softwareVersion;
	private String sdkVersion;
	private String applicationHid;

	@Override
	protected GatewayModel self() {
		return this;
	}

	public GatewayModel withSdkVersion(String sdkVersion) {
		setSdkVersion(sdkVersion);
		return this;
	}

	public GatewayModel withUid(String uid) {
		setUid(uid);
		return this;
	}

	public GatewayModel withName(String name) {
		setName(name);
		return this;
	}

	public GatewayModel withType(GatewayType type) {
		setType(type);
		return this;
	}

	public GatewayModel withUserHid(String userHid) {
		setUserHid(userHid);
		return this;
	}

	public GatewayModel withOsName(String osName) {
		setOsName(osName);
		return this;
	}

	public GatewayModel withSoftwareName(String softwareName) {
		setSoftwareName(softwareName);
		return this;
	}

	public GatewayModel withSoftwareVersion(String softwareVersion) {
		setSoftwareVersion(softwareVersion);
		return this;
	}

	public GatewayModel withApplicationHid(String applicationHid) {
		setApplicationHid(applicationHid);
		return this;
	}

	public GatewayType getType() {
		return type;
	}

	public void setType(GatewayType type) {
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

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getSoftwareName() {
		return softwareName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public String getUserHid() {
		return userHid;
	}

	public void setUserHid(String userHid) {
		this.userHid = userHid;
	}

	public void setApplicationHid(String applicationHid) {
		this.applicationHid = applicationHid;
	}

	public String getApplicationHid() {
		return applicationHid;
	}

	public String getSdkVersion() {
		return sdkVersion;
	}

	public void setSdkVersion(String sdkVersion) {
		this.sdkVersion = sdkVersion;
	}
}
