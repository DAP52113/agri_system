package com.sxt.sys.utils;

import com.sxt.sys.constast.SysConstast;

public class ResultObj {

	private  Integer code = 0;
	private  String  msg;
	
	
	public ResultObj(Integer code) {
		super();
		this.code = code;
	}


	public ResultObj(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}


	public Integer getCode() {
		return code;
	}


	public void setCode(Integer code) {
		this.code = code;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	/*
	 * 添加成功
	 * */
	public static final ResultObj ADD_SUCCESS=new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.ADD_SUCCESS); 
	
	/*
	 * 添加失败
	 * */
	public static final ResultObj ADD_ERROR = new ResultObj(SysConstast.CODE_ERROR, SysConstast.ADD_ERROR);
	
	/*
	 * 更新成功
	 * */
	public static final ResultObj UPDATE_SUCCESS=new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.UPDATE_SUCCESS); 
	
	/*
	 * 更新失败
	 * */
	public static final ResultObj UPDATE_ERROR = new ResultObj(SysConstast.CODE_ERROR, SysConstast.UPDATE_ERROR);
	
	/*
	 * 删除成功
	 * */
	public static final ResultObj DELETE_SUCCESS=new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.DELETE_SUCCESS); 
	
	/*
	 * 删除失败
	 * */
	public static final ResultObj DELETE_ERROR = new ResultObj(SysConstast.CODE_ERROR, SysConstast.DELETE_ERROR);
	
	/*
	 * 重置成功
	 * */
	public static final ResultObj RESET_SUCCESS=new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.RESET_SUCCESS); 
	
	/*
	 * 重置失败
	 * */
	public static final ResultObj RESET_ERROR = new ResultObj(SysConstast.CODE_ERROR, SysConstast.RESET_ERROR);
	
	/*
	 * 上传成功
	 * */
	public static final ResultObj UPLOAD_SUCCESS=new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.UPLOAD_SUCCESS); 
	
	/*
	 * 上传失败
	 * */
	public static final ResultObj UPLOAD_ERROR = new ResultObj(SysConstast.CODE_ERROR, SysConstast.UPLOAD_ERROR);
	
	
	/**
	 * 状态码0
	 */
	public static final ResultObj STATUS_TRUE=new ResultObj(SysConstast.CODE_SUCCESS); 
	/**
	 * 状态码-1
	 */
	public static final ResultObj STATUS_FALSE=new ResultObj(SysConstast.CODE_ERROR); 
	
	
}
