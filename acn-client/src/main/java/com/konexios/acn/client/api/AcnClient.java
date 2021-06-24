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

import com.konexios.acs.AcsUtils;
import com.konexios.acs.client.api.ApiConfig;
import com.konexios.acs.client.api.MqttHttpChannel;

public final class AcnClient {
	private ApiConfig apiConfig;
	private AccountApi accountApi;
	private ConfigBackupApi configBackupApi;
	private CoreEventApi coreEventApi;
	private CoreUserApi coreUserApi;
	private DeviceActionApi deviceActionApi;
	private DeviceApi deviceApi;
	private DeviceStateApi deviceStateApi;
	private DeviceTypeApi deviceTypeApi;
	private GatewayApi gatewayApi;
	private NodeApi nodeApi;
	private NodeTypeApi nodeTypeApi;
	private RTUFirmwareApi rtuFirmwareApi;
	private SoftwareReleaseScheduleApi softwareReleaseScheduleApi;
	private SoftwareReleaseTransApi softwareReleaseTransApi;
	private TelemetryApi telemetryApi;
	private TelemetryUnitApi telemetryUnitApi;
	private UserApi userApi;

	private MqttHttpChannel mqttHttpChannel;

	public AcnClient(ApiConfig apiConfig) {
		AcsUtils.notNull(apiConfig, "apiConfig is not set");
		this.apiConfig = apiConfig;
	}

	public void setApiConfig(ApiConfig apiConfig) {
		AcsUtils.notNull(apiConfig, "apiConfig is not set");
		this.apiConfig = apiConfig;
		if (accountApi != null)
			getAccountApi().setApiConfig(apiConfig);
		if (configBackupApi != null)
			getConfigBackupApi().setApiConfig(apiConfig);
		if (coreEventApi != null)
			getCoreEventApi().setApiConfig(apiConfig);
		if (coreUserApi != null)
			getCoreUserApi().setApiConfig(apiConfig);
		if (deviceActionApi != null)
			getDeviceActionApi().setApiConfig(apiConfig);
		if (deviceApi != null)
			getDeviceApi().setApiConfig(apiConfig);
		if (deviceStateApi != null)
			getDeviceStateApi().setApiConfig(apiConfig);
		if (deviceTypeApi != null)
			getDeviceTypeApi().setApiConfig(apiConfig);
		if (gatewayApi != null)
			getGatewayApi().setApiConfig(apiConfig);
		if (nodeApi != null)
			getNodeApi().setApiConfig(apiConfig);
		if (nodeTypeApi != null)
			getNodeTypeApi().setApiConfig(apiConfig);
		if (rtuFirmwareApi != null)
			getRTUFirmwareApi().setApiConfig(apiConfig);
		if (softwareReleaseScheduleApi != null)
			getSoftwareReleaseScheduleApi().setApiConfig(apiConfig);
		if (softwareReleaseTransApi != null)
			getSoftwareReleaseTransApi().setApiConfig(apiConfig);
		if (telemetryApi != null)
			getTelemetryApi().setApiConfig(apiConfig);
		if (telemetryUnitApi != null)
			getTelemetryUnitApi().setApiConfig(apiConfig);
		if (userApi != null)
			getUserApi().setApiConfig(apiConfig);
	}

	public void setMqttHttpChannel(MqttHttpChannel mqttHttpChannel) {
		AcsUtils.notNull(mqttHttpChannel, "mqttHttpChannel is not set");
		this.mqttHttpChannel = mqttHttpChannel;
		if (accountApi != null)
			getAccountApi().setMqttHttpChannel(mqttHttpChannel);
		if (configBackupApi != null)
			getConfigBackupApi().setMqttHttpChannel(mqttHttpChannel);
		if (coreEventApi != null)
			getCoreEventApi().setMqttHttpChannel(mqttHttpChannel);
		if (coreUserApi != null)
			getCoreUserApi().setMqttHttpChannel(mqttHttpChannel);
		if (deviceActionApi != null)
			getDeviceActionApi().setMqttHttpChannel(mqttHttpChannel);
		if (deviceApi != null)
			getDeviceApi().setMqttHttpChannel(mqttHttpChannel);
		if (deviceStateApi != null)
			getDeviceStateApi().setMqttHttpChannel(mqttHttpChannel);
		if (deviceTypeApi != null)
			getDeviceTypeApi().setMqttHttpChannel(mqttHttpChannel);
		if (gatewayApi != null)
			getGatewayApi().setMqttHttpChannel(mqttHttpChannel);
		if (nodeApi != null)
			getNodeApi().setMqttHttpChannel(mqttHttpChannel);
		if (nodeTypeApi != null)
			getNodeTypeApi().setMqttHttpChannel(mqttHttpChannel);
		if (rtuFirmwareApi != null)
			getRTUFirmwareApi().setMqttHttpChannel(mqttHttpChannel);
		if (softwareReleaseScheduleApi != null)
			getSoftwareReleaseScheduleApi().setMqttHttpChannel(mqttHttpChannel);
		if (softwareReleaseTransApi != null)
			getSoftwareReleaseTransApi().setMqttHttpChannel(mqttHttpChannel);
		if (telemetryApi != null)
			getTelemetryApi().setMqttHttpChannel(mqttHttpChannel);
		if (telemetryUnitApi != null)
			getTelemetryUnitApi().setMqttHttpChannel(mqttHttpChannel);
		if (userApi != null)
			getUserApi().setMqttHttpChannel(mqttHttpChannel);
	}

