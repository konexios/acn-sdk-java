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

public class IbmAccountModel extends AuditableDocumentModelAbstract<IbmAccountModel> {
	private static final long serialVersionUID = -5704708138765500714L;

	private String apiKey;
	private String applicationHid;
	private String authToken;
	private String organizationId;
	private String userHid;
	private boolean enabled;

	@Override
	protected IbmAccountModel self() {
		return this;
	}

	public IbmAccountModel withApiKey(String apiKey) {
		setApiKey(apiKey);
		return this;
	}

	public IbmAccountModel withApplicationHid(String applicationHid) {
		setApplicationHid(applicationHid);
		return this;
	}

	public IbmAccountModel withAuthToken(String authToken) {
		setAuthToken(authToken);
		return this;
	}

	public IbmAccountModel withOrganizationId(String organizationId) {
		setOrganizationId(organizationId);
		return this;
	}

	public IbmAccountModel withUserHid(String userHid) {
		setUserHid(userHid);
		return this;
	}

	public IbmAccountModel withEnabled(boolean enabled) {
		setEnabled(enabled);
		return this;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApplicationHid() {
		return applicationHid;
	}

	public void setApplicationHid(String applicationHid) {
		this.applicationHid = applicationHid;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getUserHid() {
		return userHid;
	}

	public void setUserHid(String userHid) {
		this.userHid = userHid;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
