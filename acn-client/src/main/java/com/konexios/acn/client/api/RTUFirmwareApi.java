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
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;

import com.fasterxml.jackson.core.type.TypeReference;

import com.konexios.acn.client.model.RTUFirmwareModels.RTUFirmwareModel;
import com.konexios.acn.client.model.RTUFirmwareModels.RTURequestedFirmwareModel;
import com.konexios.acn.client.search.RTUAvailableSearchCriteria;
import com.konexios.acn.client.search.RTURequestSearchCriteria;
import com.konexios.acs.client.api.ApiConfig;
import com.konexios.acs.client.api.MqttHttpChannel;
import com.konexios.acs.client.model.PagingResultModel;
import com.konexios.acs.client.model.StatusModel;

public class RTUFirmwareApi extends ApiAbstract {

	private static final String RTU_BASE_URL = API_BASE + "/rtu";
	private static final String REQUEST_URL = RTU_BASE_URL + "/request/{softwareReleaseHid}";
	private static final String FIND_REQUESTED = RTU_BASE_URL + "/find";
	private static final String FIND_AVAILABLE = FIND_REQUESTED + "/available";

	private TypeReference<List<RTUFirmwareModel>> rtuFirmwareModelTypeRef;
	private TypeReference<PagingResultModel<RTURequestedFirmwareModel>> rtuRequestFirmwareModelTypeRef;

	RTUFirmwareApi(ApiConfig apiConfig, MqttHttpChannel mqttHttpChannel) {
		super(apiConfig, mqttHttpChannel);
	}

	public StatusModel requestRTU(String softwareReleaseHid) {
		String method = "requestRTU";

		try {
			URI uri = buildUri(REQUEST_URL.replace("{softwareReleaseHid}", softwareReleaseHid));
			StatusModel result = execute(new HttpPut(uri), StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public List<RTUFirmwareModel> findAvailableFirmware(RTUAvailableSearchCriteria criteria) {
		try {
			URI uri = buildUri(FIND_AVAILABLE, criteria);
			List<RTUFirmwareModel> result = execute(new HttpGet(uri), getRtuFirmwareModelTypeRef());
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public PagingResultModel<RTURequestedFirmwareModel> findRequestedFirmware(RTURequestSearchCriteria criteria) {
		try {
			URI uri = buildUri(FIND_REQUESTED, criteria);
			PagingResultModel<RTURequestedFirmwareModel> result = execute(new HttpGet(uri),
					getRtuRequestedFirmwareModel());
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	private synchronized TypeReference<List<RTUFirmwareModel>> getRtuFirmwareModelTypeRef() {
		return rtuFirmwareModelTypeRef != null ? rtuFirmwareModelTypeRef
				: (rtuFirmwareModelTypeRef = new TypeReference<List<RTUFirmwareModel>>() {
				});
	}

	private synchronized TypeReference<PagingResultModel<RTURequestedFirmwareModel>> getRtuRequestedFirmwareModel() {
		return rtuRequestFirmwareModelTypeRef != null ? rtuRequestFirmwareModelTypeRef
				: (rtuRequestFirmwareModelTypeRef = new TypeReference<PagingResultModel<RTURequestedFirmwareModel>>() {
				});
	}
}
