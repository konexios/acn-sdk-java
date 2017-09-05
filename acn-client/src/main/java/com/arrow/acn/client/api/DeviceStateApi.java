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
import java.util.Collections;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import com.arrow.acn.client.AcnClientException;
import com.arrow.acn.client.model.DeviceStateModel;
import com.arrow.acn.client.model.DeviceStateRequestModel;
import com.arrow.acn.client.model.DeviceStateUpdateModel;
import com.arrow.acs.JsonUtils;
import com.arrow.acs.client.api.ApiConfig;
import com.arrow.acs.client.model.HidModel;
import com.arrow.acs.client.model.StatusModel;

public class DeviceStateApi extends ApiAbstract {
	private static final String DEVICES_STATE_BASE_URL = API_BASE + "/devices/%s/state";
	private static final String FIND_URL = DEVICES_STATE_BASE_URL;
	private static final String CREATE_STATE_REQUEST_URL = DEVICES_STATE_BASE_URL + "/request";
	private static final String CREATE_STATE_UPDATE_URL = DEVICES_STATE_BASE_URL + "/update";
	private static final String TRANS_BASE_URL = DEVICES_STATE_BASE_URL + "/trans/%s/";
	private static final String TRANS_RECEIVED_URL = TRANS_BASE_URL + "received";
	private static final String TRANS_SUCCEEDED_URL = TRANS_BASE_URL + "succeeded";
	private static final String TRANS_FAILED_URL = TRANS_BASE_URL + "failed";

	DeviceStateApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	public DeviceStateModel find(String deviceHid) {
		String method = "find";
		try {
			URI uri = buildUri(String.format(FIND_URL, deviceHid));
			DeviceStateModel result = execute(new HttpGet(uri), DeviceStateModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public HidModel createStateRequest(String deviceHid, DeviceStateRequestModel model) {
		String method = "createStateRequest";
		try {
			URI uri = buildUri(String.format(CREATE_STATE_REQUEST_URL, deviceHid));
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public HidModel createStateUpdate(String deviceHid, DeviceStateUpdateModel model) {
		String method = "createStateUpdate";
		try {
			URI uri = buildUri(String.format(CREATE_STATE_UPDATE_URL, deviceHid));
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public StatusModel transReceived(String deviceHid, String transHid) {
		String method = "transReceived";
		try {
			URI uri = buildUri(String.format(TRANS_RECEIVED_URL, deviceHid, transHid));
			StatusModel result = execute(new HttpPut(uri), StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public StatusModel transSucceeded(String deviceHid, String transHid) {
		String method = "transSucceeded";
		try {
			URI uri = buildUri(String.format(TRANS_SUCCEEDED_URL, deviceHid, transHid));
			StatusModel result = execute(new HttpPut(uri), StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public StatusModel transFailed(String deviceHid, String transHid, String error) {
		String method = "transError";
		try {
			URI uri = buildUri(String.format(TRANS_FAILED_URL, deviceHid, transHid));
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
