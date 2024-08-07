package com.sxt.stat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxt.stat.domain.BaseEntity;
import com.sxt.stat.service.StatService;


/*
 * 统计分析
 * */
@RequestMapping("stat")
@Controller
public class StatController {

	@Autowired
	private StatService statService;
	
	
	//跳转到专家地区统计界面
	@RequestMapping("toExpertAddressStat")
	public String toExpertAddressStat(){
		return "stat/expertAddressStat";
	}
	
	// 跳转到咨询问题领域统计页面
	@RequestMapping("toInformationAreaStat")
	public String toInformationAreaStat(){
		return "stat/InformationAreaStat";
	}
	
	/*
	 * 加载客户地区
	 * */
	@RequestMapping("loadExpertAddressStatJSON")
	@ResponseBody
	public List<BaseEntity> loadExpertAddressStatJSON(){
		return this.statService.queryExpertAddressStat();
	}
	
	/*
	 * 加载咨询问题的领域信息
	 * */
	@RequestMapping("loadInformationAreaStatJSON")
	@ResponseBody
	public List<BaseEntity> loadInformationAreaStatJSON(){
		return this.statService.queryInformationAreaStat();
	}
	
}
