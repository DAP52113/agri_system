package com.sxt.Auser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxt.Auser.domain.Diagnose;
import com.sxt.Auser.mapper.DiagnoseMapper;
import com.sxt.Auser.service.DiagnoseService;
//远程问诊服务类
@Service
public class DiagnoseServiceImpl implements DiagnoseService {

	@Autowired
	private DiagnoseMapper diagnoseMapper;
	
	@Override
	public void addDiagnose(Diagnose diagnose) {
		// 添加远程咨询
		this.diagnoseMapper.insertSelective(diagnose);
	}

	@Override
	public List<Diagnose> queryAllDiagnoses() {
		// 查询
		return this.diagnoseMapper.queryAllDiagnose();
	}

	
}
