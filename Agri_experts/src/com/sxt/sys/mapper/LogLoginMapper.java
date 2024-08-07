package com.sxt.sys.mapper;

import java.util.List;

import com.sxt.sys.domain.LogLogin;

public interface LogLoginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogLogin record);

    int insertSelective(LogLogin record);

    LogLogin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogLogin record);

    int updateByPrimaryKey(LogLogin record);
    
    /*
     * 查询日志
     * */
    List<LogLogin> queryAllLogLogin(LogLogin logLogin);
    
    
}