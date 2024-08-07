package com.sxt.Auser.service;

import java.util.List;

import com.sxt.Auser.domain.Words;

//文章咨询第一步留言接口
public interface WordService {

	//查询文章信息留言
	List<Words> queryAllWords();

	//添加回复
	void addReplyContent(Words words);

}
