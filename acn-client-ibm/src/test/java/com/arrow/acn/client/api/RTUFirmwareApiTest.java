package com.arrow.acn.client.api;

import org.junit.Before;
import org.junit.Test;

import com.arrow.acn.client.search.RTUAvailableSearchCriteria;
import com.arrow.acn.client.search.RTURequestSearchCriteria;
import com.arrow.acs.client.api.ApiConfig;

public class RTUFirmwareApiTest {

	private AcnClient acnClient;

	@Before
	public void init() {
		ApiConfig apiConfig = new ApiConfig().withBaseUrl("http://localhost:12001").withApiKey("apiKey")
				.withSecretkey("secretKey");

		acnClient = new AcnClient(apiConfig);
	}

	@Test
	public void findAvailableFirmware() throws Exception {
		RTUAvailableSearchCriteria criteria = new RTUAvailableSearchCriteria();
		criteria.setDeviceTypeHid("deviceHid");
		acnClient.getRTUFirmwareApi().findAvailableFirmware(criteria);
	}

	@Test
	public void findRequestedFirmware() throws Exception {
		RTURequestSearchCriteria criteria = new RTURequestSearchCriteria();
		criteria.setStatus("Requested");
		acnClient.getRTUFirmwareApi().findRequestedFirmware(criteria);
	}

	@Test
	public void requestRTU() throws Exception {
		String softwareReleaseHid = "softwareReleaseHid";
		acnClient.getRTUFirmwareApi().requestRTU(softwareReleaseHid);
	}
}