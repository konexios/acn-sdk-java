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

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import com.arrow.acn.client.AcnClientException;
import com.arrow.acn.client.model.CreateSocialEventRegistrationModel;
import com.arrow.acn.client.model.RegisterSocialEventRegistrationModel;
import com.arrow.acn.client.model.SocialEventRegistrationModel;
import com.arrow.acn.client.search.SocialEventRegistrationRegisterCriteria;
import com.arrow.acn.client.search.SocialEventRegistrationSearchCriteria;
import com.arrow.acn.client.search.SocialEventRegistrationVerifyCriteria;
import com.arrow.acs.JsonUtils;
import com.arrow.acs.client.api.ApiConfig;
import com.arrow.acs.client.model.HidModel;
import com.arrow.acs.client.model.PagingResultModel;
import com.arrow.acs.client.model.StatusModel;
import com.fasterxml.jackson.core.type.TypeReference;

public class SocialEventRegistrationApi extends ApiAbstract {

	private static final String SOCIAL_EVENT_REGISTRATIONS_BASE_URL = API_BASE + "/social/event/registrations";
	private static final String CREATE_URL = SOCIAL_EVENT_REGISTRATIONS_BASE_URL;
	private static final String SPECIFIC_SOCIAL_EVENT_URL = SOCIAL_EVENT_REGISTRATIONS_BASE_URL + "/%s";
	private static final String UPDATE_URL = SPECIFIC_SOCIAL_EVENT_URL;
	private static final String FIND_BY_HID = SPECIFIC_SOCIAL_EVENT_URL;
	private static final String DELETE_URL = SPECIFIC_SOCIAL_EVENT_URL;
	private static final String FIND_ALL_BY_URL = SOCIAL_EVENT_REGISTRATIONS_BASE_URL;
	private static final String REGISTER_URL = SOCIAL_EVENT_REGISTRATIONS_BASE_URL + "/register";
	private static final String REGISTER_BY_HID = REGISTER_URL + "/%s";
	private static final String VERIFY_URL = SOCIAL_EVENT_REGISTRATIONS_BASE_URL + "/verify/{hid}";

	private static final TypeReference<PagingResultModel<SocialEventRegistrationModel>> SOCIAL_EVENT_REGISTRATION_MODEL_TYPE_REF = new TypeReference<PagingResultModel<SocialEventRegistrationModel>>() {
	};

	SocialEventRegistrationApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	public HidModel create(CreateSocialEventRegistrationModel model) {
		String method = "create";
		try {
			URI uri = buildUri(CREATE_URL);
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			//log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public HidModel update(String hid, RegisterSocialEventRegistrationModel model) {
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

	public SocialEventRegistrationModel findByHid(String hid) {
		String method = "findByHid";
		try {
			URI uri = buildUri(String.format(FIND_BY_HID, hid));
			SocialEventRegistrationModel result = execute(new HttpGet(uri), SocialEventRegistrationModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public PagingResultModel<SocialEventRegistrationModel> findAllBy(SocialEventRegistrationSearchCriteria criteria) {
		String method = "findAllBy";
		try {
			URI uri = buildUri(FIND_ALL_BY_URL, criteria);
			PagingResultModel<SocialEventRegistrationModel> result = execute(new HttpGet(uri),
					SOCIAL_EVENT_REGISTRATION_MODEL_TYPE_REF);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}
	
	public StatusModel delete(String hid) {
		String method = "delete";
		try {
			URI uri = buildUri(String.format(DELETE_URL, hid));
			StatusModel result = execute(new HttpDelete(uri), StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}
	
	public HidModel register(String hid, RegisterSocialEventRegistrationModel model) {
		String method = "register";
		try {
			URI uri = buildUri(String.format(REGISTER_BY_HID, hid));
			HidModel result = execute(new HttpPut(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}
	
	public HidModel register(SocialEventRegistrationRegisterCriteria criteria, RegisterSocialEventRegistrationModel model) {
		String method = "register";
		try {
			URI uri = buildUri(REGISTER_URL, criteria);
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}
	
	public HidModel verify(String hid, SocialEventRegistrationVerifyCriteria criteria) {
		String method = "verify";
		try {
			URI uri = buildUri(VERIFY_URL.replace("{hid}", hid), criteria);
			HidModel result = execute(new HttpPost(uri), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}
}
