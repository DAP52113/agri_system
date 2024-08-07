package com.sxt.sys.service;

import com.sxt.sys.domain.Admin;
import com.sxt.sys.vo.AdminVo;
/*
 * 管理员服务接口
 * */
public interface AdminService {

		Admin login(AdminVo adminVo);

		//旧密码查询
		Admin queryAllByPwd(String adminpsw);

		//根据用户名查询密码
		Admin queryAllByAdminName(String adminname);

		//修改密码
		void updateAdminByPwd(AdminVo adminVo);

		void updateAdminByPwd(Admin admin);
	
}
