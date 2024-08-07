package com.sxt.sys.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



//专家websocket信息格式化类
public class Msg {

	private String msgType;//消息类型
	private List<String> userlists;//用户列表
	private String msgOriginator;//信息发送者
	private String msgRecipient;//信息接收者
	private String msgInfo;//信息内容
	private java.util.Date msgDate;//信息发送时间
	private String msgDateStr;
	
	
	
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public List<String> getUserlists() {
		return userlists;
	}
	public void setUserlists(List<String> userlists) {
		this.userlists = userlists;
	}
	public String getMsgOriginator() {
		return msgOriginator;
	}
	public void setMsgOriginator(String msgOriginator) {
		this.msgOriginator = msgOriginator;
	}
	public String getMsgRecipient() {
		return msgRecipient;
	}
	public void setMsgRecipient(String msgRecipient) {
		this.msgRecipient = msgRecipient;
	}
	public String getMsgInfo() {
		return msgInfo;
	}
	public void setMsgInfo(String msgInfo) {
		this.msgInfo = msgInfo;
	}
	public java.util.Date getMsgDate() {
		return msgDate;
	}
	public void setMsgDate(java.util.Date msgDate) {
		this.msgDate = msgDate;
	}
	public String getMsgDateStr() {
		return msgDateStr;
	}
	//设置时间格式
	public void setMsgDateStr(Date msgDateStr) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.msgDateStr = format.format(msgDateStr);
	}
	
	
	
}
