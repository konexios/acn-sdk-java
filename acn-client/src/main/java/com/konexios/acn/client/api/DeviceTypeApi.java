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

import com.konexios.acn.client.AcnClientException;
import com.konexios.acn.client.model.DeviceTypeModel;
import com.konexios.acs.JsonUtils;
import com.konexios.acs.client.api.ApiConfig;
import com.konexios.acs.client.api.MqttHttpChannel;
import com.konexios.acs.client.model.HidModel;
import com.konexios.acs.client.model.ListResultModel;

public class DeviceTypeApi extends ApiAbstract {
	private static final String DEVICES_BASE_URL = API_BASE + "/devices";
	private static final String DEVICE_TYPES_URL = DEVICES_BASE_URL + "/types";
	private static final String SPECIFIC_DEVICE_TYPE_URL = DEVICE_TYPES_URL + "/{hid}";

	private TypeReference<ListResultModel<DeviceTypeModel>> deviceTypeModelTypeRef;

	DeviceTypeApi(ApiConfig apiConfig, MqttHttpChannel mqttHttpChannel) {
		super(apiConfig, mqttHttpChannel);
	}

	/**
	 * Sends GET request to obtain parameters of all available device types
	 *
	 * @return list of {@link DeviceTypeModel} containing type parameters
	 *
	 * @throws AcnClientException if request failed
	 */
	public ListResultModel<DeviceTypeModel> listExistingDeviceTypes() {
		String method = "listExistingDeviceTypes";
		try {
			URI uri = buildUri(DEVICE_TYPES_URL);
			ListResultModel<DeviceTypeModel> result = execute(new HttpGet(uri), getDeviceTypeModelTypeRef());
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	/**
	 * Sends POST request to create new device type according to {@code model}
	 * passed
	 * 
	 * @param model {@link DeviceTypeModel} representing parameters of device type
	 *              to be created
	 *
	 * @return {@link HidModel} containing {@code hid} of device type created
	 *
	 * @throws AcnClientException if request failed
	 */
	public HidModel createNewDeviceType(DeviceTypeModel model) {
		String method = "createNewDeviceType";
		try {
			URI uri = buildUri(DEVICE_TYPES_URL);
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	/**
	 * Sends PUT request to update existing device type according to {@code model}
	 * passed
	 *
	 * @param hid   {@link String} representing {@code hid} of device type to be
	 *              updated
	 * @param model {@link DeviceTypeModel} representing device type parameters to
	 *              be updated
	 *
	 * @return {@link HidModel} containing {@code hid} of device type updated
	 *
	 * @throws AcnClientException if request failed
	 */
	public HidModel updateDeviceType(String hid, DeviceTypeModel model) {
		String method = "updateDeviceAction";
		try {
			URI uri = buildUri(SPECIFIC_DEVICE_TYPE_URL.replace("{hid}", hid));
			HidModel result = execute(new HttpPut(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	private synchronized TypeReference<ListResultModel<DeviceTypeModel>> getDeviceTypeModelTypeRef() {
		return deviceTypeModelTypeRef != null ? deviceTypeModelTypeRef
				: (deviceTypeModelTypeRef = new TypeReference<ListResultModel<DeviceTypeModel>>() {
				});
	}
}
