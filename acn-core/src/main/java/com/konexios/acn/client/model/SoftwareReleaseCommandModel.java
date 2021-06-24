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

public class SoftwareReleaseCommandModel implements Serializable {
	private static final long serialVersionUID = 3497553393740908159L;

	private String softwareReleaseTransHid;
	private String hid;
	private String fromSoftwareVersion;
	private String toSoftwareVersion;
	private String md5checksum;
	private String tempToken;

	public SoftwareReleaseCommandModel withSoftwareReleaseTransHid(String softwareReleaseTransHid) {
		setSoftwareReleaseTransHid(softwareReleaseTransHid);
		return this;
	}

	public SoftwareReleaseCommandModel withHid(String hid) {
		setHid(hid);
		return this;
	}

	public SoftwareReleaseCommandModel withFromSoftwareVersion(String fromSoftwareVersion) {
		setFromSoftwareVersion(fromSoftwareVersion);
		return this;
	}

	public SoftwareReleaseCommandModel withToSoftwareVersion(String toSoftwareVersion) {
		setToSoftwareVersion(toSoftwareVersion);
		return this;
	}

	public SoftwareReleaseCommandModel withTempToken(String tempToken) {
		setTempToken(tempToken);
		return this;
	}

	public String getSoftwareReleaseTransHid() {
		return softwareReleaseTransHid;
	}

	public void setSoftwareReleaseTransHid(String softwareReleaseTransHid) {
		this.softwareReleaseTransHid = softwareReleaseTransHid;
	}

	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}

	public String getFromSoftwareVersion() {
		return fromSoftwareVersion;
	}

	public void setFromSoftwareVersion(String fromSoftwareVersion) {
		this.fromSoftwareVersion = fromSoftwareVersion;
	}

	public String getToSoftwareVersion() {
		return toSoftwareVersion;
	}

	public void setToSoftwareVersion(String toSoftwareVersion) {
		this.toSoftwareVersion = toSoftwareVersion;
	}

	public String getMd5checksum() {
		return md5checksum;
	}

	public void setMd5checksum(String md5checksum) {
		this.md5checksum = md5checksum;
	}

	public String getTempToken() {
		return tempToken;
	}

	public void setTempToken(String tempToken) {
		this.tempToken = tempToken;
	}
}
