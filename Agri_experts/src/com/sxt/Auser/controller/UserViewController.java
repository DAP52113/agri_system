package com.sxt.Auser.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sxt.Auser.domain.Consult;
import com.sxt.Auser.domain.Words;
import com.sxt.Auser.service.ConsultService;
import com.sxt.Auser.service.WordService;
import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.domain.Expert;
import com.sxt.sys.domain.Information;
import com.sxt.sys.domain.User;
import com.sxt.sys.service.ExpertService;
import com.sxt.sys.service.InformationService;
import com.sxt.sys.service.UserService;
import com.sxt.sys.utils.WebUtils;

/*
 * 用户模块界面跳转控制器
 * */
@Controller
@RequestMapping("userView")
public class UserViewController {

	@Autowired
	private InformationService informationService;
	
	@Autowired
	private ExpertService expertService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ConsultService consultService;
	
	@Autowired
	private WordService wordService;
	
	
	//跳转到用户角色主页面
	@RequestMapping("Userindex")
	public String Userindex(Model model,HttpServletRequest request){
		// 查询资讯信息
		List<Information> informations = informationService.queryAllInformationByUser();
		model.addAttribute("informations",informations);
		//查询专家信息
		List<Expert> experts = expertService.queryAllExpertsByUser();
	
		request.setAttribute("experts", experts);
		return "user/index";
	}
	//跳转到个人中心页面
	@RequestMapping("UserPersonCen")
	public String UserPersonCen(Model model,HttpSession session){
		//通过用户名去查询数据库的该用户的所有信息
		//获取session值
		User user = (User)WebUtils.getHttpSession().getAttribute("user");
		String unname = user.getUnname();//得到sesion中的用户名
		User newUser = this.userService.queryAllUserByUnname(unname);//数据库查询用户密码
		model.addAttribute("newUser",newUser);
		return "user/PersonalCenter";
	}
	
	//跳转到农业资讯页面
	@RequestMapping("UserArticle")
	public String UserArticle(Model model){
		List<Information> informations = informationService.queryAllInformationByUserNoLimit();
		model.addAttribute("informations",informations);
		
		List<Information> informationslimits = informationService.queryAllInformationByUser();
		model.addAttribute("informationslimits",informationslimits);
		
		// jsp截取字符串需要导入fn标签，同时使用${fn:substring("要切割的字段"),0,1}
		return "user/article";
	}
	
	@RequestMapping("UserConsultOnline")
	public String UserConsultOnline(RedirectAttributes attributes,HttpServletRequest req, HttpServletResponse resp){
		User userSession =  (User)WebUtils.getHttpSession().getAttribute("user");
		String username = userSession.getUnname();
		
		attributes.addAttribute("username", username);
		attributes.addAttribute("role", SysConstast.USER_ROLE);
		return "redirect:"+SysConstast.WEBCHATADDRESS+"";
	}
	
	//跳转到在线咨询页面
	@RequestMapping("UserOnlineMessage")
	public String UserOnlineMessage(Model model){
		List<Consult> consults = consultService.queryAllConsults(); // 查询数据库所有的咨询问题信息
		
		model.addAttribute("consults", consults);
		List<Words> wordsInfos = wordService.queryAllWords(); //查询所有的留言信息(针对文章的)
		model.addAttribute("wordsInfos", wordsInfos);
		
		return "user/OnlineMessage";
	}
	
	// 跳转到文章咨询详情页面
	@RequestMapping("UserArticleDetails")
	public String UserArticleDetails(String title,Model model){
		System.out.println("获取到的标题为:"+title);
		Information information  = this.informationService.selectAllInformationByTitle(title);
		model.addAttribute("information", information);
		return "user/articleDetails";
	}
	
	//用户在线咨询提交请求处理
	@RequestMapping("UserMessageAddRequest")
	public String UserMessageAddRequest(Consult consult,Model model){
		try {
			consult.setDate(new Date());//添加时间
			User user = (User)WebUtils.getHttpSession().getAttribute("user");
			consult.setCounselor(user.getUnname());
			this.consultService.addConsult(consult);
			//查询所有的操作
			List<Consult> consults = consultService.queryAllConsults();
			model.addAttribute("consults", consults);
			List<Words> wordsInfos = wordService.queryAllWords(); //查询所有的留言信息(针对文章的)
			model.addAttribute("wordsInfos", wordsInfos);
			return "user/OnlineMessage";// 添加成功
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", "哎呀，请求好像出错误了");
			return "user/OnlineMessage";//添加失败
		}
	}
	
	//用户提交回复表单处理操作
	@RequestMapping("WordsformRequest")
	public String WordsformRequest(Words words){
		words.setDate(new Date());//设置表单处理时间
		this.wordService.addReplyContent(words);
		//重定向实现关键的处理请求
		return "forward:../userView/UserOnlineMessage.action";
	}

	//用户远程问诊聊天界面实现
	@RequestMapping("userDiagnose")
	public String userDiagnose(){
		return "user/Userdiagnose";
	}
	//跳转到用户对专家的在线聊天页面
	@RequestMapping("userToExpertChat")
	public String userToExpertChat(){
		return "user/user_expert_con";
	}
	
	
}
