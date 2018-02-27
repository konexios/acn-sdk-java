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
