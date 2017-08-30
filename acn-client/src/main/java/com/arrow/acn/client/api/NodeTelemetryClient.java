package com.arrow.acn.client.api;

import java.net.URI;

import com.arrow.acn.client.AcnClientException;
import com.arrow.acn.client.websocket.WebSocketSubscription;
import com.arrow.acs.client.api.ApiConfig;

public class NodeTelemetryClient extends WebSocketClientAbstract {

	private static final String NODE_TELEMETRY_ENDPOINT_URL = API_BASE + "/nodes/{hid}/telemetry/{telemetryName}";

	NodeTelemetryClient(ApiConfig apiConfig) {
		super(apiConfig);
	}

	public WebSocketSubscription subscribe(String nodeHid, String telemetryName) {
		String method = "subscribe";
		try {
			URI uri = buildWebSocketUri(
			        NODE_TELEMETRY_ENDPOINT_URL.replace("{hid}", nodeHid).replace("{telemetryName}", telemetryName));
			return subscribe(uri);
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}
}
