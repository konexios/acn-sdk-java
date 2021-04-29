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

public class SortedSearchCriteria extends PageSearchCriteria {
	private static final String CREATED_DATE_FROM = "createdDateFrom";
	private static final String CREATED_DATE_TO = "createdDateTo";
	private static final String SORT_FIELD = "sortField";
	private static final String SORT_DIRECTION = "sortDirection";

	public SortedSearchCriteria withCreatedDateFrom(String createdDateFrom) {
		simpleCriteria.put(CREATED_DATE_FROM, createdDateFrom);
		return this;
	}

	public SortedSearchCriteria withCreatedDateTo(String createdDateTo) {
		simpleCriteria.put(CREATED_DATE_TO, createdDateTo);
		return this;
	}

	public SortedSearchCriteria withSortField(String sortField) {
		simpleCriteria.put(SORT_FIELD, sortField);
		return this;
	}

	public SortedSearchCriteria withSortDirection(String sortDirection) {
		simpleCriteria.put(SORT_DIRECTION, sortDirection);
		return this;
	}
}
