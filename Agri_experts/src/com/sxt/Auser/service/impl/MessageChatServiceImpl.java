package com.sxt.Auser.service.impl;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxt.Auser.domain.ChatFriend;
import com.sxt.Auser.domain.Messagechat;
import com.sxt.Auser.mapper.MessagechatMapper;
import com.sxt.Auser.mapper.UserchatMapper;
import com.sxt.Auser.service.MessageChatService;


@Service
public class MessageChatServiceImpl implements MessageChatService {

	
	@Autowired
	private MessagechatMapper messagechatMapper;
	
	@Autowired
	private UserchatMapper userChatMapper;

	@Override
	public ArrayList<ChatFriend> queryGetChatFriends(int userId) {
		// TODO Auto-generated method stub
		ArrayList<ChatFriend> chatFriends = this.userChatMapper.getChatFriendInfo(userId);
		for(ChatFriend chatFriend : chatFriends){
			Messagechat messagechat = new Messagechat();
			messagechat.setMessagereciver(userId);//设置接收人
			int setMessagereciver = userId;
			System.out.println("chatFriend.getUserid"+chatFriend.getUserid());
			messagechat.setMessagesender(chatFriend.getUserid());//设置发送人
			int setMessagesender = chatFriend.getUserid();
			String messageInfo = (String)this.messagechatMapper.getMessage(setMessagereciver,setMessagesender);//得到信息
			//得到在线人数
			int count=(Integer)this.messagechatMapper.getMessageNotReadCount(setMessagereciver,setMessagesender);
			chatFriend.setMessageInfo(messageInfo);//设置发送
			chatFriend.setCount(count);

		}
		return chatFriends;
	}

	@Override
	public void updateMessages(Messagechat messagechat) {
		// TODO Auto-generated method stub
		this.messagechatMapper.updateMessages(messagechat);
	}

	@Override
	public void saveMessage(Messagechat messagechat) {
		// TODO Auto-generated method stub
		this.messagechatMapper.saveMessage(messagechat);
	}

	@Override
	public ArrayList<Messagechat> getMessages(Messagechat messagechat) {
		// TODO Auto-generated method stub
		return this.messagechatMapper.getMessages(messagechat);
	}
	
	
	
	
}
