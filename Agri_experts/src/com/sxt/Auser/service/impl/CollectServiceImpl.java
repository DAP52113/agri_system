package com.sxt.Auser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxt.Auser.domain.Collect;
import com.sxt.Auser.mapper.CollectMapper;
import com.sxt.Auser.service.CollectService;
import com.sxt.Auser.vo.CollectVo;
//收藏实现类
@Service
public class CollectServiceImpl implements CollectService {
		
	@Autowired
	private CollectMapper collectMapper;



	@Override
	public int queryInformationByinforId(int id) {
		// TODO Auto-generated method stub
		return this.collectMapper.queryInformationByinforId(id);
	}



	@Override
	public void addInformation(Collect collect) {
		// TODO Auto-generated method stub
		this.collectMapper.insertSelective(collect);
	}



	@Override
	public int queryCountNum(String unname) {
		// TODO Auto-generated method stub
		return this.collectMapper.queryCollectNum(unname);
	}



	@Override
	public List<CollectVo> queryInformationByCollectId(String unname) {
		// TODO Auto-generated method stub
		return this.collectMapper.queryInformationByCollectId(unname);
	}

	
	
	
	
}
