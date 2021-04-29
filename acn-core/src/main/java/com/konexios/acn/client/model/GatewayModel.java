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

public class GatewayModel extends BaseDeviceModelAbstract<GatewayModel> {
	private static final long serialVersionUID = 8738104557146056949L;

	public enum GatewayType {
		Local, Cloud, Mobile
	}

	private GatewayType type;
	private String deviceType;
	private String osName;
	private String sdkVersion;
	private String applicationHid;

	@Override
	protected GatewayModel self() {
		return this;
	}

	public GatewayModel withType(GatewayType type) {
		setType(type);
		return this;
	}

	public GatewayModel withDeviceType(String deviceType) {
		setDeviceType(deviceType);
		return this;
	}

	public GatewayModel withOsName(String osName) {
		setOsName(osName);
		return this;
	}
	
	public GatewayModel withSdkVersion(String sdkVersion) {
		setSdkVersion(sdkVersion);
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

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getSdkVersion() {
		return sdkVersion;
	}

	public void setSdkVersion(String sdkVersion) {
		this.sdkVersion = sdkVersion;
	}

	public void setApplicationHid(String applicationHid) {
		this.applicationHid = applicationHid;
	}

	public String getApplicationHid() {
		return applicationHid;
	}
}