	public MqttHttpChannel getMqttHttpChannel() {
		return mqttHttpChannel;
	}

	public ApiConfig getApiConfig() {
		return apiConfig;
	}

	public synchronized AccountApi getAccountApi() {
		return accountApi != null ? accountApi : (accountApi = new AccountApi(apiConfig, mqttHttpChannel));
	}

	public synchronized CoreEventApi getCoreEventApi() {
		return coreEventApi != null ? coreEventApi : (coreEventApi = new CoreEventApi(apiConfig, mqttHttpChannel));
	}

	public synchronized CoreUserApi getCoreUserApi() {
		return coreUserApi != null ? coreUserApi : (coreUserApi = new CoreUserApi(apiConfig, mqttHttpChannel));
	}

	public synchronized DeviceActionApi getDeviceActionApi() {
		return deviceActionApi != null ? deviceActionApi
				: (deviceActionApi = new DeviceActionApi(apiConfig, mqttHttpChannel));
	}

	public synchronized DeviceApi getDeviceApi() {
		return deviceApi != null ? deviceApi : (deviceApi = new DeviceApi(apiConfig, mqttHttpChannel));
	}

	public synchronized DeviceStateApi getDeviceStateApi() {
		return deviceStateApi != null ? deviceStateApi
				: (deviceStateApi = new DeviceStateApi(apiConfig, mqttHttpChannel));
	}

	public synchronized GatewayApi getGatewayApi() {
		return gatewayApi != null ? gatewayApi : (gatewayApi = new GatewayApi(apiConfig, mqttHttpChannel));
	}

	public synchronized NodeApi getNodeApi() {
		return nodeApi != null ? nodeApi : (nodeApi = new NodeApi(apiConfig, mqttHttpChannel));
	}

	public synchronized NodeTypeApi getNodeTypeApi() {
		return nodeTypeApi != null ? nodeTypeApi : (nodeTypeApi = new NodeTypeApi(apiConfig, mqttHttpChannel));
	}

	public synchronized SoftwareReleaseScheduleApi getSoftwareReleaseScheduleApi() {
		return softwareReleaseScheduleApi != null ? softwareReleaseScheduleApi
				: (softwareReleaseScheduleApi = new SoftwareReleaseScheduleApi(apiConfig, mqttHttpChannel));
	}

	public synchronized SoftwareReleaseTransApi getSoftwareReleaseTransApi() {
		return softwareReleaseTransApi != null ? softwareReleaseTransApi
				: (softwareReleaseTransApi = new SoftwareReleaseTransApi(apiConfig, mqttHttpChannel));
	}

	public synchronized TelemetryApi getTelemetryApi() {
		return telemetryApi != null ? telemetryApi : (telemetryApi = new TelemetryApi(apiConfig, mqttHttpChannel));
	}

	public synchronized ConfigBackupApi getConfigBackupApi() {
		return configBackupApi != null ? configBackupApi
				: (configBackupApi = new ConfigBackupApi(apiConfig, mqttHttpChannel));
	}

	public synchronized RTUFirmwareApi getRTUFirmwareApi() {
		return rtuFirmwareApi != null ? rtuFirmwareApi
				: (rtuFirmwareApi = new RTUFirmwareApi(apiConfig, mqttHttpChannel));
	}

	public synchronized TelemetryUnitApi getTelemetryUnitApi() {
		return telemetryUnitApi != null ? telemetryUnitApi
				: (telemetryUnitApi = new TelemetryUnitApi(apiConfig, mqttHttpChannel));
	}

	public synchronized DeviceTypeApi getDeviceTypeApi() {
		return deviceTypeApi != null ? deviceTypeApi : (deviceTypeApi = new DeviceTypeApi(apiConfig, mqttHttpChannel));
	}

	public synchronized UserApi getUserApi() {
		return userApi != null ? userApi : (userApi = new UserApi(apiConfig, mqttHttpChannel));
	}
}
