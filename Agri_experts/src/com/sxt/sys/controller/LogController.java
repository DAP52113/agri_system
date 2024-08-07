package com.sxt.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sxt.sys.service.LogLoginService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;
import com.sxt.sys.vo.LogLoginVo;

/*
 * 日志管理控制器
 * */
@RestController
@RequestMapping("log")
public class LogController {

	@Autowired
	private LogLoginService loginService;
	
	@RequestMapping("loadAllLog")
	public DataGridView loadAllLog(LogLoginVo logLoginVo){
	
		//查询所有日志并加载
		return this.loginService.queryAllLog(logLoginVo);
	}
	
	
	//删除日志文件
	@RequestMapping("deleteLog")
	public ResultObj deleteLog(LogLoginVo logLoginVo){
		try {
			this.loginService.deleteLog(logLoginVo.getId());
			return ResultObj.DELETE_SUCCESS; //删除日志文件成功
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;// 删除失败(JSON格式)
		}
		
	}
	
	//批量删除日志文件
	@RequestMapping("deleteBatchLog")
	public ResultObj deleteBatchLog(LogLoginVo logLoginVo){
		try {
			this.loginService.deleteBatchLog(logLoginVo.getIds());
			return ResultObj.DELETE_SUCCESS; //删除成功
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR; // 删除失败(JSON格式)
		}
	}
	
	
	
	
}
