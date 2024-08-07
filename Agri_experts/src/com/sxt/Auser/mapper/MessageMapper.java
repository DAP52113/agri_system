package com.sxt.Auser.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sxt.Auser.domain.Message;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    //多个数值时需要使用param注解进行解释
	List<Message> queryAllChat(@Param("send")String send, @Param("receive")String receive);
}