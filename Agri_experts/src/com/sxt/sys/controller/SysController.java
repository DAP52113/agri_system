package com.sxt.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sxt.sys.domain.Admin;
import com.sxt.sys.utils.WebUtils;




/*
 * 页面跳转管理控制器
 * */
@Controller
@RequestMapping("sys")
public class SysController {

	
	//跳转到用户管理页面
	@RequestMapping("toUserManager")
	public String toUserManager(){
		//跳转到
		return "system/userManager/UserManager";
	}
	
	// 跳转到专家管理页面
	@RequestMapping("toExpertManager")
	public String toExpertManager(){
		//跳转
		return "system/expertManager/ExpertManager";
	}
	
	//跳转到日志管理页面
	@RequestMapping("toLogLoginManager")
	public String toLogLoginManager(){
		return "system/main/LogManager";
	}
	
	// 跳转到系统公告管理页面
	@RequestMapping("toNewsManager")
	public String toNewsManager(){
		return "system/main/NewsManager";
	}
	
	//跳转到修改密码页面
	@RequestMapping("toChangePwd")
	public String toChangePwd(){
		return "system/main/ChangePwd";
	}
	
	//跳转到信息审核页面
	@RequestMapping("toInformationCheck")
	public String toInformationCheck(){
		return "system/main/InformationCheck";
	}
	


	
}
