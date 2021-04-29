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

public class RTUAvailableSearchCriteria extends SearchCriteria {

	private static final String DEVICE_TYPE_HID = "deviceTypeHid";

	public RTUAvailableSearchCriteria withDeviceTypeHid(final String deviceTypeHid) {
		simpleCriteria.put(DEVICE_TYPE_HID, String.valueOf(deviceTypeHid));
		return this;
	}

	public void setDeviceTypeHid(final String deviceTypeHid) {
		simpleCriteria.put(DEVICE_TYPE_HID, String.valueOf(deviceTypeHid));
	}
}
