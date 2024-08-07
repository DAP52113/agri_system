package com.sxt.sys.service;

import java.util.List;

import com.sxt.sys.domain.Menu;
import com.sxt.sys.vo.MenuVo;

/*
 * 菜单管理的服务接口
 * */
public interface MenuService {

	// 查询所有菜单
	public List<Menu> queryAllMenuForList(MenuVo menuVo);
}
