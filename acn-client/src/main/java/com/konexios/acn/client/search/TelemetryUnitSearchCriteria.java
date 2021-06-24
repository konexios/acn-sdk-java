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

public class TelemetryUnitSearchCriteria extends SearchCriteria {

	private static final String SYSTEM_NAME = "systemName";
	private static final String NAME = "name";
	private static final String ENABLED = "enabled";
	private static final String SORT_FIELD = "sortField";
	private static final String SORT_DIRECTION = "sortDirection";

	public TelemetryUnitSearchCriteria withSystemName(String systemName) {
		simpleCriteria.put(SYSTEM_NAME, systemName);
		return this;
	}

	public TelemetryUnitSearchCriteria withName(String name) {
		simpleCriteria.put(NAME, name);
		return this;
	}

	public TelemetryUnitSearchCriteria withEnabled(boolean enabled) {
		simpleCriteria.put(ENABLED, String.valueOf(enabled));
		return this;
	}

	public TelemetryUnitSearchCriteria withSortField(String sortField) {
		simpleCriteria.put(SORT_FIELD, sortField);
		return this;
	}

	public TelemetryUnitSearchCriteria withSortDirection(String sortDirection) {
		simpleCriteria.put(SORT_DIRECTION, sortDirection);
		return this;
	}
}
