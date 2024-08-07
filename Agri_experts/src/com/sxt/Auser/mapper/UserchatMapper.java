package com.sxt.Auser.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.sxt.Auser.domain.ChatFriend;
import com.sxt.Auser.domain.Userchat;

public interface UserchatMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(Userchat record);

    int insertSelective(Userchat record);

    Userchat selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(Userchat record);

    int updateByPrimaryKey(Userchat record);

    //添加到数据库聊天用户
	void addUserChatInfo(Userchat userchat);

	Userchat queryUserByUserName(String username);

	//查询用户信息
	Userchat queryById(int userId);

	//得到聊天好友信息
	ArrayList<ChatFriend> getChatFriendInfo(int userId);

	Userchat getUserById(@Param("userId")Integer userId);
}