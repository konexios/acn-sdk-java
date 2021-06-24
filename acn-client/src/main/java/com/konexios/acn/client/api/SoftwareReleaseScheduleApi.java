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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import com.fasterxml.jackson.core.type.TypeReference;

import com.konexios.acn.client.model.CreateSoftwareReleaseScheduleModel;
import com.konexios.acn.client.model.SoftwareReleaseScheduleAutomationModel;
import com.konexios.acn.client.model.SoftwareReleaseScheduleModel;
import com.konexios.acn.client.model.SoftwareReleaseTransModel;
import com.konexios.acn.client.model.UpdateSoftwareReleaseScheduleModel;
import com.konexios.acn.client.search.SoftwareReleaseScheduleSearchCriteria;
import com.konexios.acn.client.search.SoftwareReleaseScheduleTransactionsListSearchCriteria;
import com.konexios.acs.JsonUtils;
import com.konexios.acs.client.api.ApiConfig;
import com.konexios.acs.client.api.MqttHttpChannel;
import com.konexios.acs.client.model.HidModel;
import com.konexios.acs.client.model.PagingResultModel;

public class SoftwareReleaseScheduleApi extends ApiAbstract {

	private static final String SOFTWARE_RELEASE_SCHEDULE_BASE_URL = API_BASE + "/software/releases/schedules";
	private static final String CREATE_URL = SOFTWARE_RELEASE_SCHEDULE_BASE_URL;
	private static final String SPECIFIC_SOFTWARE_RELEASE_URL = SOFTWARE_RELEASE_SCHEDULE_BASE_URL + "/%s";
	private static final String UPDATE_URL = SPECIFIC_SOFTWARE_RELEASE_URL;
	private static final String FIND_BY_HID = SPECIFIC_SOFTWARE_RELEASE_URL;
	private static final String LIST_ALL_TRANSACTIONS = SPECIFIC_SOFTWARE_RELEASE_URL + "/transactions";
	private static final String FIND_ALL_BY_URL = SOFTWARE_RELEASE_SCHEDULE_BASE_URL;
	private static final String CREATE_AND_START_URL = SOFTWARE_RELEASE_SCHEDULE_BASE_URL + "/start";

	private TypeReference<PagingResultModel<SoftwareReleaseScheduleModel>> softwareReleaseScheduleModelTypeRef;
	private TypeReference<PagingResultModel<SoftwareReleaseTransModel>> softwareReleaseTransModelTypeRef;

	SoftwareReleaseScheduleApi(ApiConfig apiConfig, MqttHttpChannel mqttHttpChannel) {
		super(apiConfig, mqttHttpChannel);
	}

	public HidModel create(CreateSoftwareReleaseScheduleModel model) {
		String method = "create";
		try {
			URI uri = buildUri(CREATE_URL);
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public HidModel createAndStart(SoftwareReleaseScheduleAutomationModel model) {
		String method = "createAndStart";
		try {
			URI uri = buildUri(CREATE_AND_START_URL);
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public HidModel update(String hid, UpdateSoftwareReleaseScheduleModel model) {
		String method = "update";
		try {
			URI uri = buildUri(String.format(UPDATE_URL, hid));
			HidModel result = execute(new HttpPut(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public SoftwareReleaseScheduleModel findByHid(String hid) {
		String method = "findByHid";
		try {
			URI uri = buildUri(String.format(FIND_BY_HID, hid));
			SoftwareReleaseScheduleModel result = execute(new HttpGet(uri), SoftwareReleaseScheduleModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public PagingResultModel<SoftwareReleaseScheduleModel> findAllBy(SoftwareReleaseScheduleSearchCriteria criteria) {
		String method = "findAllBy";
		try {
			URI uri = buildUri(FIND_ALL_BY_URL, criteria);
			PagingResultModel<SoftwareReleaseScheduleModel> result = execute(new HttpGet(uri),
					getSoftwareReleaseScheduleModelTypeRef());
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public PagingResultModel<SoftwareReleaseTransModel> listTransactions(String hid,
			SoftwareReleaseScheduleTransactionsListSearchCriteria criteria) {
		String method = "listTransactions";
		try {
			URI uri = buildUri(String.format(LIST_ALL_TRANSACTIONS, hid), criteria);
			PagingResultModel<SoftwareReleaseTransModel> result = execute(new HttpGet(uri),
					getSoftwareReleaseTransModelTypeRef());
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	private synchronized TypeReference<PagingResultModel<SoftwareReleaseScheduleModel>> getSoftwareReleaseScheduleModelTypeRef() {
		return softwareReleaseScheduleModelTypeRef != null ? softwareReleaseScheduleModelTypeRef
				: (softwareReleaseScheduleModelTypeRef = new TypeReference<PagingResultModel<SoftwareReleaseScheduleModel>>() {
				});
	}

	private synchronized TypeReference<PagingResultModel<SoftwareReleaseTransModel>> getSoftwareReleaseTransModelTypeRef() {
		return softwareReleaseTransModelTypeRef != null ? softwareReleaseTransModelTypeRef
				: (softwareReleaseTransModelTypeRef = new TypeReference<PagingResultModel<SoftwareReleaseTransModel>>() {
				});
	}
}
