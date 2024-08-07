package com.sxt.Auser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxt.Auser.domain.Message;
import com.sxt.Auser.mapper.MessageMapper;
import com.sxt.Auser.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageMapper messageMapper;
	
	@Override
	public List<Message> queryAllChat(String send, String receive) {
		// TODO Auto-generated method stub
		return messageMapper.queryAllChat(send,receive);
	//
	}

	@Override
	public void insertMessage(Message message) {
		// TODO Auto-generated method stub
		this.messageMapper.insertSelective(message);
	}

}
