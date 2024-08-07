package com.sxt.Auser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxt.Auser.domain.Userchat;
import com.sxt.Auser.mapper.UserchatMapper;
import com.sxt.Auser.service.UserChatService;


@Service
public class UserChatServiceImpl implements UserChatService {

	@Autowired
	private UserchatMapper userChatMapper;
	
	@Override
	public void addUserChatInfo(Userchat userchat) {
		// TODO Auto-generated method stub
		this.userChatMapper.insertSelective(userchat);
	}

	@Override
	public Userchat queryUserByUserName(String username) {
		// TODO Auto-generated method stub
		return this.userChatMapper.queryUserByUserName(username);
	}

	@Override
	public Userchat queryById(int userId) {
		// TODO Auto-generated method stub
		return this.userChatMapper.queryById(userId);
	}

	@Override
	public Userchat getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return this.userChatMapper.getUserById(userId);
	}


	
	
	
	
}
