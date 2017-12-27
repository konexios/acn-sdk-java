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
import com.arrow.acs.client.search.SearchCriteria;

public class SocialEventRegistrationRegisterCriteria extends SearchCriteria {

	private static final String EVENT_CODE = "eventCode";
	private static final String SOCIAL_EVENT_HID = "socialEventHid";

	public SocialEventRegistrationRegisterCriteria withEventCode(String eventCode) {
		simpleCriteria.put(EVENT_CODE, eventCode);
		return this;
	}

	public SocialEventRegistrationRegisterCriteria withSocialEventHid(String socialEventHid) {
		simpleCriteria.put(SOCIAL_EVENT_HID, socialEventHid);
		return this;
	}
}
