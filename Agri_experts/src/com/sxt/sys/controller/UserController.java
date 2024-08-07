package com.sxt.sys.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.domain.User;
import com.sxt.sys.service.UserService;
import com.sxt.sys.utils.AppFileUtils;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;
import com.sxt.sys.utils.WebUtils;
import com.sxt.sys.vo.UserVo;

/*
 * 用户管理控制器
 * */
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	/*
	 * 加载用户列表并返回DataView
	 * */
	@RequestMapping("loadAllUser")
	public DataGridView loadAllUser(UserVo userVo){
		DataGridView userVos = this.userService.queryAllUser(userVo);
		System.out.println("userVos:::::::::::"+userVos.getData());
		return this.userService.queryAllUser(userVo);// 查询所有的用户
		
	}
	
	
	/*
	 *添加用户功能
	 **/
	@RequestMapping("addUser")
	public  ResultObj addUser(UserVo userVo,HttpServletRequest request,HttpServletResponse response){
		try {
			
			this.userService.addUser(userVo);
			return ResultObj.ADD_SUCCESS;//添加成功
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;//添加失败
		}
		
	}
	
	
	/*
	 * 修改用户功能
	 * */
	@RequestMapping("updateUser")
	public ResultObj updateUser(UserVo userVo){
		try {
			String uphoto = userVo.getUphoto();// 获取图片
			System.out.println("uphoto::::"+uphoto);
		    if(uphoto.endsWith(SysConstast.FILE_UPLOAD_TEMP)){
		    	String filePath = AppFileUtils.updateFileName(userVo.getUphoto(),SysConstast.FILE_UPLOAD_TEMP);
		    	System.out.println("要存入数据库的图片地址::::"+filePath);
				userVo.setUphoto(filePath);// 设置新的地址
				
				//原来的图片删除
				System.out.println("所要修改图片的id为："+userVo.getId());
				User user =  this.userService.queryAllUserById(userVo.getId());
				System.out.println("要删除的图片为："+user.getUphoto());
				AppFileUtils.removeFileByPath(user.getUphoto());
		    }
			

			this.userService.updateUser(userVo);
			return ResultObj.UPDATE_SUCCESS;// 修改成功
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;// 修改错误
		}
	}
	
	
	/*
	 * 删除用户功能
	 * */
	@RequestMapping("deleteUser")
	public  ResultObj deleteUser(Integer id){
		try {
			this.userService.deleteUser(id);
			return ResultObj.DELETE_SUCCESS;//删除成功
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;// 删除失败
		}
	}
	
	/*
	 * 批量删除用户
	 * */
	@RequestMapping("deleteBatchUser")
	public ResultObj deleteBatchUser(UserVo userVo){
		try {
			this.userService.deleteBatchUser(userVo.getIds());
			return ResultObj.DELETE_SUCCESS;// 删除成功
		} catch (Exception e) {
			e.printStackTrace();
			return  ResultObj.DELETE_ERROR;// 删除失败
		}
	}
	
	
	/*
	 * 重置密码
	 * */
	@RequestMapping("resetUserPwd")
	public ResultObj resetUserPwd(UserVo userVo){
		try {
			this.userService.resetUserPwd(userVo.getId());
			return ResultObj.RESET_SUCCESS;// 重置成功
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.RESET_ERROR; // 重置失败
		}
	}
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
