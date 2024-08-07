package com.sxt.Auser.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.sxt.Auser.domain.Messagechat;

public interface MessagechatMapper {
    int deleteByPrimaryKey(Integer messageid);

    int insert(Messagechat record);

    int insertSelective(Messagechat record);

    Messagechat selectByPrimaryKey(Integer messageid);

    int updateByPrimaryKeySelective(Messagechat record);

    int updateByPrimaryKey(Messagechat record);

    



	String getMessage(@Param("messagereciver")int setMessagereciver, @Param("messagesender")int setMessagesender);

	//得到统计在线信息发送人数
	int getMessageNotReadCount(@Param("messagereciver")int setMessagereciver, @Param("messagesender")int setMessagesender);

	void updateMessages(Messagechat messagechat);

	void saveMessage(Messagechat messagechat);

	ArrayList<Messagechat> getMessages(Messagechat messagechat);
	
	
}