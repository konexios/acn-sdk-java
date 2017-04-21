package com.arrow.acn.client.api;

import java.net.URI;
import java.net.URLEncoder;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import com.arrow.acn.client.AcnClientException;
import com.arrow.acn.client.model.DeviceStateModel;
import com.arrow.acn.client.model.DeviceStateRequestModel;
import com.arrow.acn.client.model.DeviceStateUpdateModel;
import com.arrow.acs.JsonUtils;
import com.arrow.acs.client.api.ApiConfig;
import com.arrow.acs.client.model.HidModel;
import com.arrow.acs.client.model.StatusModel;

public class DeviceStateApi extends ApiAbstract {
	private static final String DEVICES_STATE_BASE_URL = API_BASE + "/devices/%s/state";
	private static final String FIND_URL = DEVICES_STATE_BASE_URL;
	private static final String CREATE_STATE_REQUEST_URL = DEVICES_STATE_BASE_URL + "/request";
	private static final String CREATE_STATE_UPDATE_URL = DEVICES_STATE_BASE_URL + "/update";
	private static final String TRANS_BASE_URL = DEVICES_STATE_BASE_URL + "/trans/%s/";
	private static final String TRANS_RECEIVED_URL = TRANS_BASE_URL + "received";
	private static final String TRANS_COMPLETE_URL = TRANS_BASE_URL + "complete";
	private static final String TRANS_ERROR_URL = TRANS_BASE_URL + "error";

	DeviceStateApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	public DeviceStateModel find(String deviceHid) {
		String method = "find";
		try {
			URI uri = buildUri(String.format(FIND_URL, deviceHid));
			DeviceStateModel result = execute(new HttpGet(uri), DeviceStateModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public HidModel createStateRequest(String deviceHid, DeviceStateRequestModel model) {
		String method = "createStateRequest";
		try {
			URI uri = buildUri(String.format(CREATE_STATE_REQUEST_URL, deviceHid));
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public HidModel createStateUpdate(String deviceHid, DeviceStateUpdateModel model) {
		String method = "createStateUpdate";
		try {
			URI uri = buildUri(String.format(CREATE_STATE_UPDATE_URL, deviceHid));
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public StatusModel transReceived(String deviceHid, String transHid) {
		String method = "transReceived";
		try {
			URI uri = buildUri(String.format(TRANS_RECEIVED_URL, deviceHid, transHid));
			StatusModel result = execute(new HttpPut(uri), StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public StatusModel transComplete(String deviceHid, String transHid) {
		String method = "transComplete";
		try {
			URI uri = buildUri(String.format(TRANS_COMPLETE_URL, deviceHid, transHid));
			StatusModel result = execute(new HttpPut(uri), StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public StatusModel transError(String deviceHid, String transHid, String error) {
		String method = "transError";
		try {
			URI uri = buildUri(String.format(TRANS_ERROR_URL, deviceHid, transHid));
			StatusModel result = execute(new HttpPut(uri + "?error=" + URLEncoder.encode(error, "UTF-8")),
			        StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}
}
