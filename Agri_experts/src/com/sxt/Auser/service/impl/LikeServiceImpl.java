package com.sxt.Auser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxt.Auser.domain.Like;
import com.sxt.Auser.mapper.LikeMapper;
import com.sxt.Auser.service.LikeService;
//点赞实现服务类
@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
	private LikeMapper likeMapper;

	@Override
	public void addLikeInfo(Like like) {
		// TODO Auto-generated method stub
		this.likeMapper.insertSelective(like);
	}

	@Override
	public int queryInformationByinforId(int id) {
		// TODO Auto-generated method stub
		return this.likeMapper.queryInformationByinforId(id);
	}

	@Override
	public int queryCountNum(String unname) {
		// TODO Auto-generated method stub
		return this.likeMapper.queryCountNum(unname);
	}
	
	
	
	
}
