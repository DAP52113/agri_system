package com.sxt.sys.service;

import java.util.List;

import com.sxt.sys.domain.User;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.UserVo;

/*
 * 用户登录接口
 * */
public interface UserService {

 
	User loginUser(UserVo userVo);
	
	/**
	 * 查询所有用户
	 * @param 
	 * @return
	 */
	public DataGridView queryAllUser(UserVo userVo);
	/**
	 * 添加用户
	 * @param 
	 */
	public void addUser(UserVo userVo);

	/**
	 * 修改用户
	 * @param 
	 */
	public void updateUser(UserVo userVo);

	/**
	 * 根据id删除用户
	 * @param 
	 */
	public void deleteUser(Integer id);
	/**
	 * 批量删除用户
	 * @param 
	 */
	public void deleteBatchUser(Integer [] ids);
	
	//重置密码
	public  void resetUserPwd(Integer id);

	
	//根据id查询用户头像
	public  User queryAllUserById(Integer id);

	//根据用户名查询用户信息
	User queryAllUserByUnname(String unname);

	//查询所有的好友信息
	List<User> selectAllFriendsExMy(String unname);

	//根据账户查询用户
	String queryUserByUnname(String unname);

	//检测是否存在相同的用户名
	User queryUserByUsernameCheck(String unname);

	//用户注册
	boolean addUserRegister(User user);

	User findAndroidLogin(String unname, String upsw);

	//安卓端检测用户名是否存在
	User checkusername(String unname);

	int queryUserByUnnameCount(String unname);
	
	
	
}
