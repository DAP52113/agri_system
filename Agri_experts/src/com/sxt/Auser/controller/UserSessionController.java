package com.sxt.Auser.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sxt.sys.domain.User;
import com.sxt.sys.service.UserService;
import com.sxt.sys.utils.WebUtils;
import com.sxt.sys.vo.UserVo;

@Controller
@RequestMapping("userSession")
public class UserSessionController {

	@Autowired
	private UserService userService;
	
	//跳转到用户聊天页面
	@RequestMapping("Chat")
	public String toChat(Model model){
		User user =  (User)WebUtils.getHttpSession().getAttribute("user");
		String unname = user.getUnname();
		//User myUser = userService.queryAllUserByUnname(unname);
		//model.addAttribute("myUser", myUser);
		List<User> userlists = this.userService.selectAllFriendsExMy(unname);
		model.addAttribute("userlists", userlists);
		return "user/Chat_user";
	}
	//取出session中的数据
	@RequestMapping("onlineuser")
	public String onlineuser(HttpSession session){
		User user = (User)WebUtils.getHttpSession().getAttribute("user");
		return user.getUnname();
	}
	
	/*
	 * 执行修改个人信息操作
	 * */
	@RequestMapping("UserSavePwd")
	public String UserSavePwd(UserVo userVo,Model model){
		try {
			this.userService.updateUser(userVo);
			//获取session值
			User user = (User)WebUtils.getHttpSession().getAttribute("user");
			String unname = user.getUnname();//得到sesion中的用户名
			User newUser = this.userService.queryAllUserByUnname(unname);//数据库查询用户密码
			model.addAttribute("newUser",newUser);
			
			return "user/PersonalCenter";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("updateError", "修改失败");
			return "user/PersonalCenter";
		}
	
	}
	
	
	
}
