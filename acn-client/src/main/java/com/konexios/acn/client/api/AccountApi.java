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

import org.apache.http.client.methods.HttpPost;

import com.konexios.acn.client.AcnClientException;
import com.konexios.acn.client.model.AccountRegistrationModel;
import com.konexios.acn.client.model.AccountRegistrationOK;
import com.konexios.acs.JsonUtils;
import com.konexios.acs.client.api.ApiConfig;
import com.konexios.acs.client.api.MqttHttpChannel;

public class AccountApi extends ApiAbstract {
	private static final String ACCOUNT_BASE_URL = API_BASE + "/accounts";
	private static final String CREATE_OR_LOGIN_URL = ACCOUNT_BASE_URL;

	AccountApi(ApiConfig apiConfig, MqttHttpChannel mqttHttpChannel) {
		super(apiConfig, mqttHttpChannel);
	}

	/**
	 * Sends POST request to create new account or login the existing one based on
	 * {@code model} parameter
	 *
	 * @param model {@link AccountRegistrationModel}
	 *
	 * @return {@link AccountRegistrationOK}
	 *
	 * @throws AcnClientException if request failed
	 */
	public AccountRegistrationOK createOrLogin(AccountRegistrationModel model) {
		String method = "createOrLogin";
		try {
			URI uri = buildUri(CREATE_OR_LOGIN_URL);
			AccountRegistrationOK result = execute(new HttpPost(uri), JsonUtils.toJson(model),
					AccountRegistrationOK.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}
}
