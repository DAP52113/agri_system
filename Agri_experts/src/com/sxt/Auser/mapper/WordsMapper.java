package com.sxt.Auser.mapper;

import java.util.List;

import com.sxt.Auser.domain.Words;

public interface WordsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Words record);

    int insertSelective(Words record);

    Words selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Words record);

    int updateByPrimaryKey(Words record);

    //查询所有的信息
	List<Words> queryAllWordsInfo();
}