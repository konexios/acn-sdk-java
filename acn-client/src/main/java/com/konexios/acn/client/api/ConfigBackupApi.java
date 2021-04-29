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

import com.konexios.acn.client.model.ConfigBackupModel;
import com.konexios.acs.client.api.ApiConfig;
import com.konexios.acs.client.api.MqttHttpChannel;

public class ConfigBackupApi extends ApiAbstract {

	private static final String CONFIG_BACKUPS_BASE_URL = API_BASE + "/config-backups";
	private static final String SPECIFIC_CONFIG_BACKUP_URL = CONFIG_BACKUPS_BASE_URL + "/{hid}";

	ConfigBackupApi(ApiConfig apiConfig, MqttHttpChannel mqttHttpChannel) {
		super(apiConfig, mqttHttpChannel);
	}

	public ConfigBackupModel findByHid(String hid) {
		String method = "findByHid";
		try {
			URI uri = buildUri(SPECIFIC_CONFIG_BACKUP_URL.replace("{hid}", hid));
			ConfigBackupModel result = execute(new HttpGet(uri), ConfigBackupModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}
}
