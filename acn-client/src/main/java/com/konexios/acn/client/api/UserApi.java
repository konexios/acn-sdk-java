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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import com.konexios.acn.client.model.AuthRequestModel;
import com.konexios.acn.client.model.AuthResponseModel;
import com.konexios.acn.client.model.ChangePasswordRequestModel;
import com.konexios.acn.client.model.ChangePasswordResponseModel;
import com.konexios.acn.client.model.CreateUserRequestModel;
import com.konexios.acn.client.model.CreateUserResponseModel;
import com.konexios.acn.client.model.FindUserRequestModel;
import com.konexios.acn.client.model.FindUserResponseModel;
import com.konexios.acn.client.model.ResetPasswordRequestModel;
import com.konexios.acn.client.model.ResetPasswordResponseModel;
import com.konexios.acn.client.model.UpdateUserRequestModel;
import com.konexios.acn.client.model.UpdateUserResponseModel;
import com.konexios.acs.JsonUtils;
import com.konexios.acs.client.api.ApiConfig;
import com.konexios.acs.client.api.MqttHttpChannel;

public class UserApi extends ApiAbstract {
	private static final String USER_BASE_URL = API_BASE + "/users";
	private static final String CREATE_URL = USER_BASE_URL;
	private static final String UPDATE_URL = USER_BASE_URL + "/{hid}";
	private static final String RESET_PASSWORD_URL = USER_BASE_URL + "/{hid}/reset-password";
	private static final String CHANGE_PASSWORD_URL = USER_BASE_URL + "/{hid}/change-password";
	private static final String AUTH_URL = USER_BASE_URL + "/auth";
	private static final String FIND_URL = USER_BASE_URL + "/find";

	private static final Pattern PATTERN = Pattern.compile("{hid}", Pattern.LITERAL);

	UserApi(ApiConfig apiConfig, MqttHttpChannel mqttHttpChannel) {
		super(apiConfig, mqttHttpChannel);
	}

	public AuthResponseModel auth(AuthRequestModel model) {
		String method = "auth";
		try {
			URI uri = buildUri(AUTH_URL);
			AuthResponseModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), AuthResponseModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public CreateUserResponseModel create(CreateUserRequestModel model) {
		String method = "create";
		try {
			URI uri = buildUri(CREATE_URL);
			CreateUserResponseModel result = execute(new HttpPost(uri), JsonUtils.toJson(model),
					CreateUserResponseModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public UpdateUserResponseModel update(String hid, UpdateUserRequestModel model) {
		String method = "update";
		try {
			URI uri = buildUri(PATTERN.matcher(UPDATE_URL).replaceAll(Matcher.quoteReplacement(hid)));
			UpdateUserResponseModel result = execute(new HttpPut(uri), JsonUtils.toJson(model),
					UpdateUserResponseModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public ResetPasswordResponseModel resetPassword(String hid, ResetPasswordRequestModel model) {
		String method = "resetPassword";
		try {
			URI uri = buildUri(PATTERN.matcher(RESET_PASSWORD_URL).replaceAll(Matcher.quoteReplacement(hid)));
			ResetPasswordResponseModel result = execute(new HttpPost(uri), JsonUtils.toJson(model),
					ResetPasswordResponseModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public ChangePasswordResponseModel changePassword(String hid, ChangePasswordRequestModel model) {
		String method = "changePassword";
		try {
			URI uri = buildUri(PATTERN.matcher(CHANGE_PASSWORD_URL).replaceAll(Matcher.quoteReplacement(hid)));
			ChangePasswordResponseModel result = execute(new HttpPost(uri), JsonUtils.toJson(model),
					ChangePasswordResponseModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public FindUserResponseModel find(FindUserRequestModel model) {
		String method = "find";
		try {
			URI uri = buildUri(FIND_URL);
			FindUserResponseModel result = execute(new HttpPost(uri), JsonUtils.toJson(model),
					FindUserResponseModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}
}
