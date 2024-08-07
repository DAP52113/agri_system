package com.sxt.sys.utils;

import java.util.ArrayList;
import java.util.List;

/*
 * 菜单数据结构-树结构实现
 * */
public class TreeNode {

	private  Integer id;
	private  Integer pid;
	private  String title;
	private  String href;
	private  Integer open;
	private  String icon;
	private  String target;
	private  List<TreeNode> children = new ArrayList<TreeNode>();
	
	
	//首页左边导航构造器
	public TreeNode(Integer id, Integer pid, String title, String href,
			Integer open, String icon, String target) {
		super();
		this.id = id;
		this.pid = pid;
		this.title = title;
		this.href = href;
		this.open = open;
		this.icon = icon;
		this.target = target;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public Integer getOpen() {
		return open;
	}
	public void Integer(Integer open) {
		this.open = open;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	
	
	
} 
