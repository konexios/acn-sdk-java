package com.arrow.acn.client.api;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.arrow.acs.client.api.ApiConfig;

public class TelemetryApiTest {

	private AcnClient acnClient;

	@Before
	public void init() {
		ApiConfig apiConfig = new ApiConfig().withBaseUrl("http://localhost:8080").withApiKey("apiKey")
				.withSecretkey("secretKey");

		acnClient = new AcnClient(apiConfig);
	}

	@Test
	public void bulkDeleteLastTelemetries() throws Exception {
		final ArrayList<String> lastTelemetryItemsIds = new ArrayList<>();
		lastTelemetryItemsIds.add("lastTelemetryID");
		boolean removeDefinitionTelemetryToo = true;
		acnClient.getTelemetryApi().bulkDeleteLastTelemetries(lastTelemetryItemsIds, "deviceID",
				removeDefinitionTelemetryToo);
	}
}