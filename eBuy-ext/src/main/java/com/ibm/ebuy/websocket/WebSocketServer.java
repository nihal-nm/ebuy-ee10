package com.ibm.ebuy.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import jakarta.websocket.EndpointConfig;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class WebSocketServer {

	private static Set<Session> clients = Collections
			.synchronizedSet(new HashSet<Session>());

	Session currentSession = null;

	@OnOpen
	public void onOpen(Session session, EndpointConfig ec) {
		// Add session to the connected sessions set
		currentSession = session;
		clients.add(currentSession);
	}

	@OnClose
	public void onClose(Session session, EndpointConfig ec) {
		// Remove session from the connected sessions set
		// currentSession = session;

		clients.remove(currentSession);
	}

	@OnMessage
	public void onMessage(String message) throws IOException {

		synchronized (clients) {
			// Iterate over the connected sessions
			// and broadcast the received message
			for (Session client : clients) {
				if (!client.equals(currentSession)) {
					client.getBasicRemote().sendText(message);
				}
			}
		}

	}

}
