package com.arrow.acn.client.api;

import java.util.Collections;
import java.util.Map;

import com.arrow.acn.client.AcnClientException;
import com.arrow.acs.client.api.ApiConfig;

public class CoreEventMqttApi extends MqttApiAbstract {

	private static final String PUT_FAILED_METHOD = "PutFailed";
	
	CoreEventMqttApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	public void putFailed(String requestId, String hid, String error) {
		String method = "putFailed";
		try {
			/* example from REST API
			URI uri = buildUri(CoreEventApi.PUT_FAILED_URL.replace("{hid}", hid));
			StatusModel result = execute(new HttpPut(uri), JsonUtils.toJson(Collections.singletonMap("error", error)),
			        StatusModel.class);
			log(method, result);
			*/
			
			String url = CoreEventApi.PUT_FAILED_URL.replace("{hid}", hid);
			String jsonBody = "{param:value}";
			
			send(requestId, PUT_FAILED_METHOD, url, jsonBody);
			
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public void putReceived(String hid) {
		String method = "putReceived";
		try {
		    /* example from REST API
			URI uri = buildUri(CoreEventApi.PUT_RECEIVED_URL.replace("{hid}", hid));
			StatusModel result = execute(new HttpPut(uri), StatusModel.class);
			log(method, result);
			*/
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public void putSucceeded(String hid) {
		putSucceeded(hid, Collections.emptyMap());
	}

	public void putSucceeded(String hid, Map<String, String> parameters) {
		String method = "putSucceeded";
		try {
		    /* example from REST API
			URI uri = buildUri(CoreEventApi.PUT_SUCCEEDED_URL.replace("{hid}", hid));
			StatusModel result = execute(new HttpPut(uri), StatusModel.class);
			log(method, result);
		     */
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}
	
}
