package com.sxt.Auser.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

import com.sxt.Auser.domain.Consult;
import com.sxt.Auser.domain.Diagnose;
import com.sxt.Auser.domain.Message;
import com.sxt.Auser.service.ConsultService;
import com.sxt.Auser.service.DiagnoseService;
import com.sxt.Auser.service.MessageService;
import com.sxt.sys.domain.Information;
import com.sxt.sys.domain.User;
import com.sxt.sys.service.ExpertService;
import com.sxt.sys.service.InformationService;
import com.sxt.sys.service.UserService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;
import com.sxt.sys.utils.WebUtils;
import com.sxt.sys.vo.ExpertVo;

//用户无视图返回控制器
@RestController
@RequestMapping("userRestController")
public class UserNoRestController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private InformationService informationService;
	
	@Autowired
	private ConsultService consultService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private ExpertService expertService;
	
	@Autowired
	private DiagnoseService diagnoseService;
	
	//用户修改密码
	@RequestMapping("userChangePwd")
	public String userChangePwd(@RequestParam("userpsw")String upsw){
		String msg="";
		User user = (User)WebUtils.getHttpSession().getAttribute("user");
		String unname = user.getUnname();//得到sesion中的用户名
		User newUser = this.userService.queryAllUserByUnname(unname);//数据库查询用户密码
		String oldpassword = newUser.getUpsw();//获取到真实的用户名密码
		if(oldpassword.equals(upsw)){
			msg="原密码输入正确";
		}else{
			msg="原密码输入错误";
		}
		
		return msg;
	}
	
	//根据用户搜索的标题去查询数据库
	@RequestMapping("SearchTitle")
	public List<Information> SearchTitle(String title){
		 List<Information> informationsByTitle =  this.informationService.queryAllInformationByTitle(title);
		 for(Information information:informationsByTitle){
			 System.out.println("后台所传输的字段为"+information.getTitle());
			 
		 }
		 return informationsByTitle;
	}
	

	//查询数据库的聊天记录
	@RequestMapping("queryChat")
	public String queryChat(String send,String receive){

		List<Message> list = this.messageService.queryAllChat(send,receive);
		JSONArray jaArray = new JSONArray();//转换为json格式
		for(int i = 0;i<list.size();i++){
			JSONObject json = new JSONObject();
			json.put("content", list.get(i).getContent());
			json.put("send", list.get(i).getSend());
			json.put("receive", list.get(i).getReceive());
			json.put("date", list.get(i).getDate());
			
			jaArray.put(json);
		}
		return  jaArray.toString();
	}
	
	
	//发送信息插入数据库
	@RequestMapping("insertChatMessage")
	public String insertChatMessage(String send,String receive,String content){
		try {
			Message message = new Message();
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = simpleDateFormat.format(date);
			message.setDate(date);
			message.setSend(send);
			message.setReceive(receive);
			message.setContent(content);
			this.messageService.insertMessage(message);
			return dateString;//添加成功
		} catch (Exception e) {
			e.printStackTrace();
			return "添加失败";//添加失败
		}
	}
	//加载专家
	@RequestMapping("loadAllExpert")	
	public DataGridView loadAllExpert(ExpertVo expertVo){
		return this.expertService.loadAllExpert(expertVo);
	}
	
	//图文咨询添加
	@RequestMapping("addDiagnose")
	public ResultObj addDiagnose(Diagnose diagnose){
		try {
			User user =  (User)WebUtils.getHttpSession().getAttribute("user");
			String counselor = user.getUnname();
			diagnose.setCounselor(counselor);
			diagnose.setIndate(new Date());
			this.diagnoseService.addDiagnose(diagnose);
			return ResultObj.ADD_SUCCESS;//添加成功
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR; //添加失败
		}
	}	



	
}
