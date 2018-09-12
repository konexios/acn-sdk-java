/*******************************************************************************
 * Copyright (c) 2018 Arrow Electronics, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License 2.0
 * which accompanies this distribution, and is available at
 * http://apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *     Arrow Electronics, Inc.
 *******************************************************************************/
package com.arrow.acn.client.api;

import org.apache.commons.lang3.Validate;

import com.arrow.acs.AcsUtils;
import com.arrow.acs.client.api.ApiConfig;

public final class AcnClient {
	private ApiConfig apiConfig;
	private AccountApi accountApi;
	private CoreEventApi coreEventApi;
	private CoreUserApi coreUserApi;
	private DeviceActionApi deviceActionApi;
	private DeviceApi deviceApi;
	private DeviceStateApi deviceStateApi;
	private GatewayApi gatewayApi;
	private NodeApi nodeApi;
	private NodeTypeApi nodeTypeApi;
	private SoftwareReleaseScheduleApi softwareReleaseScheduleApi;
	private SoftwareReleaseTransApi softwareReleaseTransApi;
	private TelemetryApi telemetryApi;
	private ConfigBackupApi configBackupApi;
	private DeviceTelemetryClient deviceTelemetryClient;
	private NodeTelemetryClient nodeTelemetryClient;
	private RTUFirmwareApi rtuFirmwareApi;
	private TelemetryUnitApi telemetryUnitApi;

	public AcnClient(ApiConfig apiConfig) {
		AcsUtils.notNull(apiConfig, "apiConfig is not set");
		this.apiConfig = apiConfig;
	}

	public void setApiConfig(ApiConfig apiConfig) {
		Validate.notNull(apiConfig, "apiConfig is not set");
		this.apiConfig = apiConfig;
		getAccountApi().setApiConfig(apiConfig);
		getCoreEventApi().setApiConfig(apiConfig);
		getCoreUserApi().setApiConfig(apiConfig);
		getDeviceActionApi().setApiConfig(apiConfig);
		getDeviceApi().setApiConfig(apiConfig);
		getDeviceStateApi().setApiConfig(apiConfig);
		getGatewayApi().setApiConfig(apiConfig);
		getNodeApi().setApiConfig(apiConfig);
		getSoftwareReleaseScheduleApi().setApiConfig(apiConfig);
		getSoftwareReleaseTransApi().setApiConfig(apiConfig);
		getTelemetryApi().setApiConfig(apiConfig);
		getDeviceActionApi().setApiConfig(apiConfig);
		getConfigBackupApi().setApiConfig(apiConfig);
		getDeviceTelemetryClient().setApiConfig(apiConfig);
		getNodeTelemetryClient().setApiConfig(apiConfig);
		getRTUFirmwareApi().setApiConfig(apiConfig);
		getTelemetryUnitApi().setApiConfig(apiConfig);
	}

	public ApiConfig getApiConfig() {
		return apiConfig;
	}

	public synchronized AccountApi getAccountApi() {
		return accountApi != null ? accountApi : (accountApi = new AccountApi(apiConfig));
	}

	public synchronized CoreEventApi getCoreEventApi() {
		return coreEventApi != null ? coreEventApi : (coreEventApi = new CoreEventApi(apiConfig));
	}

	public synchronized CoreUserApi getCoreUserApi() {
		return coreUserApi != null ? coreUserApi : (coreUserApi = new CoreUserApi(apiConfig));
	}

	public synchronized DeviceActionApi getDeviceActionApi() {
		return deviceActionApi != null ? deviceActionApi : (deviceActionApi = new DeviceActionApi(apiConfig));
	}

	public synchronized DeviceApi getDeviceApi() {
		return deviceApi != null ? deviceApi : (deviceApi = new DeviceApi(apiConfig));
	}

	public synchronized DeviceStateApi getDeviceStateApi() {
		return deviceStateApi != null ? deviceStateApi : (deviceStateApi = new DeviceStateApi(apiConfig));
	}

	public synchronized GatewayApi getGatewayApi() {
		return gatewayApi != null ? gatewayApi : (gatewayApi = new GatewayApi(apiConfig));
	}

	public synchronized NodeApi getNodeApi() {
		return nodeApi != null ? nodeApi : (nodeApi = new NodeApi(apiConfig));
	}

	public synchronized NodeTypeApi getNodeTypeApi() {
		return nodeTypeApi != null ? nodeTypeApi : (nodeTypeApi = new NodeTypeApi(apiConfig));
	}

	public synchronized SoftwareReleaseScheduleApi getSoftwareReleaseScheduleApi() {
		return softwareReleaseScheduleApi != null ? softwareReleaseScheduleApi
		        : (softwareReleaseScheduleApi = new SoftwareReleaseScheduleApi(apiConfig));
	}

	public synchronized SoftwareReleaseTransApi getSoftwareReleaseTransApi() {
		return softwareReleaseTransApi != null ? softwareReleaseTransApi
		        : (softwareReleaseTransApi = new SoftwareReleaseTransApi(apiConfig));
	}

	public synchronized TelemetryApi getTelemetryApi() {
		return telemetryApi != null ? telemetryApi : (telemetryApi = new TelemetryApi(apiConfig));
	}

	public synchronized ConfigBackupApi getConfigBackupApi() {
		return configBackupApi != null ? configBackupApi : (configBackupApi = new ConfigBackupApi(apiConfig));
	}

	public synchronized DeviceTelemetryClient getDeviceTelemetryClient() {
		return deviceTelemetryClient != null ? deviceTelemetryClient
		        : (deviceTelemetryClient = new DeviceTelemetryClient(apiConfig));
	}

	public synchronized NodeTelemetryClient getNodeTelemetryClient() {
		return nodeTelemetryClient != null ? nodeTelemetryClient
		        : (nodeTelemetryClient = new NodeTelemetryClient(apiConfig));
	}

	public synchronized RTUFirmwareApi getRTUFirmwareApi() {
		return rtuFirmwareApi != null ? rtuFirmwareApi : (rtuFirmwareApi = new RTUFirmwareApi(apiConfig));
	}

	public synchronized TelemetryUnitApi getTelemetryUnitApi() {
		return telemetryUnitApi != null ? telemetryUnitApi : (telemetryUnitApi = new TelemetryUnitApi(apiConfig));
	}
}
