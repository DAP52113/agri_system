package com.sxt.Auser.mapper;

import org.apache.ibatis.annotations.Param;

import com.sxt.Auser.domain.Like;

public interface LikeMapper {
    int deleteByPrimaryKey(Integer likeid);

    int insert(Like record);

    int insertSelective(Like record);

    Like selectByPrimaryKey(Integer likeid);

    int updateByPrimaryKeySelective(Like record);

    int updateByPrimaryKey(Like record);

	int queryInformationByinforId(@Param("informationid")int id);

	int queryCountNum(@Param("unname")String unname);
}