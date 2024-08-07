package com.sxt.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sxt.sys.service.ExpertService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;
import com.sxt.sys.vo.ExpertVo;


/*
 * 专家管理控制器
 * */
@RestController
@RequestMapping("expert")
public class ExpertController {

	@Autowired
	private ExpertService expertService;
	
	/*
	 * 加载用户列表并返回DataView
	 * */
	@RequestMapping("loadAllExpert")
	public DataGridView loadAllExpert(ExpertVo expertVo){
		
		return this.expertService.queryAllExpert(expertVo);// 查询所有的用户
		
	}
	/*
	 * 添加专家功能
	 * */
	@RequestMapping("addExpert")
	public ResultObj addExpert(ExpertVo expertVo,HttpServletRequest request,HttpServletResponse response){
		try {
			this.expertService.addExpert(expertVo);
			return ResultObj.ADD_SUCCESS;// 添加成功
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR; // 添加失败
		}
	}
	
	/*
	 * 修改专家信息
	 * */
	@RequestMapping("updateExpert")
	public ResultObj updateExpert(ExpertVo expertVo){
		try {
			this.expertService.updateExpert(expertVo);
			return ResultObj.UPDATE_SUCCESS;//更新成功(以JSON数据格式)
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;//更新失败
		}
	}
	
	/*
	 * 删除专家信息
	 * */
	@RequestMapping("deleteExpert")
	public ResultObj deleteExpert(Integer eid){
		try {
			this.expertService.deleteExpert(eid);
			return ResultObj.DELETE_SUCCESS;// 删除成功
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;// 删除失败
		}
		
	}
	
	/*
	 * 批量删除专家信息
	 * */
	@RequestMapping("deleteBatchExpert")
	public ResultObj deleteBatchExpert(ExpertVo expertVo){
		try {
			this.expertService.deleteBatchExpert(expertVo.getEids());
			return ResultObj.DELETE_SUCCESS;// 删除成功
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;// 删除失败
		}
	}
	
	/*
	 * 重置专家密码
	 * */
	@RequestMapping("resetExpertPwd")
	public ResultObj resetExpertPwd(ExpertVo expertVo){
		try {
			this.expertService.resetExpertPwd(expertVo.getEid());
			return ResultObj.RESET_SUCCESS;// 重置成功
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.RESET_ERROR; // 重置失败
		}
	}
	
	
}
