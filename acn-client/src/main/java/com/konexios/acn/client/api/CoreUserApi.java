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

import org.apache.http.client.methods.HttpPost;

import com.konexios.acs.JsonUtils;
import com.konexios.acs.client.api.ApiConfig;
import com.konexios.acs.client.api.MqttHttpChannel;
import com.konexios.acs.client.model.SamlAccountModel;
import com.konexios.acs.client.model.StatusModel;

public class CoreUserApi extends ApiAbstract {
	private static final String CORE_USERS_BASE_URL = API_BASE + "/core/users";
	private static final String SYNC_SAML_ACCOUNTS_URL = CORE_USERS_BASE_URL + "/sync-saml-accounts";

	CoreUserApi(ApiConfig apiConfig, MqttHttpChannel mqttHttpChannel) {
		super(apiConfig, mqttHttpChannel);
	}

	public StatusModel syncSamlAccounts(List<SamlAccountModel> samlAccounts) {
		String method = "syncSamlAccounts";
		try {
			URI uri = buildUri(SYNC_SAML_ACCOUNTS_URL);
			StatusModel result = execute(new HttpPost(uri), JsonUtils.toJson(samlAccounts), StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}
}
