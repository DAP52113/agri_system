package com.sxt.Auser.service;

import java.util.List;

import com.sxt.Auser.domain.Collect;
import com.sxt.Auser.vo.CollectVo;

//用户收藏接口
public interface CollectService {


	//查询id
	int queryInformationByinforId(int id);

	//加入收藏
	void addInformation(Collect collect);

	//查询收藏量
	int queryCountNum(String unname);

	//根据用户名查询所有收藏相关的信息
	List<CollectVo> queryInformationByCollectId(String unname);

	



	
	

}
