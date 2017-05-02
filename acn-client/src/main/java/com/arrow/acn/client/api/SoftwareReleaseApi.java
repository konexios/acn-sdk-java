package com.arrow.acn.client.api;

import java.net.URI;
import java.util.Collections;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpPut;

import com.arrow.acn.client.AcnClientException;
import com.arrow.acs.JsonUtils;
import com.arrow.acs.client.api.ApiConfig;
import com.arrow.acs.client.model.StatusModel;

public class SoftwareReleaseApi extends ApiAbstract {
	private static final String SOFTWARE_RELEASE_BASE_URL = API_BASE + "/software/releases/";
	private static final String RECEIVED_URL = SOFTWARE_RELEASE_BASE_URL + "%s/received";
	private static final String SUCCEEDED_URL = SOFTWARE_RELEASE_BASE_URL + "%s/succeeded";
	private static final String FAILED_URL = SOFTWARE_RELEASE_BASE_URL + "%s/failed";

	public SoftwareReleaseApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	public StatusModel received(String hid) {
		String method = "received";
		try {
			URI uri = buildUri(String.format(RECEIVED_URL, hid));
			StatusModel result = execute(new HttpPut(uri), StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public StatusModel succeeded(String hid) {
		String method = "succeeded";
		try {
			URI uri = buildUri(String.format(SUCCEEDED_URL, hid));
			StatusModel result = execute(new HttpPut(uri), StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public StatusModel failed(String hid, String error) {
		String method = "failed";
		try {
			URI uri = buildUri(String.format(FAILED_URL, hid));
			StatusModel result = execute(new HttpPut(uri),
			        JsonUtils.toJson(Collections.singletonMap("error", StringUtils.trimToEmpty(error))),
			        StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}
}
