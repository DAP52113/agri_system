package com.sxt.Auser.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxt.Auser.domain.Userchat;
import com.sxt.Auser.mapper.FriendMapper;
import com.sxt.Auser.service.FriendService;


@Service
public class FriendServiceImpl implements FriendService {

	@Autowired
	private FriendMapper friendMapper;

	@Override
	public ArrayList<Userchat> queryFriendList(int userId) {
		// TODO Auto-generated method stub
		return this.friendMapper.queryFriendList(userId);
	}

	@Override
	public ArrayList<Userchat> getFriendList(int userId) {
		// TODO Auto-generated method stub
		return this.friendMapper.getFriendList(userId);
	}
	
	
	//好友列表
	
	
}
