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
package com.konexios.acn.client.cloud.ibm;

import java.util.List;
import java.util.Properties;

import com.ibm.iotf.client.gateway.Command;
import com.ibm.iotf.client.gateway.GatewayCallback;
import com.ibm.iotf.client.gateway.GatewayClient;
import com.ibm.iotf.client.gateway.Notification;

import com.konexios.acn.client.ClientConstants;
import com.konexios.acn.client.IotParameters;
import com.konexios.acn.client.api.AcnClient;
import com.konexios.acn.client.cloud.CloudConnectorAbstract;
import com.konexios.acn.client.cloud.TransferMode;
import com.konexios.acn.client.model.IbmConfigModel;
import com.konexios.acn.client.utils.Utils;
import com.konexios.acs.AcsLogicalException;
import com.konexios.acs.AcsUtils;

public class IbmConnector extends CloudConnectorAbstract {
	private static final String HEADER_ORGANIZATION_ID = "Organization-ID";
	private static final String HEADER_AUTHENTICATION_MODE = "Authentication-Method";
	private static final String HEADER_AUTHENTICATION_TOKEN = "Authentication-Token";
	private static final String HEADER_GATEWAY_TYPE = "Gateway-Type";
	private static final String HEADER_GATEWAY_ID = "Gateway-ID";
	private static final String HEADER_REGISTRATION_MODE = "Registration-Mode";
	private static final String VALUE_REGISTRATION_MODE = "Automatic";

	private static final String DEFAULT_EVENT = "telemetry";

	private final IbmConfigModel gatewayModel;
	private int qos;

	private GatewayClient gatewayClient;
	private GatewayCallback gatewayCallback = new DefaultGatewayCallback();

	public IbmConnector(AcnClient acnClient, String gatewayHid, IbmConfigModel gatewayModel) {
		super(acnClient, gatewayHid);
		this.gatewayModel = gatewayModel;
	}

	@Override
	public void start() {
		configureAndConnect();
	}

	private void configureAndConnect() {
		String method = "configureAndConnect";
		Properties props = new Properties();
		props.setProperty(HEADER_ORGANIZATION_ID, gatewayModel.getOrganizationId());
		props.setProperty(HEADER_AUTHENTICATION_MODE, gatewayModel.getAuthMethod());
		props.setProperty(HEADER_AUTHENTICATION_TOKEN, gatewayModel.getAuthToken());
		props.setProperty(HEADER_GATEWAY_ID, gatewayModel.getGatewayId());
		props.setProperty(HEADER_GATEWAY_TYPE, gatewayModel.getGatewayType());
		props.setProperty(HEADER_REGISTRATION_MODE, VALUE_REGISTRATION_MODE);
		try {
			gatewayClient = new GatewayClient(props);
			gatewayClient.setGatewayCallback(gatewayCallback);
			gatewayClient.connect(true);
		} catch (Exception e) {
			logError(method, "cannot connect to IBM Watson IoT", e);
		}
	}

	@Override
	public void stop() {
		gatewayClient.disconnect();
		super.stop();
	}

	@Override
	public void send(IotParameters payload) {
		String method = "send";

		// check gateway client
		while (gatewayClient == null || !gatewayClient.isConnected()) {
			logError(method, "gatewayClient is not ready, check back in 5 seconds ...");
			Utils.sleep(ClientConstants.DEFAULT_CLOUD_SENDING_RETRY_MS);
		}

		if (!AcsUtils.isEmpty(payload.getExternalId()) && !AcsUtils.isEmpty(payload.getDeviceType())) {
			logDebug(method, "sending message to cloud via type: %s, device: %s", payload.getDeviceType(),
					payload.getExternalId());
			gatewayClient.publishDeviceEvent(payload.getDeviceType(), payload.getExternalId(), DEFAULT_EVENT, payload,
					getQos());

			// workaround due to some change in IBM cloud that the above method
			// no longer works
			logDebug(method, "sending message to cloud via gateway: %s", getGatewayHid());
			gatewayClient.publishGatewayEvent(DEFAULT_EVENT, payload, getQos());
		} else {
			logDebug(method, "sending message to cloud via gateway: %s", getGatewayHid());
			gatewayClient.publishGatewayEvent(DEFAULT_EVENT, payload, getQos());
		}
		logInfo(method, "message was sent successfully");
	}

	@Override
	public void sendBatch(List<IotParameters> batch, TransferMode transferMode) {
		if (transferMode == TransferMode.GZIP_BATCH) {
			throw new AcsLogicalException(
					"TransferMode not supported for IBM integration: " + TransferMode.GZIP_BATCH.name());
		}
		if (batch != null) {
			batch.forEach(this::send);
		}
	}

	public int getQos() {
		return qos;
	}

	public void setQos(int qos) {
		this.qos = qos;
	}

	private class DefaultGatewayCallback implements GatewayCallback {

		DefaultGatewayCallback() {
		}

		@Override
		public void processCommand(Command cmd) {
			validateAndProcessEvent(cmd.getCommand(), cmd.getRawPayload());
		}

		@Override
		public void processNotification(Notification notification) {
			// not implemented in library
		}
	}
}
