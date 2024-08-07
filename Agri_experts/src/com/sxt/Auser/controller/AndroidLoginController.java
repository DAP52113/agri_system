package com.sxt.Auser.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;












import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.json.JSONConfig;

import com.sxt.Auser.domain.Collect;
import com.sxt.Auser.domain.Consult;
import com.sxt.Auser.domain.Like;
import com.sxt.Auser.domain.Reply;
import com.sxt.Auser.service.CollectService;
import com.sxt.Auser.service.ConsultService;
import com.sxt.Auser.service.LikeService;
import com.sxt.Auser.service.ReplyService;
import com.sxt.Auser.utils.JsonDateValueProcessor;
import com.sxt.Auser.vo.CollectVo;
import com.sxt.Auser.vo.ReplyVo;
import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.domain.Expert;
import com.sxt.sys.domain.Information;
import com.sxt.sys.domain.User;
import com.sxt.sys.service.ExpertService;
import com.sxt.sys.service.InformationService;
import com.sxt.sys.service.UserService;

//安卓服务器端连接接口

@RestController
@RequestMapping("androidaction")
public class AndroidLoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private InformationService informationService;
	
	@Autowired
	private ExpertService expertService;
	
	@Autowired
	private ConsultService consultService;
	
	@Autowired
	private CollectService collectService;
	
	@Autowired
	private LikeService likeService;
	
	@Autowired
	private ReplyService replyService;
	
	
	@RequestMapping("androidlogin")
	@ResponseBody
	public  void  androidlogin(HttpServletResponse response,HttpServletRequest request) throws IOException{
		

		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String unname = request.getParameter("unname").trim();
		String upsw = request.getParameter("password").trim();
		System.out.println("=======安卓客户端连接服务器成功=============");
		User user_android = this.userService.findAndroidLogin(unname,upsw);
		
		
		if(user_android != null) {
			out.println("login successfully!");
		}
		else {
			out.println("can not login!");
		}
		
		
	
	}

	//安卓客户端检测用户名是否存在所进行的检测
	@RequestMapping("androidcheckusername")
	@ResponseBody
	public void androidcheckusername(HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		
		//设置编码字符集
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out  = response.getWriter();
		//拿到uname
		String unname = request.getParameter("unname").trim();
		System.out.println("=======安卓客户端连接服务器成功=============");
		User user_check = this.userService.checkusername(unname);
		if(user_check != null){//数据库存在该用户名
			out.print("exist");
		}else{
			out.print("no exist");
		}
		
		
	}
	
	//安卓专家端注册功能实现
	@RequestMapping("expertregister")
	@ResponseBody
	public void expertregister(HttpServletRequest request,HttpServletResponse response) throws IOException{
		       //设置编码字符集
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				String eno = request.getParameter("eno").trim();
				
				String ename = request.getParameter("ename").trim();
				String epsw = request.getParameter("epsw").trim();
				System.out.println("=======安卓客户端连接服务器成功=============");
				try {
					Expert expert = new Expert();
					expert.setEno(eno);
					expert.setEpsw(epsw);

					expert.setEname(ename);
					expert.setArea("植保");
					expert.setEprof("农业专家");
					this.expertService.addExpertRegister(expert);
					Expert expert_queryExpert = this.expertService.queryExpertByEno(eno);
					System.out.println("查询到的expert_queryExpert"+expert_queryExpert);
					if(expert_queryExpert != null){//查询到了添加成功
						out.print("expertregister is successful");
					}else{
						out.print("register false!");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
	}
	
	//安卓用户端注册功能实现
	@RequestMapping("androidlregister")
	@ResponseBody
	public void  androidlregister(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		//设置编码字符集
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String unname = request.getParameter("unname").trim();
		String upsw = request.getParameter("password").trim();
		System.out.println("=======安卓客户端连接服务器成功=============");
		try {
			User user = new User();
			user.setUnname(unname);
			user.setUpsw(upsw);
			user.setIntroduce("专家系统所有");
			user.setUphoto("2022-05-27/202205270032030165818.png_temp");
			this.userService.addUserRegister(user);
			User quertUser = this.userService.checkusername(unname);
			System.out.println("查询到的queryuser"+quertUser);
			if(quertUser != null){//查询到了添加成功
				out.print("register successfully!");
			}else{
				out.print("register false!");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
				
			System.out.println("=======安卓客户端连接服务器注册失败=============");
		}
		
		
	}

	
	//安卓查询数据库资讯
	@RequestMapping("androidquerydetail")
	@ResponseBody
	public  void  androidquerydetail(HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		response.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		
		System.out.println("andoroid拿到的title"+title);
		List<Information> infoLists = this.informationService.queryInfoByTitle(title);
		
		
		response.setContentType("application/x-json");
		
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = JSONArray.fromObject(infoLists);//设置存放数据
		System.out.print(jsonArray.toString());
		out.print(jsonArray);
		//清除缓存
		out.flush();
		//关闭
		out.close();
		
	}
	

	//用户在线提交咨询问题控制器
	@RequestMapping("sublimit")
	@ResponseBody
	public  void  sublimit(HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		response.setCharacterEncoding("utf-8");//设置返回数据字符集
		//获取传输的数据
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String district = request.getParameter("district");
		String counselor = request.getParameter("counselor");
		String area = request.getParameter("area");
		PrintWriter out = response.getWriter();
		Consult consult = new Consult();
		consult.setArea(area);
		consult.setCity(city);
		consult.setContent(content);
		consult.setCounselor(counselor);
		consult.setDate(new Date());
		consult.setDistrict(district);
		consult.setProvince(province);
		consult.setTitle(title);
		
		try {
			this.consultService.addConsult(consult);
			//查询信息
			Consult query_Consult = this.consultService.queryAllConsultsByTitle(title);
			if(query_Consult != null){
				out.print("successful");
			}else{
				out.print("false");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	//后端根据专家领域查询专家信息接口
	@RequestMapping("getexpertbyarea")
	@ResponseBody
	public void getexpertbyarea(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");//设置返回数据字符集
		//得到Android端发送来的area领域信息
		String area = request.getParameter("area");

		System.out.println("==== android端发送来 的area数据集"+area);
		//访问数据库查询专家信息的接口
		List<Expert> expert_androidExperts = this.expertService.queryAllExpertsByArea(area);
		response.setContentType("application/x-json");//设置一JSON数据进行与Android端的数据传输
		PrintWriter outPrintWriter  = response.getWriter();//设置输出打印流
		if(expert_androidExperts != null){//若查询到相关的数据集
			JSONArray jsonArray = JSONArray.fromObject(expert_androidExperts);//对得到的list集合数据进行封装成json数据格式
			//打印测试json数据
			System.out.print(jsonArray.toString());
			outPrintWriter.print(jsonArray);//前台传递json数据格式
			outPrintWriter.flush();//清理相关的数据缓存
			outPrintWriter.close();//关闭缓存
		}else{//若没有查询到相关的数据集
			outPrintWriter.print("没有查询到相关数据");
		}
	}
	
	//安卓首页
	//安卓查询数据库资讯
	@RequestMapping("androidqueryall")
	@ResponseBody
	public  void  androidqueryall(HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		
		
		List<Information> infoLists = this.informationService.queryInfoGettitleAndarea();
		System.out.println("=====安卓listView查询服务器启动");
		
		response.setContentType("application/x-json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = JSONArray.fromObject(infoLists);//设置存放数据
		System.out.print(jsonArray.toString());
		out.print(jsonArray);
		//清除缓存
		out.flush();
		//关闭
		out.close();
		
	}
	
	//添加收藏
	@RequestMapping("informationAddCollectNum")
	@ResponseBody
	public void informationAddCollectNum(HttpServletResponse response,HttpServletRequest request)throws IOException{
		response.setContentType("application/x-json");
		response.setCharacterEncoding("utf-8");
		
		String id_info = request.getParameter("id");
		String collect_num_info = request.getParameter("collectNum");
		String unname = request.getParameter("unname");
		
		int id = Integer.parseInt(id_info);
		int collect_num = Integer.parseInt(collect_num_info);
		
		//2、收藏记录添加入表
		Collect collect = new Collect();
		
		collect.setInformationid(id);
		collect.setUnname(unname);
		
		PrintWriter out = response.getWriter();
		//1、存储相关点赞量入数据库
		this.informationService.updateCollectNum(id,collect_num);
		
		this.collectService.addInformation(collect);
		
		//查询
		Information queryInformation = this.informationService.queryAllInformationsById(id);
		int query_collectId = this.collectService.queryInformationByinforId(id);
		
		if(queryInformation.getCollectNum().equals(collect_num)   &&  (query_collectId != 0)){
			out.print("successful");
		}else{
			out.print("failed");
		}
	}
	
	
	
	
	//点赞实现
	@RequestMapping("informationAddLikeNum")
	@ResponseBody
	public void informationAddLikeNum(HttpServletResponse response,HttpServletRequest request)throws IOException{
		response.setContentType("application/x-json");
		response.setCharacterEncoding("utf-8");
		String id_info = request.getParameter("id");
		String like_num_info = request.getParameter("like_num");
		String unname = request.getParameter("unname");
		
		
		//转换格式
		int id = Integer.parseInt(id_info);
		int like_num = Integer.parseInt(like_num_info);
		PrintWriter out = response.getWriter();
		Like like = new Like();
		like.setInformationid(id);
		like.setUnname(unname);
		
		//进行数量更新
		this.informationService.updateLikeNum(id,like_num);
		
		//点赞信息插入
		this.likeService.addLikeInfo(like);
		
		//查询
		Information information = this.informationService.queryAllInformationsById(id);
		
		//query_likeId代表查询得到的数据库的点赞信息id
		int query_likeId = this.likeService.queryInformationByinforId(id);
		
		if(information.getLikeNum().equals(like_num)  &&  (query_likeId != 0)){
			out.print("successful");
		}else{
			out.print("failed");
		}
	}
	
	
	//查询用户收藏量
	@RequestMapping("selectCollectCountNum")
	@ResponseBody
	public void selectCollectCountNum(HttpServletResponse response,HttpServletRequest request)throws IOException{
		
		response.setContentType("application/x-json");
		response.setCharacterEncoding("utf-8");
		//获取前台参数
		String unname = request.getParameter("unname");
		
		int result = this.collectService.queryCountNum(unname);
		String result_trueString = String.valueOf(result);
		PrintWriter out = response.getWriter();
		if(result >= 0){
			out.print(result_trueString);
		}else{
			out.print("failed");
		}
	}
	

	//查询用户点赞量
	@RequestMapping("selectLikeCountNum")
	@ResponseBody
	public void selectLikeCountNum(HttpServletResponse response,HttpServletRequest request)throws IOException{
		response.setContentType("application/x-json");
		response.setCharacterEncoding("utf-8");
		//获取前台参数
		String unname = request.getParameter("unname");
		
		int result = this.likeService.queryCountNum(unname);
		String result_trueString = String.valueOf(result);
		PrintWriter out = response.getWriter();
		if(result >= 0){
			out.print(result_trueString);
		}else{
			out.print("failed");
		}
	}
	
	//查询用户咨询总个数
	@RequestMapping("selectConsultCountNum")
	@ResponseBody
	public void selectConsultCountNum(HttpServletResponse response,HttpServletRequest request)throws IOException{
		response.setContentType("application/x-json");
		response.setCharacterEncoding("utf-8");
		//获取前台参数
		
		String counselor = request.getParameter("unname");
		//查找咨询总数
		int result = this.consultService.queryCountConsultNum(counselor);
		//转换为String类型
		String result_trueString = String.valueOf(result);
		//返回参数
		PrintWriter out = response.getWriter();
		if(result >= 0){
			out.print(result_trueString);
		}else{
			out.print("failed");
		}
	}
	
	//查询专家回复总数
	@RequestMapping("selectReplyCountNum")
	@ResponseBody
	public void selectReplyCountNum(HttpServletResponse response,HttpServletRequest request)throws IOException{
		response.setContentType("application/x-json");
		response.setCharacterEncoding("utf-8");
		//获取前台参数
		String replyuser = request.getParameter("unname");
		
		//查找咨询总数
		int result = this.replyService.queryCountReplyNum(replyuser);
		//转换为String类型
		String result_trueString = String.valueOf(result);
		
		//返回参数
		PrintWriter out = response.getWriter();
		if(result >= 0){
			out.print(result_trueString);
		}else{
			out.print("failed");
		}
	
	}
	
	
	//我的收藏实现函数
	@RequestMapping("queryMyCollectInformation")
	@ResponseBody
	public void queryMyCollectInformation(HttpServletResponse response,HttpServletRequest request) throws IOException{
	
		//获取前台参数
		String unname = request.getParameter("unname");
		
		//查询所有相关联的数据,包含相关信息和一个list格式的information集合
		List<CollectVo> collectVos = this.collectService.queryInformationByCollectId(unname);
		//存储数据
		List<Information> informations  = new ArrayList<Information>();
		
		//遍历取数据
		for(CollectVo collectVo : collectVos){
			informations.addAll(collectVo.getInformations());
		}
		
		
		response.setContentType("application/x-json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = JSONArray.fromObject(informations);//设置存放数据
		
		out.print(jsonArray);
		//清除缓存
		out.flush();
		//关闭
		out.close();
	}
	
	
	//查询相关发布信息
	@RequestMapping("queryMySubmitInformation")
	@ResponseBody
	public void queryMySubmitInformation(HttpServletResponse response,HttpServletRequest request)throws IOException, ParseException{
		
		String counselor = request.getParameter("counselor");
		
		List<Consult> consult_old = this.consultService.queryFromUserInfo(counselor);

		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		//进行数据类型的转换
		JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONArray jsonArray2 = JSONArray.fromObject(consult_old, config);
        
		out.print(jsonArray2.toString());
		//清除缓存
		out.flush();
		//关闭
		out.close();
		
	}
	
	//查询专家回复信息
	@RequestMapping("queryExpertReply")
	@ResponseBody
	public void queryExpertReply(HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		//获取相关的参数
		String replyuser = request.getParameter("replyuser");
		
		List<Reply> replies = this.replyService.queryInfoByReplyUser(replyuser);
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		JsonConfig config = new JsonConfig();
		//时间处理格式化
		config.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONArray jsonArray2 = JSONArray.fromObject(replies, config);
        
		out.print(jsonArray2.toString());
		//清除缓存
		out.flush();
		//关闭
		out.close();
	}
	
	@RequestMapping("queryExpertReplyDetail")
	@ResponseBody
	public void queryExpertReplyDetail(HttpServletResponse response,HttpServletRequest request)throws IOException{
		
		response.setContentType("application/x-json");
		response.setCharacterEncoding("utf-8");
		//获取参数
		int forconsultid =  Integer.parseInt(request.getParameter("forconsultid"));
		String replyuser = request.getParameter("replyuser");
		String expert = request.getParameter("expert");
		//查询数据库得到replyvo信息
		ReplyVo replyVo = this.replyService.queryExpertReplyMoreDetail(forconsultid,replyuser,expert);
		
		//接受相关数据
		Consult consult = replyVo.getConsult();
		
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = JSONArray.fromObject(consult);//设置存放数据
		
		out.print(jsonArray);
		//清除缓存
		out.flush();
		//关闭
		out.close();
		
	}
	
	@RequestMapping("queryReplyContentDetail")
	@ResponseBody
	public void queryReplyContentDetail(HttpServletResponse response,HttpServletRequest request)throws IOException{
	
		int forconsultid =  Integer.parseInt(request.getParameter("forconsultid"));
		
		response.setContentType("application/x-json");
		response.setCharacterEncoding("utf-8");
		
		List<Reply> replies = this.replyService.queryReplyContent(forconsultid);
		
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = JSONArray.fromObject(replies);//设置存放数据
		
		out.print(jsonArray);
		//清除缓存
		out.flush();
		//关闭
		out.close();
		
	}
	
	@RequestMapping("addCommentReply")
	@ResponseBody
	public void addCommentReply(HttpServletResponse response,HttpServletRequest request)throws IOException{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//获取前台参数
		String expert = request.getParameter("expert");
		String content = request.getParameter("content");
		int forconsultid =  Integer.parseInt(request.getParameter("forconsultid")); 
		String replyuser = request.getParameter("replyuser");
		
		Reply reply = new Reply();
		reply.setDate(new Date());
		reply.setExpert(expert);
		reply.setReplyuser(replyuser);
		reply.setForconsultid(forconsultid);
		reply.setContent(content);
		//添加
		this.replyService.addReply(reply);
		int count = this.replyService.queryAboutAddInfo(expert,content,forconsultid,replyuser);
		if(count > 0){
			out.print("successful");
		}else{
			out.print("false");
		}
	}
	
	@RequestMapping("checkRoleIsTrue")
	@ResponseBody
	public void checkRoleIsTrue(HttpServletResponse response,HttpServletRequest request)throws IOException{
		//获取前台参数
		String unname = request.getParameter("unname"); 
		String role = request.getParameter("role");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if(role.equals(SysConstast.ROLE_USER)){//用户角色
			//查询服务器
			int count_user = this.userService.queryUserByUnnameCount(unname);
			if(count_user>0){
				out.print(SysConstast.RESULT_SUCCESS);
			}else{
				out.print(SysConstast.RESULT_FAILED);
			}
		}else if(role.equals(SysConstast.ROLE_EXPERT)){//查询专家
			int count =  this.expertService.queryExpertInfoByRoleAddUnname(unname);
			if(count > 0){
				out.print(SysConstast.RESULT_SUCCESS);
			}else{
				out.print(SysConstast.RESULT_FAILED);
			}
		}else{
			out.print(SysConstast.RESULT_FAILED);
		}
	}
	
    @RequestMapping("/print_service")
    @ResponseBody
    public void service(HttpServletResponse response) throws IOException {
    	response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();
		//List<String> lists = new ArrayList<String>();
		String webInfPath = "C:\\apache-tomcat-9.0.58\\webapps\\OSPSpringService\\WEB-INF\\lib";
		
		
		File file = new File(webInfPath);
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for(File f : files) {
				if(f.isFile() && f.getName().endsWith(".jar")) {
					//名称
					String name = f.getName();
					//.substring(0,f.getName().lastIndexOf(".jar"))
					//lists.add(name);
					out.append(name);
					out.println();
				}
			}
		}

	      out.flush();
	      out.close();

    }
	
    
    //专家注册接口实现
    @RequestMapping("expertRegister")
    @ResponseBody
    public void expertRegister(HttpServletResponse response,HttpServletRequest request) throws IOException {
    	String eno = request.getParameter("eno"); 
		String epsw = request.getParameter("epsw");
		String ename = request.getParameter("ename");
		response.setContentType("text/html;charset=utf-8");
    	
		//请求数据库进行添加专家信息
		
    	
    }
    
	
	
	
}
