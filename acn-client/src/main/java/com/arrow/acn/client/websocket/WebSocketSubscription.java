package com.arrow.acn.client.websocket;

import java.net.URI;

public class WebSocketSubscription {

	private WebSocketEndpoint webSocket;
	private URI uri;

	public WebSocketSubscription(URI uri, WebSocketEndpoint webSocket) {
		this.uri = uri;
		this.webSocket = webSocket;
	}

	public void close() throws Exception {
		webSocket.close();
	}

	public void close(int closeCode, String reasonPhrase) throws Exception {
		webSocket.close(closeCode, reasonPhrase);
	}

	public boolean isOpen() {
		return webSocket.isConnected();
	}

	public URI getURI() {
		return uri;
	}
}
