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
package com.konexios.acn.client.search;

import java.time.Instant;

import com.konexios.acs.client.search.SearchCriteria;

public class DeviceSearchCriteria extends SearchCriteria {
	private static final String USER_HID = "userHid";
	private static final String UID = "uid";
	private static final String TYPE = "type";
	private static final String GATEWAY_HID = "gatewayHid";
	private static final String ENABLED = "enabled";
	private static final String CREATED_BEFORE = "createdBefore";
	private static final String CREATED_AFTER = "createdAfter";
	private static final String UPDATED_BEFORE = "updatedBefore";
	private static final String UPDATED_AFTER = "updatedAfter";
	private PageSearchCriteria pageSearchCriteria = new PageSearchCriteria();

	public DeviceSearchCriteria withUserHid(String userHid) {
		simpleCriteria.put(USER_HID, userHid);
		return this;
	}

	public DeviceSearchCriteria withUid(String uid) {
		simpleCriteria.put(UID, uid);
		return this;
	}

	public DeviceSearchCriteria withType(String type) {
		simpleCriteria.put(TYPE, type);
		return this;
	}

	public DeviceSearchCriteria withGatewayHid(String gatewayHid) {
		simpleCriteria.put(GATEWAY_HID, gatewayHid);
		return this;
	}

	public DeviceSearchCriteria withEnabled(boolean enabled) {
		simpleCriteria.put(ENABLED, Boolean.toString(enabled));
		return this;
	}

	public DeviceSearchCriteria withCreatedBefore(Instant createdBefore) {
		simpleCriteria.put(CREATED_BEFORE, createdBefore.toString());
		return this;
	}

	public DeviceSearchCriteria withCreatedAfter(Instant createdAfter) {
		simpleCriteria.put(CREATED_AFTER, createdAfter.toString());
		return this;
	}

	public DeviceSearchCriteria withUpdatedBefore(Instant updatedBefore) {
		simpleCriteria.put(UPDATED_BEFORE, updatedBefore.toString());
		return this;
	}

	public DeviceSearchCriteria withUpdatedAfter(Instant updatedAfter) {
		simpleCriteria.put(UPDATED_AFTER, updatedAfter.toString());
		return this;
	}

	public DeviceSearchCriteria withPage(long page) {
		pageSearchCriteria.withPage(page);
		simpleCriteria.putAll(pageSearchCriteria.getSimpleCriteria());
		return this;
	}

	public DeviceSearchCriteria withSize(long size) {
		pageSearchCriteria.withSize(size);
		simpleCriteria.putAll(pageSearchCriteria.getSimpleCriteria());
		return this;
	}
}
