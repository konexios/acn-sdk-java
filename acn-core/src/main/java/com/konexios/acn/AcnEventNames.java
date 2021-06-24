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
package com.konexios.acn;

public interface AcnEventNames {
	interface ServerToGateway {
		final static String GATEWAY_SOFTWARE_UPDATE = "ServerToGateway_GatewaySoftwareUpdate";

		final static String DEVICE_START = "ServerToGateway_DeviceStart";
		final static String DEVICE_STOP = "ServerToGateway_DeviceStop";

		final static String DEVICE_PROPERTY_CHANGE = "ServerToGateway_DevicePropertyChange";
		final static String DEVICE_COMMAND = "ServerToGateway_DeviceCommand";
		final static String DEVICE_STATE_REQUEST = "ServerToGateway_DeviceStateRequest";

		final static String SENSOR_PROPERTY_CHANGE = "ServerToGateway_SensorPropertyChange";
		final static String SENSOR_TELEMETRY_CHANGE = "ServerToGateway_SensorTelemetryChange";

		final static String GATEWAY_SOFTWARE_RELEASE = "ServerToGateway_GatewaySoftwareRelease";
		final static String DEVICE_SOFTWARE_RELEASE = "ServerToGateway_DeviceSoftwareRelease";

		final static String GATEWAY_CONFIGURATION_UPDATE = "ServerToGateway_GatewayConfigurationUpdate";
		final static String DEVICE_CONFIGURATION_UPDATE = "ServerToGateway_DeviceConfigurationUpdate";

		final static String GATEWAY_CONFIGURATION_RESTORE = "ServerToGateway_GatewayConfigurationRestore";
		final static String DEVICE_CONFIGURATION_RESTORE = "ServerToGateway_DeviceConfigurationRestore";

		final static String API_RESPONSE = "ServerToGateway_ApiResponse";

	}

	interface GatewayToServer {
		final static String API_REQUEST = "GatewayToServer_ApiRequest";
	}
}
