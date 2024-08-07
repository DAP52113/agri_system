package com.sxt.sys.service;


import com.sxt.sys.domain.LogLogin;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.LogLoginVo;


/*
 * 日志登录接口
 * */
public interface LogLoginService {

	/**
	 * 查询所有日志
	 * @param 
	 * @return
	 */
	public DataGridView queryAllLog(LogLoginVo logLoginVo);
	/**
	 * 添加日志
	 * @param 
	 */
	public void addLog(LogLoginVo logLoginVo);

	/**
	 * 根据id删除日志
	 * @param 
	 */
	public void deleteLog(Integer id);
	/**
	 * 批量删除日志
	 * @param 
	 */
	public void deleteBatchLog(Integer [] ids);
	
	
	
}
