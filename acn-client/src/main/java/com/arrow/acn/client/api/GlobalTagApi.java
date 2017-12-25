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
import com.arrow.acn.client.model.GlobalTagModel;
import com.arrow.acn.client.model.GlobalTagRegistrationModel;
import com.arrow.acn.client.search.GlobalTagSearchCriteria;
import com.arrow.acs.JsonUtils;
import com.arrow.acs.client.api.ApiConfig;
import com.arrow.acs.client.model.HidModel;
import com.arrow.acs.client.model.ListResultModel;
import com.arrow.acs.client.model.PagingResultModel;
import com.fasterxml.jackson.core.type.TypeReference;

public class GlobalTagApi extends ApiAbstract {

	private static final String GLOBAL_TAG_BASE_URL = API_BASE + "/global-tags";
	private static final String SPECIFIC_GLOBAL_TAG_URL = GLOBAL_TAG_BASE_URL + "/{hid}";
	private static final String FIND_LIST_URL = GLOBAL_TAG_BASE_URL;
	private static final String FIND_PAGE_URL = GLOBAL_TAG_BASE_URL + "/pages";
	private static final String FIND_BY_HID_URL = SPECIFIC_GLOBAL_TAG_URL;
	private static final String CREATE_GLOBAL_TAG_URL = GLOBAL_TAG_BASE_URL;
	private static final String UPDATE_GLOBAL_TAG_URL = SPECIFIC_GLOBAL_TAG_URL;

	private static final TypeReference<PagingResultModel<GlobalTagModel>> GLOBAL_TAG_MODEL_PAGE_TYPE_REF = new TypeReference<PagingResultModel<GlobalTagModel>>() {
	};
	private static final TypeReference<ListResultModel<GlobalTagModel>> GLOBAL_TAG_MODEL_LIST_TYPE_REF = new TypeReference<ListResultModel<GlobalTagModel>>() {
	};

	GlobalTagApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	public ListResultModel<GlobalTagModel> findAllBy(GlobalTagSearchCriteria criteria) {
		String method = "findAllBy";
		try {
			URI uri = buildUri(FIND_LIST_URL, criteria);
			ListResultModel<GlobalTagModel> result = execute(new HttpGet(uri), GLOBAL_TAG_MODEL_LIST_TYPE_REF);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public PagingResultModel<GlobalTagModel> findPageBy(GlobalTagSearchCriteria criteria) {
		String method = "findPageBy";
		try {
			URI uri = buildUri(FIND_PAGE_URL, criteria);
			PagingResultModel<GlobalTagModel> result = execute(new HttpGet(uri), GLOBAL_TAG_MODEL_PAGE_TYPE_REF);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public GlobalTagModel findByHid(String hid) {
		String method = "findByHid";
		try {
			URI uri = buildUri(FIND_BY_HID_URL.replace("{hid}", hid));
			GlobalTagModel result = execute(new HttpGet(uri), GlobalTagModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public HidModel createGlobalTag(GlobalTagRegistrationModel model) {
		String method = "createGlobalTag";
		try {
			URI uri = buildUri(CREATE_GLOBAL_TAG_URL);
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public HidModel updateGlobalTag(String hid, GlobalTagRegistrationModel model) {
		String method = "updateGlobalTag";
		try {
			URI uri = buildUri(UPDATE_GLOBAL_TAG_URL.replace("{hid}", hid));
			HidModel result = execute(new HttpPut(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

}
