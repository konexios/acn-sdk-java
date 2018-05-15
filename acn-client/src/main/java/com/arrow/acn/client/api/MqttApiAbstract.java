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

import java.time.Instant;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;

import com.arrow.acn.AcnEventNames;
import com.arrow.acn.client.AcnClientException;
import com.arrow.acs.AcsUtils;
import com.arrow.acs.ApiHeaders;
import com.arrow.acs.ApiRequestSigner;
import com.arrow.acs.GatewayPayloadSigner;
import com.arrow.acs.client.api.ApiConfig;
import com.arrow.acs.client.model.CloudMqttRequestParams;
import com.arrow.acs.client.model.CloudRequestMethodName;
import com.arrow.acs.client.model.CloudRequestModel;
import com.arrow.acs.client.model.CloudRequestParameters;
import com.arrow.acs.client.model.CloudResponseModel;
import com.arrow.acs.client.search.SearchCriteria;

public class MqttApiAbstract extends ApiAbstract{

	private CouldResponseProcessorApi couldResponseProcessorApi;
	
	MqttApiAbstract(ApiConfig apiConfig, CouldResponseProcessorApi couldResponseProcessorApi) {
		super(apiConfig);
		this.couldResponseProcessorApi = couldResponseProcessorApi;
	}
	
	public void send(CloudMqttRequestParams params) {
		AcsUtils.notNull(couldResponseProcessorApi.getCloudConnector(), "cloud connector is null");
		String method = "send";
		logInfo(method, "...");

		// build request parameters
		CloudRequestParameters parameters = buildCloudRequestParameters(params.getUrl(), params.getHttpMethod(),
				params.getJsonBody(), params.getCriteria());

		// build request model
		CloudRequestModel requestModel = buildCloudRequestModel(params.getRequestId(), params.isEncrypted(), parameters);
		
		couldResponseProcessorApi.getCloudConnector().sendCloudRequest(requestModel);
	}

	private CloudRequestParameters buildCloudRequestParameters(String uri, CloudRequestMethodName method, String body,
			SearchCriteria criteria) {
		AcsUtils.notEmpty(uri, "uri is null");
		AcsUtils.notNull(method, "method is null");

		String methodName = "buildCloudRequestParameters";
		logDebug(methodName, "params: ...");

		CloudRequestParameters parameters = new CloudRequestParameters();
		parameters.setUri(uri);
		parameters.setMethod(method);
		parameters.setApiKey(getApiConfig().getApiKey());
		parameters.setBody(body);

		Instant timestamp = Instant.now();
		ApiRequestSigner signer = getMqttRequestParametersSigner(method, uri, timestamp);
		if (criteria != null) {
			for (NameValuePair pair : criteria.getAllCriteria()) {
				signer.parameter(pair.getName(), pair.getValue());
			}
		}
		String signature = signer.signV1();
		parameters.setApiRequestSignature(signature);
		parameters.setApiRequestSignatureVersion(ApiHeaders.X_ARROW_VERSION_1);
		parameters.setTimestamp(timestamp.toString());
		return parameters;
	}

	private CloudRequestModel buildCloudRequestModel(String requestId, boolean encrypted,
			CloudRequestParameters parameters) {
		CloudRequestModel requestModel = new CloudRequestModel();

		requestModel.setRequestId(requestId);

		requestModel.setEventName(AcnEventNames.GatewayToServer.API_REQUEST);
		requestModel.setEncrypted(encrypted);
		requestModel.setParameters(parameters.getParameters());

		GatewayPayloadSigner signer = GatewayPayloadSigner.create(getApiConfig().getSecretKey())
				.withApiKey(getApiConfig().getApiKey()).withHid(requestId).withName(requestModel.getEventName())
				.withEncrypted(requestModel.isEncrypted());
		for (Entry<String, String> entry : requestModel.getParameters().entrySet()) {
			signer.withParameter(entry.getKey(), entry.getValue());
		}
		String signature = signer.signV1();

		requestModel.setSignature(signature);
		requestModel.setSignatureVersion(GatewayPayloadSigner.PAYLOAD_SIGNATURE_VERSION_1);

		return requestModel;
	}
	
	private ApiRequestSigner getMqttRequestParametersSigner(CloudRequestMethodName method, String uri,
			Instant timestamp) {
		AcsUtils.notNull(method, "method is null");
		AcsUtils.notNull(timestamp, "timestamp is null");
		ApiConfig apiConfig = getApiConfig();
		AcsUtils.notEmpty(apiConfig.getApiKey(), "apiKey is empty");
		AcsUtils.notEmpty(apiConfig.getSecretKey(), "secretKey is empty");
		return ApiRequestSigner.create(apiConfig.getSecretKey()).method(String.valueOf(method)).canonicalUri(uri)
				.apiKey(apiConfig.getApiKey()).timestamp(timestamp.toString());
	}
	
	static void verifyCloudResponseResponse(CloudResponseModel cloudResponse) {
		String status = cloudResponse.getParameters().get(CloudResponseModel.STATUS_PARAMETER_NAME);
		if (!status.equals("OK")) {
			String message = cloudResponse.getParameters().get(CloudResponseModel.MESSAGE_PARAMETER_NAME);
			throw new AcnClientException(message);
		}
	}
	
}
