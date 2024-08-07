package com.sxt.Auser.websocket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.springframework.web.socket.server.standard.SpringConfigurator;

@ServerEndpoint(value = "/webServer/{key}", configurator =SpringConfigurator.class)
public class MyWebSocket {

	
	@OnOpen
	public void onOpen(Session session,@PathParam("key") String key) {
	if(!WebSocketUtils.hasConnection(key)){
		System.out.println("连接ID"+key);
		WebSocketUtils.clients.put(key, session); // 加入set中
	}
		
	}
	@OnClose
	public void onClose(@PathParam("key") String key) throws IOException {
		WebSocketUtils.clients.remove(key);
	}
	@OnMessage
	public void onMessage(String message, Session session) {
		
	}
	@OnError
	public void onError(@PathParam("key") String key, Throwable error) {
		WebSocketUtils.clients.remove(key);
		error.printStackTrace();
	}

	/*public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
		//this.session.getAsyncRemote().sendText(message);
	}*/
}
