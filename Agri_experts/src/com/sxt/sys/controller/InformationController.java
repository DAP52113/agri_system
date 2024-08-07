package com.sxt.sys.controller;

import java.awt.print.Printable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sxt.sys.domain.Information;
import com.sxt.sys.service.InformationService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;
import com.sxt.sys.vo.InformationVo;

/*
 * 信息审核控制器
 * */
@RestController
@RequestMapping("information")
public class InformationController {

	@Autowired
	private InformationService informationService;
	
	//查询所有信息
	@RequestMapping("loadAllInformation")
	public DataGridView loadAllInformation(InformationVo informationVo){
		return this.informationService.queryAllInformation(informationVo);
	}
	
	/*
	 * 修改公告信息
	 * */
	@RequestMapping("updateInformation")
	public ResultObj updateInformation(InformationVo informationVo){
		try {
			this.informationService.updateInformation(informationVo);
			return ResultObj.UPDATE_SUCCESS;  // 修改成功
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR; // 修改失败
		}
	}
	
	/*
	 * 删除信息数据
	 * */
	@RequestMapping("deleteInformation")
	public ResultObj deleteInformation(InformationVo informationVo){
		try {
			this.informationService.deleteInformation(informationVo.getId());
			return ResultObj.DELETE_SUCCESS; // 删除成功
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;// 删除失败
		}
	}
	
	/*
	 * 批量删除数据
	 * */
	@RequestMapping("deleteBatchInformation")
	public ResultObj deleteBatchInformation(InformationVo informationVo){
		try {
			this.informationService.deleteBatchInformation(informationVo.getIds());
			return ResultObj.DELETE_SUCCESS; //删除成功
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR; // 删除失败
		}
	}
	
	/*
	 * 加载农业专家资讯
	 * */
	@RequestMapping("loadInformationsById")
	public Information loadInformationsById(Integer id){
		return this.informationService.queryAllInformationsById(id);
	}
	
	
}
