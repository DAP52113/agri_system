package com.sxt.sys.constast;

/*
 * 常亮接口
 * */


public interface SysConstast {

	String ADMIN_LOGIN_ERROR_MSG = "用户名、密码或用户类型不正确";
	String ADMIN_LOGIN_CODE_ERROR_MSG="验证码不正确";
	//可用菜单状态
	Integer AVAILABLE_TRUE = 1;
	String USER_DEFAULT_PWD = "123456";//默认密码配置

	String ROLE_USER="用户";
	String ROLE_EXPERT="农业专家";
	String RESULT_SUCCESS="success";
	String RESULT_FAILED="failed";
	
	/**
	 * 操作状态
	 */
	String ADD_SUCCESS="添加成功";
	String ADD_ERROR="添加失败";
	
	String UPDATE_SUCCESS="更新成功";
	String UPDATE_ERROR="更新失败";
	
	String DELETE_SUCCESS="删除成功";
	String DELETE_ERROR="删除失败";
	
	String RESET_SUCCESS="重置成功";
	String RESET_ERROR="重置失败";
	
	String UPLOAD_SUCCESS="上传成功";
	String UPLOAD_ERROR ="上传失败";
	
	Integer CODE_SUCCESS=0; //操作成功
	Integer CODE_ERROR=-1;//失败
	/**
	 * 临时文件标记
	 */
	String FILE_UPLOAD_TEMP = "_temp";
	String REDIS_USER_INFO="user";
	
	//聊天项目重定向地址
	String WEBCHATADDRESS="http://localhost:8080/webchat/user/login";
	
	String WEBCHATADDRESS_EXPERT="http://localhost:8080/webchat/user/expertlogin";
	
	String USER_ROLE="用户";
	String EXPERT_ROLE="农业专家";
	
}
