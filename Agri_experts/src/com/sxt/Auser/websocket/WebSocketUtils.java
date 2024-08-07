/**
 * 
 */
package com.sxt.Auser.websocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.Session;

/**
 * @ClassName: WebSocketUtils
 * @Description: TODO
 * @author Simple 
 * @date 2017-6-15 下午2:26:09
 *
 */
public class WebSocketUtils {

	public static Map<String, Session> clients=new ConcurrentHashMap<String, Session>();
	public static void put(String key,Session session){
		clients.put(key, session);
	}
	public static Session get(String key){
		return clients.get(key);
	}
	 public static void remove(String key){
		 clients.remove(key);
	}
	 public static boolean hasConnection(String key) {
		 return clients.containsKey(key);
	}
}
