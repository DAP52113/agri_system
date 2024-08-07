package com.sxt.Auser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxt.sys.domain.Expert;
import com.sxt.sys.domain.User;
import com.sxt.sys.service.ExpertService;
import com.sxt.sys.service.UserService;
import com.sxt.sys.utils.MobileUtils;
import com.sxt.sys.vo.UserVo;

//用户注册控制器
@Controller
@RequestMapping("userRegister")
public class UserRegisterController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ExpertService expertService;
	

	
	//跳转到注册页面
	@RequestMapping("register")
	public String toRegister(){
		return "system/main/zhuce";
	}
	
	//跳转到专家注册页面
	@RequestMapping("expertRegister")
	public String toExpertRegister(){
		return "system/main/expertzhuce"; 
	}

	
	@RequestMapping("userNameCheck")
	@ResponseBody
	public String userNameCheck(String unname){
		String msg="";
		
		System.out.println("要检测的用户名"+unname);
		User user = this.userService.queryUserByUsernameCheck(unname);
		
		if(user == null){
			msg = "success";
		}else{
			msg = "error";
		}
		
		return msg;
	}
	
	
	//检测用户名是否存在
	@RequestMapping("expertnamecheck")
	@ResponseBody
	public String expertNameCheck(String userName){
		String msg = "";
		String eno = userName;
		if(MobileUtils.checkPhone(eno) == false){
			msg = "error";
			return msg;
		}
		
		System.out.println("数据库查询"+eno);
		Expert expert = this.expertService.queryExpertByEno(eno);
		if(expert == null){
			msg = "success";
		}else{
			msg = "error";
		}
		
		return msg;
	}
	
	
	//用户注册操作实现
	@RequestMapping("userRegisterToLogin")
	public String userRegisterToLogin(@RequestParam("unname")String unname,@RequestParam("password")String upsw,@RequestParam("introduce")String introduce,Model model){
		try {
			String uphoto = "2022-05-27/202205270032030165818.png_temp";//默认用户头像
			
			User user  = new User();
			user.setUnname(unname);
			user.setUpsw(upsw);
			user.setIntroduce(introduce);
			user.setUphoto(uphoto);
			
			boolean result = this.userService.addUserRegister(user);
			//成功后存入redis
			if(result == true){
				model.addAttribute("successRegister","用户注册成功");
				return "system/main/login";
			}else{
				model.addAttribute("errorRegister", "注册错误");
				return "system/main/zhuce";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorRegister", "注册错误");
			return "system/main/zhuce";
		}
	}
	
	//专家用户注册实现
	@RequestMapping("expertRegisterToLogin")
	public String expertRegisterToLogin(@RequestParam("userName")String eno,@RequestParam("name")String ename,@RequestParam("password")String  epsw,@RequestParam("userType")String esex,@RequestParam("eprof")String eprof,@RequestParam("area")String area,@RequestParam("province")String province,  @RequestParam("city")String city,@RequestParam("county")String county,   Model model){
		try {
			String photo = "2021-06-20/202106200011512689926.jpg_temp";//专家默认头像
			System.out.println("eno"+eno);
			if(eno.equals("")){
				model.addAttribute("errorRegisterExpert", "哎呀，注册好像出错误了");
				return "system/main/expertzhuce";
			}
			Expert expert = new Expert();
			expert.setEno(eno);
			expert.setEname(ename);
			expert.setEpsw(epsw);
			expert.setEsex(esex);
			expert.setArea(area);
			expert.setEprof(eprof);
			expert.setPhoto(photo);
			expert.setProvince(province);
			expert.setCity(city);
			expert.setDistrict(county);
		
			boolean result = this.expertService.addExpertRegister(expert);
			if(result == true){
				model.addAttribute("successRegisterExpert","专家注册成功");
				return "system/main/login";
			}else{
				model.addAttribute("errorRegisterExpert", "注册错误");
				return "system/main/expertzhuce";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			model.addAttribute("errorRegisterExpert", "哎呀，注册好像出错误了");
			return "system/main/expertzhuce";
		}
	
		
		
		
	}
	
	
	
	
}
