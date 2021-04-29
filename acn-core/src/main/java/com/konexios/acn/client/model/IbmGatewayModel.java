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

public class IbmGatewayModel extends AuditableDocumentModelAbstract<IbmGatewayModel> {
	private static final long serialVersionUID = -3664854601849510548L;

	private String ibmAccountHid;
	private String gatewayHid;
	private String authToken;
	private boolean enabled;

	@Override
	protected IbmGatewayModel self() {
		return this;
	}

	public IbmGatewayModel withIbmAccountHid(String ibmAccountHid) {
		setIbmAccountHid(ibmAccountHid);
		return this;
	}

	public IbmGatewayModel withGatewayHid(String gatewayHid) {
		setGatewayHid(gatewayHid);
		return this;
	}

	public IbmGatewayModel withAuthToken(String authToken) {
		setAuthToken(authToken);
		return this;
	}

	public IbmGatewayModel withEnabled(boolean enabled) {
		setEnabled(enabled);
		return this;
	}

	public String getIbmAccountHid() {
		return ibmAccountHid;
	}

	public void setIbmAccountHid(String ibmAccountHid) {
		this.ibmAccountHid = ibmAccountHid;
	}

	public String getGatewayHid() {
		return gatewayHid;
	}

	public void setGatewayHid(String gatewayHid) {
		this.gatewayHid = gatewayHid;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
