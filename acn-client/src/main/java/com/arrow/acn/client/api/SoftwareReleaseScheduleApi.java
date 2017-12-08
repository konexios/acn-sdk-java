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

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import com.arrow.acn.client.AcnClientException;
import com.arrow.acn.client.model.CreateSoftwareReleaseScheduleModel;
import com.arrow.acn.client.model.SoftwareReleaseScheduleModel;
import com.arrow.acn.client.model.UpdateSoftwareReleaseScheduleModel;
import com.arrow.acn.client.search.SoftwareReleaseScheduleSearchCriteria;
import com.arrow.acs.JsonUtils;
import com.arrow.acs.client.api.ApiConfig;
import com.arrow.acs.client.model.HidModel;
import com.arrow.acs.client.model.PagingResultModel;
import com.fasterxml.jackson.core.type.TypeReference;

public class SoftwareReleaseScheduleApi extends ApiAbstract {

	private static final String SOFTWARE_RELEASE_SCHEDULE_BASE_URL = API_BASE + "/software/releases/schedules";
	private static final String CREATE_URL = SOFTWARE_RELEASE_SCHEDULE_BASE_URL;
	private static final String SPECIFIC_SOFTWARE_RELEASE_URL = SOFTWARE_RELEASE_SCHEDULE_BASE_URL + "/%s";
	private static final String UPDATE_URL = SPECIFIC_SOFTWARE_RELEASE_URL;
	private static final String FIND_BY_HID = SPECIFIC_SOFTWARE_RELEASE_URL;
	private static final String FIND_ALL_BY_URL = SOFTWARE_RELEASE_SCHEDULE_BASE_URL;

	private TypeReference<PagingResultModel<SoftwareReleaseScheduleModel>> softwareReleaseScheduleModelTypeRef;

	SoftwareReleaseScheduleApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	public HidModel create(CreateSoftwareReleaseScheduleModel model) {
		String method = "create";
		try {
			URI uri = buildUri(CREATE_URL);
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
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
			logError(method, e);
			throw new AcnClientException(method, e);
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
			logError(method, e);
			throw new AcnClientException(method, e);
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
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	private synchronized TypeReference<PagingResultModel<SoftwareReleaseScheduleModel>> getSoftwareReleaseScheduleModelTypeRef() {
		return softwareReleaseScheduleModelTypeRef != null ? softwareReleaseScheduleModelTypeRef
		        : (softwareReleaseScheduleModelTypeRef = new TypeReference<PagingResultModel<SoftwareReleaseScheduleModel>>() {
		        });
	}
}
