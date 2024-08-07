package com.sxt.Auser.service;

import java.util.ArrayList;

import com.sxt.Auser.domain.Userchat;

public interface FriendService {

	//查找用户列表
	ArrayList<Userchat> queryFriendList(int userId);

	//得到用户好友列表信息
	ArrayList<Userchat> getFriendList(int userId);
	
	
	

}
