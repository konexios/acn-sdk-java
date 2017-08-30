package com.arrow.acn.client.websocket;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import com.arrow.acs.Loggable;

@WebSocket
public class WebSocketEndpoint extends Loggable implements AutoCloseable {

	private volatile Session session;
	private MessageListener listener;

	public WebSocketEndpoint() {
	}

	public WebSocketEndpoint(MessageListener listener) {
		this.listener = listener;
	}

	public Session getSession() {
		return session;
	}

	public boolean isConnected() {
		Session sess = this.session;
		return (sess != null) && (sess.isOpen());
	}

	public void setMessageListener(MessageListener listener) {
		this.listener = listener;
	}

	@OnWebSocketConnect
	public void onConnect(Session session) {
		String method = "onConnect";
		logDebug(method, "...");
		this.session = session;
		logDebug(method, "connected session=%s", session);
	}

	@OnWebSocketMessage
	public void onMessage(String msg) {
		String method = "onMessage";
		logDebug(method, "...");
		logDebug(method, "message=%s", msg);
		if (listener != null) {
			listener.onMessage(msg);
		}
	}

	@OnWebSocketMessage
	public void onMessage(byte[] payload, int offset, int len) {
		String method = "onMessage";
		logDebug(method, "...");
		logDebug(method, "payload=%s", payload);
		if (listener != null) {
			listener.onMessage(payload, offset, len);
		}
	}

	@OnWebSocketClose
	public void onClose(Session session, int statusCode, String reason) {
		String method = "onClose";
		logDebug(method, "...");
		logDebug(method, "statusCode: %s, reason: %s, session=%s", statusCode, reason, session);
		this.session = null;
		logDebug(method, "closed.");
	}

	@OnWebSocketError
	public void onError(Throwable throwable) throws Exception {
		String method = "onError";
		logDebug(method, "...");
		logError(method, throwable);
		Session sess = this.session;
		if (sess != null && sess.isOpen()) {
			session.disconnect();
		}
	}

	public void close() throws Exception {
		String method = "close";
		logDebug(method, "...");
		Session sess = this.session;
		if (sess != null && sess.isOpen()) {
			sess.close();
		}
	}

	public void close(int statusCode, String reason) throws Exception {
		String method = "close";
		logDebug(method, "...");
		logDebug(method, "statusCode: %s, reason: %s", statusCode, reason);
		Session sess = this.session;
		if (sess != null && sess.isOpen()) {
			sess.close(statusCode, reason);
		}
	}
}
