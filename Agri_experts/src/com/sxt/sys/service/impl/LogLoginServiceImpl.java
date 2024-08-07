package com.sxt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.domain.LogLogin;
import com.sxt.sys.domain.User;
import com.sxt.sys.mapper.LogLoginMapper;
import com.sxt.sys.service.LogLoginService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.LogLoginVo;

@Service
public class LogLoginServiceImpl implements LogLoginService {

	@Autowired
	private LogLoginMapper logLoginMapper;
	
	
	@Override
	public DataGridView queryAllLog(LogLoginVo logLoginVo) {
		//查询所有日志
		// 使用分页查询
		Page<Object> page = PageHelper.startPage(logLoginVo.getPage(),logLoginVo.getLimit());
		List<LogLogin> data =  this.logLoginMapper.queryAllLogLogin(logLoginVo);//函数查询
		return new DataGridView(page.getTotal(),data);//调用数据数目
	}

	@Override
	public void addLog(LogLoginVo logLoginVo) {
		// 添加日志
		this.logLoginMapper.insertSelective(logLoginVo);

	}

	@Override
	public void deleteLog(Integer id) {
		// 删除日志
		this.logLoginMapper.deleteByPrimaryKey(id);

	}

	@Override
	public void deleteBatchLog(Integer[] ids) {
		// 批量删除日志文件
		for(Integer id:ids){
			this.deleteLog(id);
		}

	}

}
