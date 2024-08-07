package com.sxt.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sxt.sys.domain.Admin;
import com.sxt.sys.service.AdminService;
import com.sxt.sys.service.UserService;
import com.sxt.sys.utils.ResultObj;
import com.sxt.sys.utils.WebUtils;
import com.sxt.sys.vo.AdminVo;

/*
 * 修改密码控制器
 * */
@RestController
@RequestMapping("Change")
public class ChangePwdController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserService userService;
	
	//检查旧密码
	@RequestMapping("CheckPwd")
	public String CheckPwd(String adminpsw){
		
		String msg="";
		Admin adminSession = (Admin) WebUtils.getHttpSession().getAttribute("admin");//获取session中的用户名
		System.out.println("session的用户名时："+adminSession);
		String adminname = adminSession.getAdminname();
		Admin admin =  this.adminService.queryAllByAdminName(adminname);//去数据库进行查询
		
		String oldPassword = admin.getAdminpsw();//查询旧密码结果
	
		System.out.println("旧密码时："+oldPassword);
		if(adminpsw.equals(oldPassword)){
			msg = "原密码输入正确";
		}else{
			msg = "原密码输入错误";
		}
		return msg;
	}
	
	@RequestMapping("CheckUserName")
	public String CheckUserName(String unname){
		String msg ="";
		String realUnname = this.userService.queryUserByUnname(unname);
		System.out.println(realUnname);
		if(realUnname != null){
			msg = "用户名已存在";
		}else{
			msg = "恭喜您用户名可以使用";
		}
		return msg;
	}
	
	
//	/*
//	 *修改密码操作
//	 **/
//	@RequestMapping("SavePwd")
//	public  String SavePwd(@RequestParam("NewPwd")String NewPwd,Model model){
//	try {
//			Admin adminSession = (Admin) WebUtils.getHttpSession().getAttribute("admin");//获取session中的用户名
//			System.out.println("session的用户名时："+adminSession);
//			String adminname = adminSession.getAdminname();
//			Admin admin =  this.adminService.queryAllByAdminName(adminname);//去数据库进行查询
//			admin.setAdminpsw(NewPwd);
//			this.adminService.updateAdminByPwd(admin);
//			model.addAttribute("success", "修改成功");
//			return "system/main/ChangePwd"; //修改成功
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("updateError", "修改失败");
//			return "system/main/ChangePwd"; // 修改啊成功
//		}
//	}

}
