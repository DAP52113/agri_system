package com.sxt.stat.service;

import java.util.List;

import com.sxt.stat.domain.BaseEntity;

public interface StatService {

	// 客户地区的查询
	List<BaseEntity> queryExpertAddressStat();

	// 咨询问题领域查询
	List<BaseEntity> queryInformationAreaStat();

}
