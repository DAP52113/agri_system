package com.sxt.sys.mapper;

import com.sxt.sys.domain.Admin;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    
    /*
     * 登录
     * */
    
    Admin login(Admin admin);

	Admin selectByPwd(String adminpsw);

	Admin selectAllByAdminName(String adminname);
}