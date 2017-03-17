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

public class SortedSearchCriteria extends PageSearchCriteria {
	private static final String CREATED_DATE_FROM = "createdDateFrom";
	private static final String CREATED_DATE_TO = "createdDateTo";
	private static final String SORT_FIELD = "sortField";
	private static final String SORT_DIRECTION = "sortDirection";

	SortedSearchCriteria withCreatedDateFrom(String createdDateFrom) {
		simpleCriteria.put(CREATED_DATE_FROM, createdDateFrom);
		return this;
	}

	SortedSearchCriteria withCreatedDateTo(String createdDateTo) {
		simpleCriteria.put(CREATED_DATE_TO, createdDateTo);
		return this;
	}

	SortedSearchCriteria withSortField(String sortField) {
		simpleCriteria.put(SORT_FIELD, sortField);
		return this;
	}

	SortedSearchCriteria withSortDirection(String sortDirection) {
		simpleCriteria.put(SORT_DIRECTION, sortDirection);
		return this;
	}
}
