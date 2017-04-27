package com.arrow.acn.client.api;

import com.arrow.acs.client.api.ApiConfig;

public class SoftwareReleaseApi extends ApiAbstract {
	private static final String SOFTWARE_RELEASE_BASE_URL = API_BASE + "/software/releases/";
	private static final String RECEIVED_URL = SOFTWARE_RELEASE_BASE_URL + "received";
	private static final String SUCCEEDED_URL = SOFTWARE_RELEASE_BASE_URL + "succeeded";
	private static final String FAILED_URL = SOFTWARE_RELEASE_BASE_URL + "failed";

	public SoftwareReleaseApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	public void received() {
		// TODO
	}

	public void succeeded() {
		// TODO
	}

	public void failed() {
		// TODO
	}
}
