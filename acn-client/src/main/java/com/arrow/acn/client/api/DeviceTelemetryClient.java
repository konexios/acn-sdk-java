package com.arrow.acn.client.api;

import java.net.URI;

import com.arrow.acn.client.AcnClientException;
import com.arrow.acn.client.websocket.WebSocketSubscription;
import com.arrow.acs.client.api.ApiConfig;

public class DeviceTelemetryClient extends WebSocketClientAbstract {

	private static final String DEVICE_TELEMETRY_ENDPOINT_URL = API_BASE + "/devices/{hid}/telemetry/{telemetryName}";

	DeviceTelemetryClient(ApiConfig apiConfig) {
		super(apiConfig);
	}

	public WebSocketSubscription subscribe(String deviceHid, String telemetryName) {
		String method = "subscribe";
		try {
			URI uri = buildWebSocketUri(DEVICE_TELEMETRY_ENDPOINT_URL.replace("{hid}", deviceHid)
			        .replace("{telemetryName}", telemetryName));
			return subscribe(uri);
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}
}
