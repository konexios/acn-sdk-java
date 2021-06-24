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

import org.apache.http.client.methods.HttpGet;

import com.fasterxml.jackson.core.type.TypeReference;

import com.konexios.acn.client.model.TelemetryUnitModel;
import com.konexios.acn.client.search.TelemetryUnitSearchCriteria;
import com.konexios.acs.client.api.ApiConfig;
import com.konexios.acs.client.api.MqttHttpChannel;
import com.konexios.acs.client.model.ListResultModel;

public class TelemetryUnitApi extends ApiAbstract {

	private static final String TELEMETRY_UNIT_BASE_URL = API_BASE + "/telemetries/units";

	private TypeReference<ListResultModel<TelemetryUnitModel>> telemetryUnitModelTypeRef;

	TelemetryUnitApi(ApiConfig apiConfig, MqttHttpChannel mqttHttpChannel) {
		super(apiConfig, mqttHttpChannel);
	}

	public ListResultModel<TelemetryUnitModel> findAllBy(TelemetryUnitSearchCriteria criteria) {
		String method = "findAllBy";
		try {
			URI uri = buildUri(TELEMETRY_UNIT_BASE_URL, criteria);
			ListResultModel<TelemetryUnitModel> result = execute(new HttpGet(uri), getTelemetryUnitModelTypeRef());
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	private synchronized TypeReference<ListResultModel<TelemetryUnitModel>> getTelemetryUnitModelTypeRef() {
		return telemetryUnitModelTypeRef != null ? telemetryUnitModelTypeRef
				: (telemetryUnitModelTypeRef = new TypeReference<ListResultModel<TelemetryUnitModel>>() {
				});
	}
}
