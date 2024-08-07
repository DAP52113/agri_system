package com.sxt.Auser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxt.Auser.domain.Consult;
import com.sxt.Auser.mapper.ConsultMapper;
import com.sxt.Auser.service.ConsultService;

@Service
public class ConsultServiceImpl implements ConsultService {

	@Autowired
	private ConsultMapper consultMapper;
	
	@Override
	public void addConsult(Consult consult) {
		// 添加咨询
		this.consultMapper.insertSelective(consult);
	}

	@Override
	public List<Consult> queryAllConsults() {
		// 查询咨询信息
		return this.consultMapper.queryAllConsults();
	}

	@Override
	public List<Consult> queryAllConsultsByArea(String area) {
		// 查询领域问题
		return this.consultMapper.queryAllConsultsByArea(area);
	}

	@Override
	public Consult queryAllConsultsByTitle(String title) {
		// TODO Auto-generated method stub
		return this.consultMapper.queryAllConsultsByTitle(title);
	}

	@Override
	public int queryCountConsultNum(String counselor) {
		// TODO Auto-generated method stub
		return this.consultMapper.queryCountConsultNum(counselor);
	}

	@Override
	public List<Consult> queryFromUserInfo(String counselor) {
		// TODO Auto-generated method stub
		return this.consultMapper.queryFromUserInfo(counselor);
	}

}
