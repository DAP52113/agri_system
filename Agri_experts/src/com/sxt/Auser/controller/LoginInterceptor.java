package com.sxt.Auser.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sxt.sys.domain.Admin;
import com.sxt.sys.domain.Expert;
import com.sxt.sys.domain.User;
import com.sxt.sys.utils.WebUtils;

/*
 * 用户登录拦截器
 * */
public class LoginInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		HttpSession session = request.getSession();
		//但凡可以拿到下面的其中一个session值便放行
		Admin admin = (Admin)session.getAttribute("admin");
		Expert expert = (Expert)session.getAttribute("expert");
		User user = (User)session.getAttribute("user");
		//System.out.println("session 获取的session为："+session);
		if(user == null && admin==null && expert==null){
			
			request.setAttribute("msg", "对不起请先登录");
			request.getRequestDispatcher("../index.jsp").forward(request, response);
			return false;
		}
		
		return true;
		
		
		
	}
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

		System.out.println("after");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("post");
	}



}
