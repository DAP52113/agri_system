package com.sxt.sys.controller;


import java.util.ArrayList;
import java.util.List;

import net.sf.jsqlparser.util.AddAliasesVisitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.domain.Menu;
import com.sxt.sys.service.MenuService;
import com.sxt.sys.utils.TreeNode;
import com.sxt.sys.vo.MenuVo;

/*
 * 菜单管理控制器
 * */
@RestController
@RequestMapping("menu")
public class MenuController {

	
	@Autowired
	private MenuService menuService;//注入service服务类接口
	
	@RequestMapping("loadIndexLeftMenuJson")
	public List<TreeNode> loadIndexLeftMenuJson(MenuVo menuVo){
		
		//调用没有mybaitis进行查询
		List<Menu> list  = null;
		menuVo.setAvailable(SysConstast.AVAILABLE_TRUE);
		list = this.menuService.queryAllMenuForList(menuVo);
		
		//将list中的数据存在一个nodes链表结构中
		// 当前存储的为链表结构无树形数据结构
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		
		for(Menu menu : list){
			Integer id = menu.getId();
			Integer pid = menu.getPid();
			
			String title =menu.getTitle();
			
			String href = menu.getHref();
			Integer open = menu.getOpen();
			String icon = menu.getIcon();
			String target = menu.getTarget();
			
			nodes.add(new TreeNode(id, pid, title, href, open, icon, target));
		}
	
		
		//将nodes中的数据变为树形数据
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		
		for(TreeNode n1 : nodes){
			if(n1.getPid()==1){
				treeNodes.add(n1);//若pid为1则为根节点
			}
			for(TreeNode n2 : nodes){
				if(n2.getPid()==n1.getId()){
					n1.getChildren().add(n2);
				}
			}
		}

		return treeNodes;
	}


	
	
}
