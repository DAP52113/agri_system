package com.sxt.sys.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.domain.Admin;
import com.sxt.sys.domain.Expert;
import com.sxt.sys.domain.Information;
import com.sxt.sys.domain.User;
import com.sxt.sys.service.AdminService;
import com.sxt.sys.service.ExpertService;
import com.sxt.sys.service.InformationService;
import com.sxt.sys.service.LogLoginService;
import com.sxt.sys.service.UserService;
import com.sxt.sys.utils.RedisUtils;
import com.sxt.sys.utils.WebUtils;
import com.sxt.sys.vo.AdminVo;
import com.sxt.sys.vo.ExpertVo;
import com.sxt.sys.vo.LogLoginVo;
import com.sxt.sys.vo.UserVo;

/*
 * 用户登录控制器
 * */
@Controller
@RequestMapping("login")
public class LoginController {

	
	@Autowired
	private  AdminService adminService;
	@Autowired
	private  UserService userService;

	@Autowired
	private ExpertService expertService;
	
	@Autowired
	private LogLoginService logLoginService;
	
	@Autowired
	private InformationService informationService;
	
	@Resource
	private RedisUtils redisUtils;
	
	/*
	 * 跳转到登录页面
	 * */
	@RequestMapping("toLogin")
	public String toLogin(){
		return "system/main/login";
	}
	//User user = userService.loginUser(userVo);
	// 管理员登录方法
	@RequestMapping("login")
	public String login(AdminVo adminVo,Model model,String userType){
		
	    String code=WebUtils.getHttpSession().getAttribute("code").toString();
	    if(adminVo.getCode().equals(code)){
		switch (userType) {
		case "管理员":
			
				Admin admin = this.adminService.login(adminVo);

				if(null != admin){
					//放到session
					WebUtils.getHttpSession().setAttribute("admin", admin);
					//记录登录日志，向login_log插入数据
					LogLoginVo logLoginVo = new LogLoginVo();
					logLoginVo.setLogintime(new Date());// 时间为当前时间
					logLoginVo.setLoginname(admin.getAdminname());// 登录名为管理员名
				    logLoginVo.setLoginip(WebUtils.getHttpServletRequest().getRemoteAddr());// 获取请求ip
					logLoginService.addLog(logLoginVo);//执行添加日志函数
					return "system/main/index";
				}
		case "用户":
	
			String  unname = adminVo.getAdminname();
			String  upsw = adminVo.getAdminpsw();
			UserVo userVo = new UserVo();
			User user = new User();
			userVo.setUnname(unname);
			userVo.setUpsw(upsw);
			boolean result  = redisUtils.haskey(unname);
			if(result == true ){
				JSONObject jsonObject =  JSONObject.parseObject(redisUtils.get(unname));
				user.setUnname(jsonObject.get("unname").toString());
				user.setUpsw(jsonObject.get("upsw").toString());
				user.setUphoto(jsonObject.get("uphoto").toString());
				user.setIntroduce(jsonObject.get("introduce").toString());
			}else{
				 user = this.userService.loginUser(userVo);
			}
			
			if(user != null){
				//放到session
				WebUtils.getHttpSession().setAttribute("user", user);
				//记录登录日志，向login_log插入数据
				LogLoginVo logLoginVo = new LogLoginVo();
				logLoginVo.setLogintime(new Date());// 当前时间
				logLoginVo.setLoginname(user.getUnname()); // 用户角色登录用户名
				logLoginVo.setLoginip(WebUtils.getHttpServletRequest().getRemoteAddr()); // 获得ip地址
				logLoginService.addLog(logLoginVo);
				// 查询资讯信息
				List<Information> informations = informationService.queryAllInformationByUser();
				model.addAttribute("informations",informations);
				//查询专家信息
				List<Expert> experts = expertService.queryAllExpertsByUser();
			
				model.addAttribute("experts", experts);
				return "user/index";
			}
		case "农业专家":
			String  eno = adminVo.getAdminname();// 获取用户输入信息
			String  epsw = adminVo.getAdminpsw();
			Expert expert = new Expert();
			ExpertVo expertVo = new ExpertVo();
			expertVo.setEno(eno);
			expertVo.setEpsw(epsw);
			boolean result_expert =  redisUtils.haskey(eno);
			if(result_expert == true){
				JSONObject jsonObject = JSONObject.parseObject(redisUtils.get(eno));
				expert.setEno(jsonObject.get("eno").toString());
				expert.setEpsw(jsonObject.get("epsw").toString());
				expert.setEprof(jsonObject.get("eprof").toString());
				expert.setPhoto(jsonObject.get("photo").toString());
				expert.setEname(jsonObject.get("ename").toString());
			}else{
				expert = this.expertService.loginExpert(expertVo);
				
			}
			if(null != expert){
				//放到session
				WebUtils.getHttpSession().setAttribute("expert", expert);
				//记录登录日志，向login_log插入数据
				LogLoginVo logLoginVo = new LogLoginVo();
				logLoginVo.setLogintime(new Date());//  获取当前时间
				logLoginVo.setLoginname(expert.getEno()); // 获取当前登录用户名
				logLoginVo.setLoginip(WebUtils.getHttpServletRequest().getRemoteAddr());// 获取当前请求ip
				logLoginService.addLog(logLoginVo);
				
				//查询当前资讯信息
		
				List<Information> informations = informationService.queryAllInformationByUserNoLimit();
				model.addAttribute("informations",informations);
				return "expert/index";
			}
		default:
				model.addAttribute("error",SysConstast.ADMIN_LOGIN_ERROR_MSG);
				return "system/main/login";
		
		}
		
	    }else{
	    	model.addAttribute("error",SysConstast.ADMIN_LOGIN_CODE_ERROR_MSG);
			return "system/main/login";
	    }

	}
	
	
	/*
	 * 得到登录验证码
	 * */
	@RequestMapping("getCode")
	public void  getCode(HttpServletResponse response,HttpSession session) throws IOException{
		//定义图形验证码的长和宽
		LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116, 36);
		session.setAttribute("code", lineCaptcha.getCode());// 将其字符存储到session中
		
		ServletOutputStream outputStream =  response.getOutputStream();// 拿到输入输出流
		
		ImageIO.write(lineCaptcha.getImage(), "JPEG", outputStream);

	}

	
	
	
	
}
