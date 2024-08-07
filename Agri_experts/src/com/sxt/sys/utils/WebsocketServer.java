package com.sxt.sys.utils;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang.ArrayUtils;

import cn.hutool.core.util.ArrayUtil;

import com.google.gson.Gson;
import com.sxt.sys.vo.Msg;


//用户->专家在线聊天控制器
@ServerEndpoint(value = "/chatroomtouser")
public class WebsocketServer {

	private static Set<Session> set = new HashSet<Session>();//存放session用
	private static List<String> userlist = new ArrayList<String>();//存放登录用
	private static Map<Session,String> m = new HashMap<Session, String>();
	
	private static Gson g = new Gson();
	
	
	@OnOpen
	public void OnOpen(Session session) throws Exception{
		System.out.println("建立成功！");
		//每次建立连接都要进行session存储

		
		String queryString = session.getQueryString();//获取session
		queryString = URLDecoder.decode(queryString,"utf-8");
		System.out.println("后台获取到的"+queryString);
		userlist.add(queryString.substring(4));//将其session数值存入到userlist中
		
		
		set.add(session);//set容器存储session
		m.put(session,queryString.substring(4));//将其存入map表中，键为seesion，值为数值
		//创建系统消息
		Msg msg = new Msg();
		msg.setMsgType("s");//设置系统消息类型
		msg.setMsgOriginator("system");//设置发起人
		msg.setMsgInfo("欢迎"+queryString.substring(4)+"上线!!!!");//消息内容打印
		msg.setMsgDate(new Date());//设置消息时间
		msg.setUserlists(userlist);//消息的用户列表存储
		String jsonmsg = g.toJson(msg);//转换成seesion
		System.out.println(jsonmsg);
		this.boardCast(set, jsonmsg);
	}
	
	
	@OnClose
	public void OnClose(Session session) throws Exception{
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
	
	//发送信息
	@OnMessage
	public void OnMessage(String message,Session session) throws Exception{
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
	
	//发送信息的封装方法
	private static void boardCast(Set<Session> set,String message)throws Exception{
		//实现信息反馈到前台
		for(Session session  : set){
			session.getBasicRemote().sendText(message);
		}

	}
	
//	@OnMessage
//	public void OnMessage(byte[] inputStream,Session session,boolean  last) throws Exception{
//		byte[] b= null;
//		if(!last){
//			b = ArrayUtils.addAll(b, inputStream);
//			
//		}else{//传输完成
//			b = ArrayUtils.addAll(b, inputStream);
//			ByteBuffer bb = ByteBuffer.wrap(b);//转换为字节
//			this.boardCast(m.keySet(), bb);
//			
//		}
//	}
	
	
	//发送信息的封装方法
//	private static void boardCast(Set<Session> set,ByteBuffer msg)throws Exception{
//		//实现信息反馈到前台
//		for(Session session  : set){
//			session.getBasicRemote().sendBinary(msg);
//		}
//
//	}
	
	
}
