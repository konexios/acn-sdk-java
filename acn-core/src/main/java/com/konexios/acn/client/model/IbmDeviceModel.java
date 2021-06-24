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

import com.konexios.acs.client.model.AuditableDocumentModelAbstract;

public class IbmDeviceModel extends AuditableDocumentModelAbstract<IbmDeviceModel> {
	private static final long serialVersionUID = -1149559101858341899L;

	private String authToken;
	private String ibmAccountHid;
	private String deviceHid;
	private boolean enabled;

	@Override
	protected IbmDeviceModel self() {
		return this;
	}

	public IbmDeviceModel withAuthToken(String authToken) {
		setAuthToken(authToken);
		return this;
	}

	public IbmDeviceModel withIbmAccountHid(String ibmAccountHid) {
		setIbmAccountHid(ibmAccountHid);
		return this;
	}

	public IbmDeviceModel withDeviceHid(String deviceHid) {
		setDeviceHid(deviceHid);
		return this;
	}

	public IbmDeviceModel withEnabled(boolean enabled) {
		setEnabled(enabled);
		return this;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getIbmAccountHid() {
		return ibmAccountHid;
	}

	public void setIbmAccountHid(String ibmAccountHid) {
		this.ibmAccountHid = ibmAccountHid;
	}

	public String getDeviceHid() {
		return deviceHid;
	}

	public void setDeviceHid(String deviceHid) {
		this.deviceHid = deviceHid;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
