package com.sxt.Auser.service;

import java.util.List;

import com.sxt.Auser.domain.Consult;

//用户在线咨询接口
public interface ConsultService {

	//添加咨询
	void addConsult(Consult consult);

	//查询所有的咨询信息
	List<Consult> queryAllConsults();

	//根据领域查询问题
	List<Consult> queryAllConsultsByArea(String area);

	Consult queryAllConsultsByTitle(String title);

	//汇总总数
	int queryCountConsultNum(String counselor);

	//查询发布信息数
	List<Consult> queryFromUserInfo(String counselor);

}
