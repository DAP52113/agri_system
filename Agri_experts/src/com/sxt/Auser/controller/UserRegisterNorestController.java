package com.sxt.Auser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sxt.sys.domain.User;
import com.sxt.sys.service.UserService;

@RestController
@RequestMapping("user_register_noRest")
public class UserRegisterNorestController {

	
//	@Autowired
//	private UserService userService;
//	
//	@RequestMapping("userNameCheck")
//	public String userNameCheck(String userName){
//		String msg="";
//		String unname = userName;
//		System.out.println("要检测的用户名"+unname);
//		User user = this.userService.queryUserByUsernameCheck(unname);
//		String realbyMYsqlString = user.getUnname();
//		if(realbyMYsqlString.equals(unname)){
//			msg = "error";
//		}else{
//			msg = "success";
//		}
//		
//		return msg;
//	}
}
