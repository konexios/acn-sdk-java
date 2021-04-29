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

import com.konexios.acs.client.search.SearchCriteria;

public class SoftwareReleaseScheduleTransactionsListSearchCriteria extends SearchCriteria {

	private static final String SORT_FIELD = "sortField";
	private static final String SORT_DIRECTION = "sortDirection";

	private PageSearchCriteria pageSearchCriteria = new PageSearchCriteria();

	public SoftwareReleaseScheduleTransactionsListSearchCriteria withSortField(String sortField) {
		simpleCriteria.put(SORT_FIELD, sortField);
		return this;
	}

	public SoftwareReleaseScheduleTransactionsListSearchCriteria withSortDirection(String sortDirection) {
		simpleCriteria.put(SORT_DIRECTION, sortDirection);
		return this;
	}

	public SoftwareReleaseScheduleTransactionsListSearchCriteria withPage(long page) {
		pageSearchCriteria.withPage(page);
		simpleCriteria.putAll(pageSearchCriteria.getSimpleCriteria());
		return this;
	}

	public SoftwareReleaseScheduleTransactionsListSearchCriteria withSize(long size) {
		pageSearchCriteria.withSize(size);
		simpleCriteria.putAll(pageSearchCriteria.getSimpleCriteria());
		return this;
	}
}
