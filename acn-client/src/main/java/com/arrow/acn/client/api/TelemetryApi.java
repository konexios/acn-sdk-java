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

import java.net.URI;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import com.arrow.acn.client.AcnClientException;
import com.arrow.acn.client.IotParameters;
import com.arrow.acn.client.model.TelemetryItemModel;
import com.arrow.acn.client.search.TelemetrySearchCriteria;
import com.arrow.acs.JsonUtils;
import com.arrow.acs.client.api.ApiConfig;
import com.arrow.acs.client.model.PagingResultModel;
import com.arrow.acs.client.model.StatusModel;

public final class TelemetryApi extends ApiAbstract {
	private final String TELEMETRY_BASE_URL = API_BASE + "/telemetries";
	private final String FIND_BY_APPLICATION_HID = TELEMETRY_BASE_URL + "/applications/{applicationHid}";
	private final String FIND_BY_DEVICE_HID = TELEMETRY_BASE_URL + "/devices/{deviceHid}";
	private final String FIND_BY_NODE_HID = TELEMETRY_BASE_URL + "/nodes/{nodeHid}";
	private final String CREATE_URL = TELEMETRY_BASE_URL;
	private final String BATCH_CREATE_URL = CREATE_URL + "/batch";
	private final TypeReference<PagingResultModel<TelemetryItemModel>> TELEMETRY_ITEM_MODEL_TYPE_REF = new TypeReference<PagingResultModel<TelemetryItemModel>>() {
	};

	TelemetryApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	/**
	 * Sends POST request to store telemetry
	 *
	 * @param parameters
	 *            {@link IotParameters} representing bunch of telemetry received
	 *            from a device
	 *
	 * @return {@link StatusModel} containing status of telemetry storing
	 *         request
	 *
	 * @throws AcnClientException
	 *             if request failed
	 */
	public StatusModel create(IotParameters parameters) {
		String method = "create";
		try {
			URI uri = buildUri(CREATE_URL);
			StatusModel result = execute(new HttpPost(uri), JsonUtils.toJson(parameters), StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	/**
	 * Sends GET request to obtain telemetry by specific {@code applicationHid}
	 * and corresponding to {@code criteria}
	 *
	 * @param applicationHid
	 *            {@link String} representing specific application Hid
	 * @param criteria
	 *            {@link TelemetrySearchCriteria} representing search filter
	 *            parameters
	 *
	 * @return subset of {@link TelemetryItemModel} containing telemetry
	 *         parameters. <b>Note:</b> resulting subset may contain not all
	 *         telemetry corresponding to search parameters because it cannot
	 *         exceed page size passed in {@code criteria}
	 *
	 * @throws AcnClientException
	 *             if request failed
	 */
	public PagingResultModel<TelemetryItemModel> findByApplicationHid(String applicationHid,
	        TelemetrySearchCriteria criteria) {
		String method = "findByApplicationHid";
		try {
			URI uri = buildUri(FIND_BY_APPLICATION_HID.replace("{applicationHid}", applicationHid), criteria);
			PagingResultModel<TelemetryItemModel> result = execute(new HttpGet(uri), criteria,
			        TELEMETRY_ITEM_MODEL_TYPE_REF);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	/**
	 * Sends GET request to obtain telemetry of specific device and
	 * corresponding to {@code criteria}
	 *
	 * @param deviceHid
	 *            {@link String} representing specific device
	 * @param criteria
	 *            {@link TelemetrySearchCriteria} representing search filter
	 *            parameters
	 *
	 * @return subset of {@link TelemetryItemModel} containing telemetry
	 *         parameters. <b>Note:</b> resulting subset may contain not all
	 *         telemetry corresponding to search parameters because it cannot
	 *         exceed page size passed in {@code criteria}
	 *
	 * @throws AcnClientException
	 *             if request failed
	 */
	public PagingResultModel<TelemetryItemModel> findByDeviceHid(String deviceHid, TelemetrySearchCriteria criteria) {
		String method = "findByDeviceHid";
		try {
			URI uri = buildUri(FIND_BY_DEVICE_HID.replace("{deviceHid}", deviceHid), criteria);
			PagingResultModel<TelemetryItemModel> result = execute(new HttpGet(uri), criteria,
			        TELEMETRY_ITEM_MODEL_TYPE_REF);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	/**
	 * Sends GET request to obtain telemetry by specific {@code nodeHid} and
	 * corresponding to {@code criteria}
	 *
	 * @param nodeHid
	 *            {@link String} representing specific node Hid
	 * @param criteria
	 *            {@link TelemetrySearchCriteria} representing search filter
	 *            parameters
	 *
	 * @return subset of {@link TelemetryItemModel} containing telemetry
	 *         parameters. <b>Note:</b> resulting subset may contain not all
	 *         telemetry corresponding to search parameters because it cannot
	 *         exceed page size passed in {@code criteria}
	 *
	 * @throws AcnClientException
	 *             if request failed
	 */
	public PagingResultModel<TelemetryItemModel> findByNodeHid(String nodeHid, TelemetrySearchCriteria criteria) {
		String method = "findByNodeHid";
		try {
			URI uri = buildUri(FIND_BY_NODE_HID.replace("{nodeHid}", nodeHid), criteria);
			PagingResultModel<TelemetryItemModel> result = execute(new HttpGet(uri), criteria,
			        TELEMETRY_ITEM_MODEL_TYPE_REF);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	/**
	 * Sends POST request to store set of telemetry
	 *
	 * @param parameters
	 *            {@link IotParameters} representing set of telemetry bunches
	 *            received from devices
	 *
	 * @return {@link StatusModel} containing status of batch telemetry creation
	 *         request
	 *
	 * @throws AcnClientException
	 *             if request failed
	 */
	public StatusModel batchCreate(List<IotParameters> parameters) {
		String method = "batchCreate";
		try {
			URI uri = buildUri(BATCH_CREATE_URL);
			StatusModel result = execute(new HttpPost(uri), JsonUtils.toJson(parameters), StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}
}
