package com.sxt.Auser.service;

import java.util.List;

import com.sxt.Auser.domain.Message;

public interface MessageService {

	//查询数据库记录
	List<Message> queryAllChat(String send, String receive);

	//添加数据
	void insertMessage(Message message);

	
}
