package com.sxt.Auser.service;

import java.util.ArrayList;

import com.sxt.Auser.domain.ChatFriend;
import com.sxt.Auser.domain.Messagechat;

public interface MessageChatService {

	ArrayList<ChatFriend> queryGetChatFriends(int userId);

	//更新信息表
	void updateMessages(Messagechat messagechat);

	//保存信息表
	void saveMessage(Messagechat messagechat);

	ArrayList<Messagechat> getMessages(Messagechat messagechat);

}
