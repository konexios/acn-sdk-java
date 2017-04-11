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

import org.apache.commons.lang3.Validate;

import com.arrow.acs.client.api.ApiConfig;

public final class AcnClient {
	private ApiConfig apiConfig;
	private final AccountApi accountApi;
	private final CoreEventApi coreEventApi;
	private final CoreUserApi coreUserApi;
	private final DeviceActionApi deviceActionApi;
	private final DeviceApi deviceApi;
	private final GatewayApi gatewayApi;
	private final NodeApi nodeApi;
	private final NodeTypeApi nodeTypeApi;
	private final TelemetryApi telemetryApi;
	private final DeviceStateApi deviceStateApi;

	public AcnClient(ApiConfig apiConfig) {
		Validate.notNull(apiConfig, "apiConfig is not set");
		this.apiConfig = apiConfig;
		this.accountApi = new AccountApi(apiConfig);
		this.coreEventApi = new CoreEventApi(apiConfig);
		this.coreUserApi = new CoreUserApi(apiConfig);
		this.deviceActionApi = new DeviceActionApi(apiConfig);
		this.deviceApi = new DeviceApi(apiConfig);
		this.gatewayApi = new GatewayApi(apiConfig);
		this.nodeApi = new NodeApi(apiConfig);
		this.nodeTypeApi = new NodeTypeApi(apiConfig);
		this.telemetryApi = new TelemetryApi(apiConfig);
		this.deviceStateApi = new DeviceStateApi(apiConfig);
	}

	public void setApiConfig(ApiConfig apiConfig) {
		this.apiConfig = apiConfig;
		accountApi.setApiConfig(apiConfig);
		coreEventApi.setApiConfig(apiConfig);
		coreUserApi.setApiConfig(apiConfig);
		deviceActionApi.setApiConfig(apiConfig);
		deviceApi.setApiConfig(apiConfig);
		gatewayApi.setApiConfig(apiConfig);
		nodeApi.setApiConfig(apiConfig);
		nodeTypeApi.setApiConfig(apiConfig);
		telemetryApi.setApiConfig(apiConfig);
		deviceActionApi.setApiConfig(apiConfig);
	}

	public ApiConfig getApiConfig() {
		return apiConfig;
	}

	public AccountApi getAccountApi() {
		return accountApi;
	}

	public CoreEventApi getCoreEventApi() {
		return coreEventApi;
	}

	public CoreUserApi getCoreUserApi() {
		return coreUserApi;
	}

	public DeviceActionApi getDeviceActionApi() {
		return deviceActionApi;
	}

	public DeviceApi getDeviceApi() {
		return deviceApi;
	}

	public GatewayApi getGatewayApi() {
		return gatewayApi;
	}

	public NodeApi getNodeApi() {
		return nodeApi;
	}

	public NodeTypeApi getNodeTypeApi() {
		return nodeTypeApi;
	}

	public TelemetryApi getTelemetryApi() {
		return telemetryApi;
	}

	public DeviceStateApi getDeviceStateApi() {
		return deviceStateApi;
	}
}
