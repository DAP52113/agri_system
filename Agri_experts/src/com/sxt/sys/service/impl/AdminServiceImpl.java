package com.sxt.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.sxt.sys.domain.Admin;
import com.sxt.sys.mapper.AdminMapper;
import com.sxt.sys.service.AdminService;
import com.sxt.sys.vo.AdminVo;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;
	
	
	@Override
	public Admin login(AdminVo adminVo) {
		// TODO Auto-generated method stub
		//明文
		//密文生成
		//String  adminpsw =  DigestUtils.md5DigestAsHex(adminVo.getAdminpsw().getBytes());
		//adminVo.setAdminpsw(adminpsw);
		
		return adminMapper.login(adminVo);
	}


	@Override
	public Admin queryAllByPwd(String adminpsw) {
		// 查询密码
		return adminMapper.selectByPwd(adminpsw);
	}


	@Override
	public Admin queryAllByAdminName(String adminname) {
		// TODO Auto-generated method stub
		return adminMapper.selectAllByAdminName(adminname);
	}


	@Override
	public void updateAdminByPwd(Admin admin) {
		
		this.adminMapper.updateByPrimaryKeySelective(admin);
		
	}


	@Override
	public void updateAdminByPwd(AdminVo adminVo) {
		// TODO Auto-generated method stub
		this.adminMapper.updateByPrimaryKeySelective(adminVo);
	}

}
