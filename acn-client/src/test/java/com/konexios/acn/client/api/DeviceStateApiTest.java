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
package com.konexios.acn.client.api;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.konexios.acs.client.api.ApiConfig;

public class DeviceStateApiTest {

	private AcnClient acnClient;

	@Before
	public void init() {
		ApiConfig apiConfig = new ApiConfig().withBaseUrl("http://localhost:8080").withApiKey("apiKey")
				.withSecretkey("secretKey");

		acnClient = new AcnClient(apiConfig);
	}

	@Test
	public void deleteDeviceStates() throws Exception {
		final ArrayList<String> deviceStatesNames = new ArrayList<>();
		deviceStatesNames.add("testStateName");
		boolean removeStatesDefinitionToo = true;
		acnClient.getDeviceStateApi().deleteDeviceStates(deviceStatesNames, "deviceHID", removeStatesDefinitionToo);
	}
}
