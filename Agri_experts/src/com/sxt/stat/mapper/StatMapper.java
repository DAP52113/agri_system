package com.sxt.stat.mapper;

import java.util.List;

import com.sxt.stat.domain.BaseEntity;

public interface StatMapper {

	//查询专家地区
	List<BaseEntity> queryExpertAddressStatList();
	//查询问题领域
	List<BaseEntity> queryInformationAreaStatList();

}
