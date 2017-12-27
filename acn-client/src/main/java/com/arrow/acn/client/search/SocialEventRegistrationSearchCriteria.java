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
import java.util.Arrays;

import com.arrow.acn.client.model.SocialEventRegistrationStatuses;
import com.arrow.acs.client.search.SearchCriteria;

public class SocialEventRegistrationSearchCriteria extends SearchCriteria {

	private static final String UPDATE_BEFORE = "updatedBefore";
	private static final String UPDATE_AFTER = "updatedAfter";
	private static final String CREATED_BEFORE = "createdBefore";
	private static final String CREATED_AFTER = "createdAfter";
	private static final String SOCIAL_EVENT_HIDS = "socialEventHids";
	private static final String NAMES = "names";
	private static final String EMAILS = "emails";
	private static final String STATUSES = "statuses";
	private static final String ORIG_EMAILS = "origEmails";

	private PageSearchCriteria pageSearchCriteria = new PageSearchCriteria();

	public SocialEventRegistrationSearchCriteria withUpdatedBefore(Instant updatedBefore) {
		simpleCriteria.put(UPDATE_BEFORE, updatedBefore.toString());
		return this;
	}

	public SocialEventRegistrationSearchCriteria withUpdatedAfter(Instant updatedAfter) {
		simpleCriteria.put(UPDATE_AFTER, updatedAfter.toString());
		return this;
	}
	
	public SocialEventRegistrationSearchCriteria withCreatedBefore(Instant createdBefore) {
		simpleCriteria.put(CREATED_BEFORE, createdBefore.toString());
		return this;
	}

	public SocialEventRegistrationSearchCriteria withCreatedAfter(Instant createdAfter) {
		simpleCriteria.put(CREATED_AFTER, createdAfter.toString());
		return this;
	}

	public SocialEventRegistrationSearchCriteria withSocialEventHids(String... socialEventHids) {
		arrayCriteria.put(SOCIAL_EVENT_HIDS, socialEventHids);
		return this;
	}

	public SocialEventRegistrationSearchCriteria withNames(String... names) {
		arrayCriteria.put(NAMES, names);
		return this;
	}
	
	public SocialEventRegistrationSearchCriteria withEmails(String... emails) {
		arrayCriteria.put(EMAILS, emails);
		return this;
	}
	
	public SocialEventRegistrationSearchCriteria withOrigEmails(String... origEmails) {
		arrayCriteria.put(ORIG_EMAILS, origEmails);
		return this;
	}

	public SocialEventRegistrationSearchCriteria withStatuses(SocialEventRegistrationStatuses... statuses) {
		if (statuses != null) {
			arrayCriteria.put(STATUSES,
			        Arrays.stream(statuses).map(Object::toString).toArray(String[]::new));
		}
		return this;
	}

	public SocialEventRegistrationSearchCriteria withPage(long page) {
		pageSearchCriteria.withPage(page);
		simpleCriteria.putAll(pageSearchCriteria.getSimpleCriteria());
		return this;
	}

	public SocialEventRegistrationSearchCriteria withSize(long size) {
		pageSearchCriteria.withSize(size);
		simpleCriteria.putAll(pageSearchCriteria.getSimpleCriteria());
		return this;
	}
}
