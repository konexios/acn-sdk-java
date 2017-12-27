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
package com.arrow.acn.client.search;

import java.time.Instant;
import com.arrow.acs.client.search.SearchCriteria;

public class SocialEventDeviceSearchCriteria extends SearchCriteria {

	private static final String UPDATED_BEFORE = "updatedBefore";
	private static final String UPDATED_AFTER = "updatedAfter";
	private static final String CREATED_BEFORE = "createdBefore";
	private static final String CREATED_AFTER = "createdAfter";
	private static final String DEVICE_TYPE_HIDS = "deviceTypeHids";
	private static final String MAC_ADDRESSES = "macAddresses";

	private PageSearchCriteria pageSearchCriteria = new PageSearchCriteria();

	public SocialEventDeviceSearchCriteria withUpdatedBefore(Instant updatedBefore) {
		simpleCriteria.put(UPDATED_BEFORE, updatedBefore.toString());
		return this;
	}

	public SocialEventDeviceSearchCriteria withUpdatedUfter(Instant updatedAfter) {
		simpleCriteria.put(UPDATED_AFTER, updatedAfter.toString());
		return this;
	}
	
	public SocialEventDeviceSearchCriteria withCreatedBefore(Instant createdBefore) {
		simpleCriteria.put(CREATED_BEFORE, createdBefore.toString());
		return this;
	}

	public SocialEventDeviceSearchCriteria withCreatedUfter(Instant createdAfter) {
		simpleCriteria.put(CREATED_AFTER, createdAfter.toString());
		return this;
	}

	public SocialEventDeviceSearchCriteria withDeviceTypeHids(String... deviceTypeHids) {
		arrayCriteria.put(DEVICE_TYPE_HIDS, deviceTypeHids);
		return this;
	}
	
	public SocialEventDeviceSearchCriteria withMacAddresses(String... macAddresses) {
		arrayCriteria.put(MAC_ADDRESSES, macAddresses);
		return this;
	}

	public SocialEventDeviceSearchCriteria withPage(long page) {
		pageSearchCriteria.withPage(page);
		simpleCriteria.putAll(pageSearchCriteria.getSimpleCriteria());
		return this;
	}

	public SocialEventDeviceSearchCriteria withSize(long size) {
		pageSearchCriteria.withSize(size);
		simpleCriteria.putAll(pageSearchCriteria.getSimpleCriteria());
		return this;
	}
}
