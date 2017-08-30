package com.arrow.acn.client.websocket;

public interface MessageListener {

	default void onMessage(String message) {
	}

	default void onMessage(byte[] payload, int offset, int len) {
	}
}
