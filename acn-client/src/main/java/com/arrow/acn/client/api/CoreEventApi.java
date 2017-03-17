/*******************************************************************************
 * Copyright (c) 2017 Arrow Electronics, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License 2.0
 * which accompanies this distribution, and is available at
 * http://apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *     Arrow Electronics, Inc.
 *******************************************************************************/
package com.arrow.acn.client.api;

import java.net.URI;

import org.apache.http.client.methods.HttpPut;

import com.arrow.acn.client.AcnClientException;
import com.arrow.acs.client.api.ApiConfig;
import com.arrow.acs.client.model.StatusModel;

public class CoreEventApi extends ApiAbstract {
	private static final String CORE_EVENT_BASE_URL = API_BASE + "/core/events";
	private static final String PUT_FAILED_URL = CORE_EVENT_BASE_URL + "/{hid}/failed";
	private static final String PUT_RECEIVED_URL = CORE_EVENT_BASE_URL + "/{hid}/received";
	private static final String PUT_SUCCEEDED_URL = CORE_EVENT_BASE_URL + "/{hid}/succeeded";

	CoreEventApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	// TODO add body
	public StatusModel putFailed(String hid) {
		String method = "putFailed";
		try {
			URI uri = buildUri(PUT_FAILED_URL.replace("{hid}", hid));
			StatusModel result = execute(new HttpPut(uri), StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public StatusModel putReceived(String hid) {
		String method = "putReceived";
		try {
			URI uri = buildUri(PUT_RECEIVED_URL.replace("{hid}", hid));
			StatusModel result = execute(new HttpPut(uri), StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	// TODO add body
	public StatusModel putSucceeded(String hid) {
		String method = "putSucceeded";
		try {
			URI uri = buildUri(PUT_SUCCEEDED_URL.replace("{hid}", hid));
			StatusModel result = execute(new HttpPut(uri), StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}
}
