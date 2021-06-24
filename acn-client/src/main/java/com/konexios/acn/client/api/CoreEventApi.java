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

import java.net.URI;
import java.util.Collections;
import java.util.Map;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;

import com.konexios.acs.JsonUtils;
import com.konexios.acs.client.api.ApiConfig;
import com.konexios.acs.client.api.MqttHttpChannel;
import com.konexios.acs.client.model.EventModel;
import com.konexios.acs.client.model.StatusModel;

public class CoreEventApi extends ApiAbstract {
	private static final String CORE_EVENT_BASE_URL = "/api/v1/core/events";
	private static final String PUT_FAILED_URL = CORE_EVENT_BASE_URL + "/{hid}/failed";
	private static final String PUT_RECEIVED_URL = CORE_EVENT_BASE_URL + "/{hid}/received";
	private static final String PUT_SUCCEEDED_URL = CORE_EVENT_BASE_URL + "/{hid}/succeeded";
	private static final String FIND_BY_HID = CORE_EVENT_BASE_URL + "/{hid}";

	CoreEventApi(ApiConfig apiConfig, MqttHttpChannel mqttHttpChannel) {
		super(apiConfig, mqttHttpChannel);
	}

	public EventModel findByHid(String hid) {
		String method = "findByHid";
		try {
			URI uri = buildUri(FIND_BY_HID.replace("{hid}", hid));
			EventModel result = execute(new HttpGet(uri), EventModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public StatusModel putFailed(String hid, String error) {
		String method = "putFailed";
		try {
			URI uri = buildUri(PUT_FAILED_URL.replace("{hid}", hid));
			StatusModel result = execute(new HttpPut(uri), JsonUtils.toJson(Collections.singletonMap("error", error)),
					StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
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
			throw handleException(e);
		}
	}

	public StatusModel putSucceeded(String hid) {
		return putSucceeded(hid, Collections.emptyMap());
	}

	public StatusModel putSucceeded(String hid, Map<String, String> parameters) {
		String method = "putSucceeded";
		try {
			URI uri = buildUri(PUT_SUCCEEDED_URL.replace("{hid}", hid));
			StatusModel result = execute(new HttpPut(uri), JsonUtils.toJson(parameters), StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}
}
