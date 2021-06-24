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

public class TelemetrySearchCriteria extends SearchCriteria {
	private static final String TELEMETRY_NAMES = "telemetryNames";
	private PageSearchCriteria pageSearchCriteria = new PageSearchCriteria();
	private TimestampSearchCriteria timestampSearchCriteria = new TimestampSearchCriteria();

	public TelemetrySearchCriteria withTelemetryNames(String telemetryNames) {
		simpleCriteria.put(TELEMETRY_NAMES, telemetryNames);
		return this;
	}

	public TelemetrySearchCriteria withPage(int page) {
		pageSearchCriteria.withPage(page);
		simpleCriteria.putAll(pageSearchCriteria.getSimpleCriteria());
		return this;
	}

	public TelemetrySearchCriteria withSize(int size) {
		pageSearchCriteria.withSize(size);
		simpleCriteria.putAll(pageSearchCriteria.getSimpleCriteria());
		return this;
	}

	public TelemetrySearchCriteria withFromTimestamp(String fromTimestamp) {
		timestampSearchCriteria.withFromTimestamp(fromTimestamp);
		simpleCriteria.putAll(timestampSearchCriteria.getSimpleCriteria());
		return this;
	}

	public TelemetrySearchCriteria withToTimestamp(String toTimestamp) {
		timestampSearchCriteria.withToTimestamp(toTimestamp);
		simpleCriteria.putAll(timestampSearchCriteria.getSimpleCriteria());
		return this;
	}
}
