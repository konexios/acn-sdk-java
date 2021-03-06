package com.arrow.acn.client.api;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.arrow.acs.client.api.ApiConfig;

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