package com.sxt.stat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxt.stat.domain.BaseEntity;
import com.sxt.stat.mapper.StatMapper;
import com.sxt.stat.service.StatService;

@Service
public class StatServiceImpl implements StatService {

	@Autowired
	private StatMapper statMapper;
	
	@Override
	public List<BaseEntity> queryExpertAddressStat() {
		// 统计将客户的地区进行查询
		return this.statMapper.queryExpertAddressStatList();
	}

	@Override
	public List<BaseEntity> queryInformationAreaStat() {
		// 咨询问题的所属领域进行查询
		return this.statMapper.queryInformationAreaStatList();
	}

}
