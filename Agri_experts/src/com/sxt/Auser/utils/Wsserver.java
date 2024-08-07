package com.sxt.Auser.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.sxt.sys.utils.WebUtils;
import com.sxt.sys.vo.Msg;



@ServerEndpoint(value = "/chatroom")
public class Wsserver {


	
	private static Set<Session> set = new HashSet<Session>();
	private static List<String> userlist = new ArrayList<String>();//存放登录用
	private static Map<Session,String> m = new HashMap<Session, String>();
	
	private static Gson g = new Gson();
	//建立连接
	@OnOpen
	public void OnOpen(Session session) throws IOException{
		System.out.println("建立成功！");
		//每次建立连接都要进行session存储
		String queryString = session.getQueryString();
		queryString = URLDecoder.decode(queryString,"utf-8");
		System.out.println(queryString.substring(4));
		userlist.add(queryString.substring(4));
		set.add(session);
		m.put(session,queryString.substring(4));
		//创建系统消息
		Msg msg = new Msg();
		msg.setMsgType("s");
		msg.setMsgOriginator("system");
		msg.setMsgInfo("欢迎"+queryString.substring(4)+"上线!!!!");
		msg.setMsgDate(new Date());
		msg.setUserlists(userlist);
		String jsonmsg = g.toJson(msg);
		System.out.println(jsonmsg);
		boardCast(set, jsonmsg);
	}
	
	@OnClose
	public void OnClose(Session session) throws IOException{
		String queryString = session.getQueryString();
		queryString = URLDecoder.decode(queryString,"utf-8");
		Msg messMsg  = new Msg();
		messMsg.setMsgType("s");
		messMsg.setMsgOriginator("system");
		messMsg.setMsgDate(new Date());
		messMsg.setMsgInfo(queryString.substring(4)+"下线了!!!!");
		String jsonmsg = g.toJson(messMsg);
		System.out.println(jsonmsg);
		boardCast(set, jsonmsg);
		set.remove(session);//移除session
		userlist.remove(queryString.substring(4));
		m.remove(session);//删除私聊信息
		System.out.println("连接已关闭");
		
	}
	
	@OnMessage
	public void OnMessage(String message,Session session) throws IOException{
		System.out.println("客户端发送的信息："+message);
		String queryString = session.getQueryString();
		queryString = URLDecoder.decode(queryString,"utf-8");
		Msg messageMsg = new Msg();
		messageMsg.setMsgType("e");
		messageMsg.setMsgOriginator(queryString.substring(4));
		//对客户端发送的信息进行处理
		if(message.startsWith("@") && message.contains(":")){
			String recipentString = message.substring(message.indexOf("@")+1, message.indexOf(":"));
			System.out.println("接收人为"+recipentString);
			messageMsg.setMsgRecipient(recipentString);
			messageMsg.setMsgDateStr(new Date());
			if(m.containsValue(recipentString)){
				for(Entry<Session, String> e : m.entrySet()){
					if(recipentString.equals(e.getValue())){
						Session recipentSession = e.getKey();//得到session
						message =  message.substring(message.indexOf(":")+1);
						System.out.println("截取后的信息为"+message);
						messageMsg.setMsgInfo(message);
						HashSet<Session> hsHashSet = new HashSet<Session>();
						hsHashSet.add(recipentSession);
						hsHashSet.add(session);
						this.boardCast(hsHashSet, g.toJson(messageMsg));
					}
				}
			}
			
		}else{
			messageMsg.setMsgInfo(message);
			messageMsg.setMsgDateStr(new Date());
			this.boardCast(m.keySet(), g.toJson(messageMsg));
		}

	}
	

	@javax.websocket.OnError
	public void OnError(Throwable t,Session session) throws Throwable{
		System.out.println("连接异常");
	
		
	}
	
	//发送信息方法
	private static void boardCast(Set<Session> set, String message) throws IOException{
		//实现信息反馈到前台
				for(Session session2 : set){
					//遍历信息
					session2.getBasicRemote().sendText(message);
				}
	}
	
	
	
}
