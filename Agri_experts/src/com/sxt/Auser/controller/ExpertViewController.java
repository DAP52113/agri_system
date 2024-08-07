package com.sxt.Auser.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sxt.Auser.domain.Consult;
import com.sxt.Auser.domain.Diagnose;
import com.sxt.Auser.domain.Diagnosereply;
import com.sxt.Auser.domain.Reply;
import com.sxt.Auser.service.ConsultService;
import com.sxt.Auser.service.DiagnoseReplyService;
import com.sxt.Auser.service.DiagnoseService;
import com.sxt.Auser.service.ReplyService;
import com.sxt.Auser.utils.DateTrans;
import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.domain.Expert;
import com.sxt.sys.domain.Information;
import com.sxt.sys.domain.User;
import com.sxt.sys.service.ExpertService;
import com.sxt.sys.service.InformationService;
import com.sxt.sys.utils.WebUtils;
import com.sxt.sys.vo.ExpertVo;
import com.sxt.sys.vo.InformationVo;

/*
 * 专家角色界面跳转控制器
 * */
@Controller
@RequestMapping("expertView")
public class ExpertViewController {

	@Autowired
	private InformationService informationService;
	
	@Autowired
	private ExpertService expertService;
	
	@Autowired
	private ConsultService consultService;
	
	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private DiagnoseService diagnoseService;
	
	@Autowired
	private DiagnoseReplyService diagnoseReplyService;
	
	//跳转到首页界面
	@RequestMapping("Expertindex")
	public String Expertindex(Model model){
		return "expert/index";
	}
	
	//跳转到专家个人中心页面
	@RequestMapping("ExpertPersonCen")
	public String ExpertPersonCen(Model model){
		Expert expert =  (Expert)WebUtils.getHttpSession().getAttribute("expert");// 获取session的数据
		String eno = expert.getEno();// 获取专家登录名
		Expert newExpert =  this.expertService.queryExpertByEno(eno);
		model.addAttribute("newExpert", newExpert);
		return "expert/expertPersonalCenter";
	}
	
	//跳转到信息发布界面
	@RequestMapping("ExpertArticleSubmit")
	public String ExpertArticleSubmit(Model model){
		//查询数据库已审核信息
		List<Information> disableYesInformations  = this.informationService.queryAllInformationByUserNoLimit();
		model.addAttribute("disableYesInformations", disableYesInformations);
		//查询数据库未审核信息
		List<Information> disableNoInformations = this.informationService.queryAllInformationBydisable();
		model.addAttribute("disableNoInformations", disableNoInformations);
		return "expert/expertInfoSubmit";
	}
	
	//跳转到在线回复页面
	@RequestMapping("ExpertOnlineReply")
	public String ExpertOnlineReply(RedirectAttributes attributes,HttpServletRequest req, HttpServletResponse resp){
		Expert expertSession = (Expert)WebUtils.getHttpSession().getAttribute("expert");
		
		String username = expertSession.getEname();
		//为请求添加参数
		attributes.addAttribute("username", username);
		attributes.addAttribute("role", SysConstast.ROLE_EXPERT);
		return "redirect:"+SysConstast.WEBCHATADDRESS_EXPERT+"";
	}
	
	//跳转到远程回复页面
	@RequestMapping("ExpertDiagnose")
	public String ExpertDiagnose(Model model){
		//查询所有的远程咨询问题
		List<Diagnose> diagnoses = this.diagnoseService.queryAllDiagnoses();
		model.addAttribute("diagnoses", diagnoses);
		List<Diagnosereply> diagnosereplies = this.diagnoseReplyService.queryAllDiagnoseReplies();
		model.addAttribute("diagnosereplies", diagnosereplies);
		return "expert/expertDiagnose";
	}
	
	//完成远程一对一回复操作
	@RequestMapping("DiagnoseRepliesform")
	public String DiagnoseRepliesform(Diagnosereply diagnosereply,Model model){
		try {
			diagnosereply.setDate(new Date());
			this.diagnoseReplyService.addDiagnoseReply(diagnosereply);
			return "redirect:ExpertDiagnose.action";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("adderror", "回复失败");
			return "redirect:ExpertDiagnose.action";
		}
		
		
	}
	
	
	//进行密码修改操作
	@RequestMapping("ExpertSavePwd")
	public String ExpertSavePwd(ExpertVo expertVo,Model model){
		try {
			this.expertService.updateExpert(expertVo);
			Expert expert =  (Expert)WebUtils.getHttpSession().getAttribute("expert");// 获取session的数据
			String eno = expert.getEno();// 获取专家登录名
			Expert newExpert =  this.expertService.queryExpertByEno(eno);
			model.addAttribute("newExpert", newExpert);
			return "expert/expertPersonalCenter";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("updateError", "修改失败");
			return "expert/expertPersonalCenter";
		}
	}
	
	//实现信息发布功能
	@RequestMapping("ExpertMessageAddRequest")
	public String ExpertMessageAddRequest(Model model,InformationVo informationVo){
		try {
			Expert expertSession = (Expert)WebUtils.getHttpSession().getAttribute("expert");
			String eno = expertSession.getEno();
			informationVo.setEno(eno);
			System.out.println("时间为："+DateTrans.dateTr());
			informationVo.setDate(DateTrans.dateTr());
			this.informationService.addInformation(informationVo);//执行发布资讯操作
			showInformation(model);
			return "expert/expertInfoSubmit";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", "发布失败");
			return "expert/expertInfoSubmit";
		}
	}
	
	//实现专家在线回复功能
	@RequestMapping("RepliesformRequest")
	public String RepliesformRequest(Reply reply,Model model){
		try {
			reply.setDate(new Date());
			this.replyService.addReply(reply);
			//执行跳转指令直接使用redirect:加一个请求action
			return "redirect:ExpertOnlineReplyction";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("inserterror", "提交失败");
			return "redirect:ExpertOnlineReply.action";
		}
	
	}
	

	//单独的查询审核与未审核信息操作函数
	public  void showInformation(Model model){
		List<Information> disableYesInformations  = this.informationService.queryAllInformationByUserNoLimit();
		model.addAttribute("disableYesInformations", disableYesInformations);
		List<Information> disableNoInformations = this.informationService.queryAllInformationBydisable();
		model.addAttribute("disableNoInformations", disableNoInformations);
	}
	
	//跳转到专家用户在线聊天页面
	@RequestMapping("userToExpertCon")
	public String userToExpertCon(){
		
		return "chatview/chat_index";
	}
	
	
}
