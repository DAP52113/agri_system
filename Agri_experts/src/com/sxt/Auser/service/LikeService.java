package com.sxt.Auser.service;

import com.sxt.Auser.domain.Like;

//点赞实现接口
public interface LikeService {

	void addLikeInfo(Like like);

	//根据文章id进行查询点赞id
	int queryInformationByinforId(int id);

	//获取点赞量
	int queryCountNum(String unname);
	

}
