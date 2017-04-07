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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import com.arrow.acn.client.AcnClientException;
import com.arrow.acn.client.model.AuditLogModel;
import com.arrow.acn.client.model.DeviceEventModel;
import com.arrow.acn.client.model.DeviceModel;
import com.arrow.acn.client.model.DeviceRegistrationModel;
import com.arrow.acn.client.search.DeviceSearchCriteria;
import com.arrow.acn.client.search.EventsSearchCriteria;
import com.arrow.acn.client.search.LogsSearchCriteria;
import com.arrow.acs.JsonUtils;
import com.arrow.acs.client.api.ApiConfig;
import com.arrow.acs.client.model.ErrorModel;
import com.arrow.acs.client.model.ExternalHidModel;
import com.arrow.acs.client.model.HidModel;
import com.arrow.acs.client.model.PagingResultModel;
import com.fasterxml.jackson.core.type.TypeReference;

public class DeviceApi extends ApiAbstract {
	private static final String DEVICES_BASE_URL = API_BASE + "/devices";
	private static final String CREATE_OR_UPDATE_URL = DEVICES_BASE_URL;
	private static final String FIND_ALL_BY_URL = DEVICES_BASE_URL;
	private static final String SPECIFIC_DEVICE_URL = DEVICES_BASE_URL + "/{hid}";
	private static final String FIND_BY_HID_URL = SPECIFIC_DEVICE_URL;
	private static final String UPDATE_EXISTING_URL = SPECIFIC_DEVICE_URL;
	private static final String SPECIFIC_EVENTS_URL = SPECIFIC_DEVICE_URL + "/events";
	private static final String SPECIFIC_LOGS_URL = SPECIFIC_DEVICE_URL + "/logs";
	private static final String SEND_ERROR_URL = SPECIFIC_DEVICE_URL + "/errors";

	private static final TypeReference<PagingResultModel<DeviceModel>> DEVICE_MODEL_TYPE_REF = new TypeReference<PagingResultModel<DeviceModel>>() {
	};
	private static final TypeReference<PagingResultModel<DeviceEventModel>> DEVICE_EVENT_MODEL_TYPE_REF = new TypeReference<PagingResultModel<DeviceEventModel>>() {
	};
	private static final TypeReference<PagingResultModel<AuditLogModel>> AUDIT_LOG_MODEL_TYPE_REF = new TypeReference<PagingResultModel<AuditLogModel>>() {
	};
	private static final Pattern PATTERN = Pattern.compile("{hid}", Pattern.LITERAL);

	DeviceApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	/**
	 * Sends GET request to obtain parameters of devices corresponding
	 * {@code criteria}
	 *
	 * @param criteria
	 *            {@link DeviceSearchCriteria} representing search filter
	 *            parameters
	 *
	 * @return subset of {@link DeviceModel} containing device parameters.
	 *         <b>Note:</b> resulting subset may contain not all devices
	 *         corresponding to search parameters because it cannot exceed page
	 *         size passed in {@code criteria}
	 *
	 * @throws AcnClientException
	 *             if request failed
	 */
	public PagingResultModel<DeviceModel> findAllBy(DeviceSearchCriteria criteria) {
		String method = "findAllBy";
		try {
			URI uri = buildUri(FIND_ALL_BY_URL + criteria);
			PagingResultModel<DeviceModel> result = execute(new HttpGet(uri), DEVICE_MODEL_TYPE_REF);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	/**
	 * Sends POST request to create new or update existing device according to
	 * {@code model} passed. <b>Note:</b> device uniqueness is defined by
	 * {@code uid} and {@code applicationId} passed in {@code model}
	 *
	 * @param model
	 *            {@link DeviceRegistrationModel} representing parameters of
	 *            device to be created/updated
	 *
	 * @return {@link ExternalHidModel} containing external {@code hid} of
	 *         device created/updated
	 *
	 * @throws AcnClientException
	 *             if request failed
	 */
	public ExternalHidModel createOrUpdate(DeviceRegistrationModel model) {
		String method = "createOrUpdate";
		try {
			URI uri = buildUri(CREATE_OR_UPDATE_URL);
			ExternalHidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), ExternalHidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	/**
	 * Sends GET request to obtain parameters of device specified by its
	 * {@code hid}
	 *
	 * @param hid
	 *            {@link String} representing specific device
	 *
	 * @return {@link DeviceModel} containing device parameters
	 *
	 * @throws AcnClientException
	 *             if request failed
	 */
	public DeviceModel findByHid(String hid) {
		String method = "findByHid";
		try {
			URI uri = buildUri(PATTERN.matcher(FIND_BY_HID_URL).replaceAll(Matcher.quoteReplacement(hid)));
			DeviceModel result = execute(new HttpGet(uri), DeviceModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	/**
	 * Sends PUT request to update specific existing device according to
	 * {@code model} passed
	 *
	 * @param hid
	 *            {@link String} representing {@code hid} of device to be
	 *            updated
	 * @param model
	 *            {@link DeviceRegistrationModel} representing device parameters
	 *            to be updated
	 *
	 * @return {@link HidModel} containing {@code hid} of device updated
	 *
	 * @throws AcnClientException
	 *             if request failed
	 */
	public HidModel updateExistingDevice(String hid, DeviceRegistrationModel model) {
		String method = "putFailed";
		try {
			URI uri = buildUri(PATTERN.matcher(UPDATE_EXISTING_URL).replaceAll(Matcher.quoteReplacement(hid)));
			HidModel result = execute(new HttpPut(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	/**
	 * Sends GET request to obtain events related to specific device and
	 * corresponding {@code criteria}
	 *
	 * @param hid
	 *            {@link String} representing device {@code hid}
	 * @param criteria
	 *            {@link EventsSearchCriteria} representing search filter
	 *            parameters.
	 *
	 * @return subset of {@link DeviceEventModel} containing event parameters.
	 *         <b>Note:</b> resulting subset may contain not all events
	 *         corresponding to search parameters because it cannot exceed page
	 *         size passed in{@code
	 * criteria}
	 *
	 * @throws AcnClientException
	 *             if request failed
	 */
	public PagingResultModel<DeviceEventModel> listHistoricalDeviceEvents(String hid, EventsSearchCriteria criteria) {
		String method = "listHistoricalDeviceEvents";
		try {
			URI uri = buildUri(
			        PATTERN.matcher(SPECIFIC_EVENTS_URL).replaceAll(Matcher.quoteReplacement(hid)) + criteria);
			PagingResultModel<DeviceEventModel> result = execute(new HttpGet(uri), criteria,
			        DEVICE_EVENT_MODEL_TYPE_REF);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	/**
	 * Sends GET request to obtain logs related to specific device and
	 * corresponding {@code criteria}
	 *
	 * @param hid
	 *            {@link String} representing device {@code hid}
	 * @param criteria
	 *            {@link LogsSearchCriteria} representing search filter
	 *            parameters.
	 *
	 * @return subset of {@link AuditLogModel} containing event parameters.
	 *         <b>Note:</b> resulting subset may contain not all logs
	 *         corresponding to search parameters because it cannot exceed page
	 *         size passed in {@code criteria}
	 *
	 * @throws AcnClientException
	 *             if request failed
	 */
	public PagingResultModel<AuditLogModel> listDeviceAuditLogs(String hid, LogsSearchCriteria criteria) {
		String method = "listDeviceAuditLogs";
		try {
			URI uri = buildUri(PATTERN.matcher(SPECIFIC_LOGS_URL).replaceAll(Matcher.quoteReplacement(hid)) + criteria);
			PagingResultModel<AuditLogModel> result = execute(new HttpGet(uri), criteria, AUDIT_LOG_MODEL_TYPE_REF);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	/**
	 * Sends POST request to submit an error
	 *
	 * @param model
	 *            {@link ErrorModel} content of the error (code and message)
	 *
	 * @return {@link HidModel} containing external {@code hid} of the created
	 *         audit log
	 *
	 * @throws AcnClientException
	 *             if request failed
	 */
	public HidModel sendError(String hid, ErrorModel model) {
		String method = "sendError";
		try {
			URI uri = buildUri(SEND_ERROR_URL.replace("{hid}", hid));
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}
}
