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
package com.arrow.acn.client.cloud;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import com.arrow.acn.client.AcnClientException;
import com.arrow.acn.client.IotParameters;
import com.arrow.acn.client.api.AcnClient;
import com.arrow.acn.client.api.CoreEventApi;
import com.arrow.acs.AcsUtils;
import com.arrow.acs.GatewayPayloadSigner;
import com.arrow.acs.JsonUtils;
import com.arrow.acs.Loggable;
import com.arrow.acs.client.api.ApiConfig;
import com.arrow.acs.client.model.CloudRequestModel;
import com.arrow.acs.client.model.CloudResponseModel;
import com.arrow.acs.client.model.GatewayEventModel;

public abstract class CloudConnectorAbstract extends Loggable {
	private String gatewayHid;
	protected MessageListener listener;
	protected MessageListener cloudResponselistener;
	protected AcnClient acnClient;

	protected CloudConnectorAbstract(AcnClient acnClient) {
		this.acnClient = acnClient;
	}

	protected String getGatewayHid() {
		return gatewayHid;
	}

	public void setGatewayHid(String gatewayHid) {
		this.gatewayHid = gatewayHid;
	}

	public void setListener(MessageListener listener) {
		this.listener = listener;
	}

	public void setCloudResponseListener(MessageListener listener) {
		this.cloudResponselistener = listener;
	}

	public abstract void start();

	public abstract void stop();

	public abstract void send(IotParameters payload);

	public abstract void sendBatch(List<IotParameters> batch, TransferMode transferMode);

	public void sendCloudRequest(CloudRequestModel cloudRequest) {
		String method = "sendCloudRequest";
		logError(method, "method is not implemented");
		throw new AcnClientException("Cloud Request send method is not supported");
	}

	protected void validateAndProcessEvent(String topic, byte[] payload) {
		String method = "validateAndProcessEvent";
		GatewayEventModel model = JsonUtils.fromJson(new String(payload, Charset.defaultCharset()),
				GatewayEventModel.class);
		if (model == null || AcsUtils.isEmpty(model.getName())) {
			logError(method, "ignore invalid payload: %s", payload);
			return;
		}
		CoreEventApi eventApi = acnClient.getCoreEventApi();
		eventApi.putReceived(model.getHid());
		if (isSignatureValid(model)) {
			logDebug(method, "signature is valid");
			if (listener == null) {
				logError(method, "listener is not defined");
				eventApi.putFailed(model.getHid(), "Listener is not defined");
			} else {
				try {
					listener.processMessage(topic, payload);
					eventApi.putSucceeded(model.getHid());
				} catch (Exception e) {
					eventApi.putFailed(model.getHid(), e.getMessage());
				}
			}
		} else {
			logWarn(method, "signature is invalid");
			eventApi.putFailed(model.getHid(), "Signature is invalid");
		}
	}

	protected void validateAndProcessCloudResponse(String topic, byte[] payload) {
		String method = "validateAndProcessCloudResponse";
		CloudResponseModel model = JsonUtils.fromJson(new String(payload, Charset.defaultCharset()),
				CloudResponseModel.class);
		if (model == null || AcsUtils.isEmpty(model.getEventName())) {
			logError(method, "ignore invalid payload: %s", payload);
			return;
		}
		if(isCloudResponseSignatureValid(model)){
			logDebug(method, "cloud response signature is valid");
			if (cloudResponselistener == null) {
				logError(method, "cloud response listener is not defined");
			}else {
				try {
					cloudResponselistener.processMessage(topic, payload);
				} catch (Exception e) {
					logError(method, "cloud response listener failed to process a message");
				}
			}
		}else {
			logError(method, "signature is invalid");
		}
	}

	private boolean isSignatureValid(GatewayEventModel model) {
		String method = "isSignatureValid";
		if (AcsUtils.isEmpty(model.getSignature())) {
			logDebug(method, "skipping signature validation");
			return true;
		}
		logDebug(method, "validating signature");
		return isSignatureValid(model.getHid(), model.getName(), model.isEncrypted(), model.getParameters(),
				model.getSignature(), model.getSignatureVersion());
	}

	private boolean isCloudResponseSignatureValid(CloudResponseModel model) {
		String method = "isCloudResponseSignatureValid";
		if (AcsUtils.isEmpty(model.getSignature())) {
			logDebug(method, "skipping signature validation");
			return true;
		}
		logDebug(method, "validating signature");
		return isSignatureValid(model.getRequestId(), model.getEventName(), model.isEncrypted(), model.getParameters(),
				model.getSignature(), model.getSignatureVersion());
	}

	private boolean isSignatureValid(String hid, String name, boolean encrypted, Map<String, String> parameters,
			String signature, String signatureVersion) {
		String method = "isSignatureValid";
		logDebug(method, "validating signature");
		ApiConfig apiConfig = acnClient.getApiConfig();
		GatewayPayloadSigner signer = GatewayPayloadSigner.create(apiConfig.getSecretKey())
				.withApiKey(apiConfig.getApiKey()).withHid(hid).withName(name).withEncrypted(encrypted);
		for (Entry<String, String> entry : parameters.entrySet()) {
			signer.withParameter(entry.getKey(), entry.getValue());
		}
		switch (signatureVersion) {
		case GatewayPayloadSigner.PAYLOAD_SIGNATURE_VERSION_1: {
			return Objects.equals(signer.signV1(), signature);
		}
		default: {
			logWarn(method, "signature of version %s is not supported", signatureVersion);
			return false;
		}
		}
	}
}
