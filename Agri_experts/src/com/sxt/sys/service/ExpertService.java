package com.sxt.sys.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.sxt.sys.domain.Expert;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.ExpertVo;



/*
 * 专家角色登录接口
 * */

public interface ExpertService {

	Expert loginExpert(ExpertVo expertVo);
	
	/**
	 * 查询所有专家
	 * @param 
	 * @return
	 */
	public DataGridView queryAllExpert(ExpertVo expertVo);
	/**
	 * 添加专家
	 * @param 
	 */
	public void addExpert(ExpertVo expertVo);

	/**
	 * 修改专家
	 * @param 
	 */
	public void updateExpert(ExpertVo expertVo);

	/**
	 * 根据id删除专家信息
	 * @param 
	 */
	public void deleteExpert(Integer eid);
	/**
	 * 批量删除专家
	 * @param 
	 */
	public void deleteBatchExpert(Integer [] eids);
	
	//重置密码
	public  void resetExpertPwd(Integer eid);

	
	//根据id查询用户头像
	public  Expert queryAllExpertById(Integer eid);

	//用户操作--查询专家信息
	List<Expert> queryAllExpertsByUser();

	//查询原密码
	Expert selectExpertByEno(String eno);

	//根据专家名去查询数据
	Expert queryExpertByEno(String eno);

	//根据专家登录名查询所属领域
	Expert queryExpertGetArea(String eno);

	//远程问诊查询专家
	DataGridView loadAllExpert(ExpertVo expertVo);

	//专家注册
	boolean addExpertRegister(Expert expert);

	//android端根据专家领域查询专家
	List<Expert> queryAllExpertsByArea(String area);

	int queryExpertInfoByRoleAddUnname(String unname);
	
	
}
