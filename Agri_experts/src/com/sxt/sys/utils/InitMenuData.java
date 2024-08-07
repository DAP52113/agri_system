package com.sxt.sys.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sxt.sys.domain.Menu;
import com.sxt.sys.mapper.MenuMapper;

/*
 * 初始化菜单
 * */
public class InitMenuData {

	public  static void main(String[] args){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		
		
		MenuMapper menuMapper = applicationContext.getBean(MenuMapper.class);
		//一级目录
		menuMapper.insert(new Menu(1, 0, "农业专家系统", null, 1, 1, "&#xe68e", null, 1));
		menuMapper.insert(new Menu(2, 1, "基础管理", null, 1, 0, "&#xe621", null, 1));
		menuMapper.insert(new Menu(3, 1, "业务管理", null, 1, 0, "&#xe634", null, 1));
		menuMapper.insert(new Menu(4, 1, "系统管理", null, 1, 0, "&#xe716", null, 1));
		menuMapper.insert(new Menu(5, 1, "数据分析", null, 0, 0, "&#xe62a", null, 1));
		
		//二级目录
		menuMapper.insert(new Menu(6, 2, "用户管理", null, 0, 0, "&#xe612", null, 1));
		menuMapper.insert(new Menu(7, 2, "专家管理", null, 0, 0, "&#xe770", null, 1));
		
		menuMapper.insert(new Menu(8, 3, "信息审核", null, 0, 0, "&#xe655", null, 1));
		
		
		menuMapper.insert(new Menu(9, 4, "系统公告", null, 0, 0, "&#xe609", null, 1));
		menuMapper.insert(new Menu(10, 4, "修改密码", null, 0, 0, "&#xe683", null, 1));
		menuMapper.insert(new Menu(11, 4, "日志管理", null, 0, 0, "&#xe609", null, 1));
		
		menuMapper.insert(new Menu(12, 5, "专家地区统计", null, 0, 0, "&#xe67b", null, 1));
		menuMapper.insert(new Menu(13, 5, "咨询地区统计", null, 0, 0, "&#xe6b1", null, 1));
		
		System.out.println("初始化完成！！！");
	}
	
}
