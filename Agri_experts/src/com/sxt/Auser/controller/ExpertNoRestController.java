package com.sxt.Auser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sxt.sys.domain.Expert;
import com.sxt.sys.domain.Information;
import com.sxt.sys.service.ExpertService;
import com.sxt.sys.service.InformationService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.WebUtils;
import com.sxt.sys.vo.InformationVo;

/*
 * 没有视图解析器的控制器
 * */
@RestController
@RequestMapping("ExpertRest")
public class ExpertNoRestController {

	@Autowired
	private InformationService informationService;
	
	@Autowired
	private ExpertService expertService;
	
	//查询专家原密码
	@RequestMapping("expertChangePwd")
	public String expertChangePwd(String expertpsw){
		//获取session中的存储值
		String msg="";
		Expert expertsession = (Expert)WebUtils.getHttpSession().getAttribute("expert");
		String eno = expertsession.getEno();//获取用户名
		//根据用户名查询数据库
		Expert expert = this.expertService.selectExpertByEno(eno);
		String queryEpsw = expert.getEpsw();//获取数据库查询密码
		if(queryEpsw.equals(expertpsw)){
			msg="原密码输入正确";
			
		}else{
			msg="原密码输入错误";
		}
		return msg;
		
	}
	//分页查询信息
	@RequestMapping("loadInformationsLimit")
	public List<Information> loadInformationsLimit(Integer p,Integer limit){
		Integer pagesize = limit;//pagesize表示查询的数目
		//start表示查询的第一个位置
		System.out.println("pagesize"+pagesize);
		List<Information> informations =  this.informationService.selectAllInformationWithLimit(p,pagesize);

		return informations;
	}
	
}
