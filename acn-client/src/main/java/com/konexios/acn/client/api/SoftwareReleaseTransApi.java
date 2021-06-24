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
import java.util.Collections;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import com.konexios.acn.client.model.SoftwareReleaseTransModel;
import com.konexios.acn.client.model.SoftwareReleaseTransRegistrationModel;
import com.konexios.acn.client.model.SoftwareReleaseUpgradeModel;
import com.konexios.acs.AcsUtils;
import com.konexios.acs.JsonUtils;
import com.konexios.acs.client.api.ApiConfig;
import com.konexios.acs.client.api.MqttHttpChannel;
import com.konexios.acs.client.model.DownloadFileInfo;
import com.konexios.acs.client.model.HidModel;
import com.konexios.acs.client.model.StatusModel;

public class SoftwareReleaseTransApi extends ApiAbstract {
	private static final String SOFTWARE_RELEASE_TRANS_BASE_URL = API_BASE + "/software/releases/transactions";
	private static final String RECEIVED_URL = SOFTWARE_RELEASE_TRANS_BASE_URL + "/%s/received";
	private static final String SUCCEEDED_URL = SOFTWARE_RELEASE_TRANS_BASE_URL + "/%s/succeeded";
	private static final String FAILED_URL = SOFTWARE_RELEASE_TRANS_BASE_URL + "/%s/failed";
	private static final String RETRY_URL = SOFTWARE_RELEASE_TRANS_BASE_URL + "/%s/retry";
	private static final String CREATE_URL = SOFTWARE_RELEASE_TRANS_BASE_URL;
	private static final String UPGRADE_GATEWAY_URL = SOFTWARE_RELEASE_TRANS_BASE_URL + "/gateways/upgrade";
	private static final String UPGRADE_DEVICE_URL = SOFTWARE_RELEASE_TRANS_BASE_URL + "/devices/upgrade";
	private static final String START_URL = SOFTWARE_RELEASE_TRANS_BASE_URL + "/%s/start";
	private static final String FILE_URL = SOFTWARE_RELEASE_TRANS_BASE_URL + "/%s/%s/file";
	private static final String FIND_BY_HID = SOFTWARE_RELEASE_TRANS_BASE_URL + "/%s";

	public SoftwareReleaseTransApi(ApiConfig apiConfig, MqttHttpChannel mqttHttpChannel) {
		super(apiConfig, mqttHttpChannel);
	}

	public SoftwareReleaseTransModel findByHid(String hid) {
		String method = "findByHid";
		try {
			URI uri = buildUri(String.format(FIND_BY_HID, hid));
			SoftwareReleaseTransModel result = execute(new HttpGet(uri), SoftwareReleaseTransModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public DownloadFileInfo downloadFile(String hid, String tempToken) {
		String method = "file";
		try {
			URI uri = buildUri(String.format(FILE_URL, hid, tempToken));
			DownloadFileInfo result = downloadFile(new HttpGet(uri));
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public StatusModel received(String hid) {
		String method = "received";
		try {
			URI uri = buildUri(String.format(RECEIVED_URL, hid));
			StatusModel result = execute(new HttpPut(uri), StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public StatusModel succeeded(String hid) {
		String method = "succeeded";
		try {
			URI uri = buildUri(String.format(SUCCEEDED_URL, hid));
			StatusModel result = execute(new HttpPut(uri), StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public StatusModel failed(String hid, String error) {
		String method = "failed";
		try {
			URI uri = buildUri(String.format(FAILED_URL, hid));
			StatusModel result = execute(new HttpPut(uri),
					JsonUtils.toJson(Collections.singletonMap("error", AcsUtils.trimToEmpty(error))),
					StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public StatusModel retry(String hid) {
		String method = "retry";
		try {
			URI uri = buildUri(String.format(RETRY_URL, hid));
			StatusModel result = execute(new HttpPut(uri), StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public HidModel create(SoftwareReleaseTransRegistrationModel model) {
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

	public StatusModel start(String hid) {
		String method = "start";
		try {
			URI uri = buildUri(String.format(START_URL, hid));
			StatusModel result = execute(new HttpPost(uri), StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public HidModel upgradeGateway(SoftwareReleaseUpgradeModel model) {
		String method = "upgradeGateway";
		try {
			URI uri = buildUri(UPGRADE_GATEWAY_URL);
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public HidModel upgradeDevice(SoftwareReleaseUpgradeModel model) {
		String method = "upgradeDevice";
		try {
			URI uri = buildUri(UPGRADE_DEVICE_URL);
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}
}
