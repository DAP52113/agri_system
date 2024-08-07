package com.sxt.Auser.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sxt.Auser.domain.ChatFriend;
import com.sxt.Auser.domain.Messagechat;
import com.sxt.Auser.domain.Userchat;
import com.sxt.Auser.service.FriendService;
import com.sxt.Auser.service.MessageChatService;
import com.sxt.Auser.service.UserChatService;
import com.sxt.Auser.websocket.WebSocketUtils;


@Controller
@RequestMapping("newuserchat")
public class UserChatController {

	@Autowired
	private UserChatService userChatService;
	
	@Autowired
	private FriendService friendService;
	
	@Autowired
	private MessageChatService messageChatService;
	
	@RequestMapping("loginchat")
	public String loginchat(@RequestParam("username")String username,@RequestParam("userintroduce")String userintroduce){
		
//		Userchat userchat = new Userchat();
//		userchat.setUsername(username);
//		userchat.setUserintroduce(userintroduce);
//		userchat.setUserphoto("001.jpg");
//		//添加用户到聊天数据表
//		this.userChatService.addUserChatInfo(userchat);
		
		Userchat querryUserchat = this.userChatService.queryUserByUserName(username);
		if(querryUserchat != null){
			System.out.println("querryUserchat.getUserid()"+querryUserchat.getUserid());
			return "redirect:list.action?userId="+querryUserchat.getUserid();
		}else{
			return "chatview/chat_index";
		}
	}
	
	@RequestMapping("list")
	public String friendList(int userId,Model model,HttpServletRequest request){
		//查询数据
		Userchat userchat = this.userChatService.queryById(userId);
		
		//查询好友信息
		ArrayList<Userchat> friendlist = this.friendService.queryFriendList(userId);
		System.out.println("用户好友信息"+friendlist.toArray());
		//得到好友信息
		ArrayList<ChatFriend> chatFriends = this.messageChatService.queryGetChatFriends(userId);
		model.addAttribute("friendlist", friendlist);
		model.addAttribute("chatFriends", chatFriends);
		model.addAttribute("userchat",userchat);
		
		return "chatview/main";
	}
	
	@RequestMapping(value = "online" ,method = RequestMethod.POST)
	public  @ResponseBody void  getOnline(Integer  userId) throws IOException{
		System.out.println("错误地online方的"+userId);
		ArrayList<Userchat> users =  this.friendService.getFriendList(userId);
		if(users == null){
			System.out.println("空指针");
		}else{
			for(Userchat userchat : users){
				if(WebSocketUtils.hasConnection(userchat.getUserid()+"")){
					Messagechat messagechat = new Messagechat();
					messagechat.setMessagetype("2");
					messagechat.setMessageinfo(userchat.getUserid()+"");
					Gson gson = new Gson();//新建Json对象
					WebSocketUtils.get(userId+"").getBasicRemote().sendText(gson.toJson(messagechat));
				}
			}
		}

	}
	
	@RequestMapping("noticeFriends")
	@ResponseBody
	public void noticeFriends(Integer  userId) throws IOException{
		System.out.println("错误地方的"+userId);
		ArrayList<Userchat> users =  this.friendService.getFriendList(userId);
		for(Userchat userchat : users){
			if(WebSocketUtils.hasConnection(userchat.getUserid()+"")){
				Messagechat messagechat = new Messagechat();
				messagechat.setMessagetype("2");
				messagechat.setMessageinfo(userId+"");
				Gson gson = new Gson();//新建Json对象
				WebSocketUtils.get(userchat.getUserid()+"").getBasicRemote().sendText(gson.toJson(messagechat));
			}
		}
	}
	
	@RequestMapping("outline")
	@ResponseBody
	public void noticeFriendsOutLine(Integer   userId) throws IOException{
		
		System.out.println(WebSocketUtils.clients.size());
		System.out.println("22退出id"+userId);
	
			ArrayList<Userchat> users =  this.friendService.getFriendList(userId);
			for(Userchat userchat : users){
				if(WebSocketUtils.hasConnection(userchat.getUserid()+"")){
					Messagechat messagechat = new Messagechat();
					messagechat.setMessagetype("3");
					messagechat.setMessageinfo(userId+"");
					Gson gson = new Gson();//新建Json对象
					WebSocketUtils.get(userchat.getUserid()+"").getBasicRemote().sendText(gson.toJson(messagechat));
				}
			}
		

	}
	@RequestMapping("updateMessage")
	@ResponseBody
	public void updateMessages(Messagechat messagechat){
		System.out.print(" "+messagechat.getMessagesender()+"re"+messagechat.getMessagereciver());
		
		this.messageChatService.updateMessages(messagechat);
	}
	
	@RequestMapping("sendMessage")
	public void sendMessage(Messagechat messagechat,HttpServletRequest request,HttpServletResponse response) throws IOException{
		messagechat.setMessageread(0+"");
		System.out.println("saveMessage中messagechat"+messagechat.getMessagesender());
		this.messageChatService.saveMessage(messagechat);
		if(WebSocketUtils.hasConnection(messagechat.getMessagereciver()+"")){
			Gson gson=new Gson();
			WebSocketUtils.get(messagechat.getMessagereciver()+"").getBasicRemote().sendText(gson.toJson(messagechat));
		}
	}
	
	@RequestMapping("messages")
	public String getMessages(Messagechat messagechat,Model model){
		Userchat senderUser = this.userChatService.getUserById(messagechat.getMessagesender());
		Userchat reciverUser = this.userChatService.getUserById(messagechat.getMessagereciver());
		ArrayList<Messagechat> messages=this.messageChatService.getMessages(messagechat);
		model.addAttribute("messages", messages);
		model.addAttribute("senderUser",senderUser);
		model.addAttribute("reciverUser",reciverUser);
		return "chatview/chat";
	}
	
	
	
}
