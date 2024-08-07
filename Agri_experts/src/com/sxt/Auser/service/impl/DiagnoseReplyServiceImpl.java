package com.sxt.Auser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxt.Auser.domain.Diagnosereply;
import com.sxt.Auser.mapper.DiagnosereplyMapper;
import com.sxt.Auser.service.DiagnoseReplyService;

//远程问诊操作服务类
@Service
public class DiagnoseReplyServiceImpl implements DiagnoseReplyService {

	@Autowired
	private DiagnosereplyMapper diagnoseReplyMapper;
	
	@Override
	public List<Diagnosereply> queryAllDiagnoseReplies() {
		// 查询所有的远程回复信息
		return this.diagnoseReplyMapper.queryAllDiagnoseReplies();
	}

	@Override
	public void addDiagnoseReply(Diagnosereply diagnosereply) {
		// 添加远程回复信息
		this.diagnoseReplyMapper.insertSelective(diagnosereply);
	}

}
