package com.sxt.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;





import org.eclipse.jdt.internal.compiler.parser.diagnose.DiagnoseParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;





import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.domain.User;
import com.sxt.sys.mapper.UserMapper;
import com.sxt.sys.service.UserService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.RedisUtils;
import com.sxt.sys.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	

	@Resource
	private RedisUtils redisUtils;
	
	@Override
	public User loginUser(UserVo userVo) {
		// TODO Auto-generated method stub
		return userMapper.loginUser(userVo);
	}

	//用户信息查询
	@Override
	public DataGridView queryAllUser(UserVo userVo) {
		// 使用分页查询
		Page<Object> page = PageHelper.startPage(userVo.getPage(),userVo.getLimit());
		List<User> data =  this.userMapper.queryAllUser(userVo);//函数查询
		
		return new DataGridView(page.getTotal(),data);//调用数据数目
	}

	@Override
	public void addUser(UserVo userVo) {
		//设置默认密码
		
		this.userMapper.insertSelective(userVo);
		
		
	}

	@Override
	public void updateUser(UserVo userVo) {
		// TODO Auto-generated method stub
		this.userMapper.updateByPrimaryKeySelective(userVo);
		
	}

	@Override
	public void deleteUser(Integer id) {
		// 删除用户
		this.userMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public void deleteBatchUser(Integer[] ids) {
		// 批量删除
		for(Integer id : ids){
			this.deleteUser(id);
		}
		
	}

	@Override
	public void resetUserPwd(Integer id) {
		// 重置密码
		User user = new User();
		user.setId(id);
		user.setUpsw(SysConstast.USER_DEFAULT_PWD);
		// 更新
		this.userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public User queryAllUserById(Integer id) {
		// TODO Auto-generated method stub
		return this.userMapper.selectByPrimaryKey(id);
	}

	@Override
	public User queryAllUserByUnname(String unname) {
		// 用户名查询用户信息
		return this.userMapper.queryAllUserByUnname(unname);
	}

	@Override
	public List<User> selectAllFriendsExMy(String unname) {
		// 查询自身好友信息
		return this.userMapper.queryAllFriendsExme(unname);
	}

	@Override
	public String queryUserByUnname(String unname) {
		// TODO Auto-generated method stub
		return this.userMapper.queryUserByUnname(unname);
	}

	@Override
	public User queryUserByUsernameCheck(String unname) {
		// TODO Auto-generated method stub
		System.out.println("在mapper下的"+unname);
		return this.userMapper.queryUserByUsernameCheck(unname);
	}

	@Override
	@Transactional
	public boolean addUserRegister(User user) {
		// TODO Auto-generated method stub
		
		//信息存入redis  user转化为jsonobject
		Boolean result =  redisUtils.set(user.getUnname(), JSON.toJSONString(user));
		if(result.equals(true)){
			this.userMapper.insertSelective(user);
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public User findAndroidLogin(String unname, String upsw) {
		// TODO Auto-generated method stub
		return this.userMapper.findAndroidLogin(unname,upsw);
	}

	@Override
	public User checkusername(String unname) {
		// TODO Auto-generated method stub
		return this.userMapper.checkusername(unname);
	}

	@Override
	public int queryUserByUnnameCount(String unname) {
		// TODO Auto-generated method stub
		return this.userMapper.queryUserByUnnameCount(unname);
	}

}
