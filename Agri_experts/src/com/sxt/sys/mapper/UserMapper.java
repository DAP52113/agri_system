package com.sxt.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sxt.sys.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    //用户登录方法
    User loginUser(User user);
    
    //查询用户信息
    List<User> queryAllUser(User user);

    //查询用户信息根据用户名去查询
	User queryAllUserByUnname(String unname);

	//查询除了自身以外的用户信息
	List<User> queryAllFriendsExme(String unname);

	//根据账号查询用户
	String queryUserByUnname(String unname);
	
	//根据用户名去检测数据库是否存在相同的数值
	User queryUserByUsernameCheck(String unname);

	//安卓客户端进行登录接口
	User findAndroidLogin(@Param("unname")String unname, @Param("upsw")String upsw);

	//安卓客户端进行用户名检测
	User checkusername(@Param("unname")String unname);

	int queryUserByUnnameCount(@Param("unname")String unname);
}