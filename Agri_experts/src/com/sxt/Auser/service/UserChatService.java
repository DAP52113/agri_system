package com.sxt.Auser.service;

import com.sxt.Auser.domain.Userchat;

public interface UserChatService {

	//添加用户到
	void addUserChatInfo(Userchat userchat);

	//查询用户
	Userchat queryUserByUserName(String username);

	//查询用户信息
	Userchat queryById(int userId);

	Userchat getUserById(Integer messagesender);

}
