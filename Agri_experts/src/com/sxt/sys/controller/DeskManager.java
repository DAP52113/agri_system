package com.sxt.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sxt.sys.domain.Admin;
import com.sxt.sys.service.AdminService;
import com.sxt.sys.utils.WebUtils;

/*
 * 后台首页控制台开发
 * */
@Controller
@RequestMapping("desk")
public class DeskManager {

	@Autowired
	private AdminService adminService;
	
	
	/*
	 * 跳转到工作台的页面
	 * */
	@RequestMapping("toDeskManager")
	public String toDeskManager(){
		return  "system/main/deskManager";
	}
	
	/*
	 *修改密码操作
	 **/
	@RequestMapping("SavePwd")
	public  String SavePwd(@RequestParam("NewPwd")String NewPwd,Model model){
	try {
			Admin adminSession = (Admin) WebUtils.getHttpSession().getAttribute("admin");//获取session中的用户名
			System.out.println("session的用户名时："+adminSession);
			String adminname = adminSession.getAdminname();
			Admin admin =  this.adminService.queryAllByAdminName(adminname);//去数据库进行查询
			admin.setAdminpsw(NewPwd);
			this.adminService.updateAdminByPwd(admin);
			model.addAttribute("success", "修改成功");
			return "system/main/ChangePwd"; //修改成功
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("updateError", "修改失败");
			return "system/main/ChangePwd"; // 修改啊成功
		}
	}
	
	
}
