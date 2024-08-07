package com.sxt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxt.sys.domain.Menu;
import com.sxt.sys.mapper.MenuMapper;
import com.sxt.sys.service.MenuService;
import com.sxt.sys.vo.MenuVo;

@Service
public class MenuServiceImpl implements MenuService {
	
	
	@Autowired
	private MenuMapper menuMapper;

	@Override
	public List<Menu> queryAllMenuForList(MenuVo menuVo) {
		// TODO Auto-generated method stub
		return menuMapper.queryAllMenu(menuVo);
	}

}
