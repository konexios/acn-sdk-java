package com.arrow.acn.client.api;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.Validate;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import com.arrow.acn.client.websocket.MessageListener;
import com.arrow.acn.client.websocket.WebSocketSubscription;
import com.arrow.acn.client.websocket.WebSocketEndpoint;
import com.arrow.acs.ApiHeaders;
import com.arrow.acs.client.AcsClientException;
import com.arrow.acs.client.api.ApiConfig;

public abstract class WebSocketClientAbstract extends ApiAbstract {

	private static final long DEFAULT_CONNECTION_TIMEOUT_MILLISECONDS = 5000;

	private WebSocketClient client = new WebSocketClient();
	private long connectionTimeout = DEFAULT_CONNECTION_TIMEOUT_MILLISECONDS;
	private MessageListener messageListener = new MessageListener() {
	};

	WebSocketClientAbstract(ApiConfig apiConfig) {
		super(apiConfig);
	}

	public void setConnectionTimeout(long ms) {
		connectionTimeout = ms;
	}

	public void setMaxIdleTimeout(long ms) {
		client.setMaxIdleTimeout(ms);
	}

	public void setMessageListener(MessageListener messageListener) {
		Validate.notNull(messageListener, "messageListener is null");
		this.messageListener = messageListener;
	}

	public void start() throws Exception {
		if (!client.isRunning()) {
			String method = "start";
			logDebug(method, "...");
			client.start();
		}
	}

	public void stop() throws Exception {
		String method = "stop";
		logDebug(method, "...");
		client.stop();
	}

	protected WebSocketSubscription subscribe(URI uri) throws Exception {
		String method = "subscribe";
		logDebug(method, "...");
		try {
			start();
			ClientUpgradeRequest request = new ClientUpgradeRequest();
			addHeaders(request);

			WebSocketEndpoint webSocket = new WebSocketEndpoint(messageListener);
			logDebug(method, "connecting to %s", uri);
			client.connect(webSocket, uri, request).get(connectionTimeout, TimeUnit.MILLISECONDS);
			logDebug(method, "connected.");
			return new WebSocketSubscription(uri, webSocket);
		} catch (Exception e) {
			throw new AcsClientException(e);
		}
	}

	public void close(WebSocketEndpoint webSocket) {
		Validate.notNull(webSocket, "webSocket is null");
		try {
			webSocket.close();
		} catch (Exception e) {
			throw new AcsClientException(e);
		}
	}

	private ClientUpgradeRequest addHeaders(ClientUpgradeRequest request) {
		Validate.notNull(request, "request is null");
		request.setHeader(ApiHeaders.X_ARROW_APIKEY, getApiConfig().getApiKey());
		return request;
	}
}
