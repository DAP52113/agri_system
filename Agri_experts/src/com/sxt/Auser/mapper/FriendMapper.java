package com.sxt.Auser.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.sxt.Auser.domain.Friend;
import com.sxt.Auser.domain.Userchat;

public interface FriendMapper {
    int deleteByPrimaryKey(Integer friendid);

    int insert(Friend record);

    int insertSelective(Friend record);

    Friend selectByPrimaryKey(Integer friendid);

    int updateByPrimaryKeySelective(Friend record);

    int updateByPrimaryKey(Friend record);

	ArrayList<Userchat> queryFriendList(@Param("userId")int userId);

	ArrayList<Userchat> getFriendList(@Param("userId")int userId);
}