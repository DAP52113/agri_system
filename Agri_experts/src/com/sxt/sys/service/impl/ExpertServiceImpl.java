package com.sxt.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.domain.Expert;
import com.sxt.sys.domain.User;
import com.sxt.sys.mapper.ExpertMapper;
import com.sxt.sys.service.ExpertService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.RedisUtils;
import com.sxt.sys.vo.ExpertVo;

@Service
public class ExpertServiceImpl implements ExpertService {

	@Autowired
	private ExpertMapper expertMapper;
	
	@Resource
	private RedisUtils redisUtils;
	
	@Override
	public Expert loginExpert(ExpertVo expertVo) {
		// TODO Auto-generated method stub
		return expertMapper.loginExpert(expertVo);
	}

	@Override
	public DataGridView queryAllExpert(ExpertVo expertVo) {
		// 使用分页查询
		Page<Object> page = PageHelper.startPage(expertVo.getPage(),expertVo.getLimit());
		List<Expert> data =  this.expertMapper.queryAllExpert(expertVo);//函数查询
				
	    return new DataGridView(page.getTotal(),data);//调用数据数目
	}

	@Override
	public void addExpert(ExpertVo expertVo) {
		// 添加专家信息
		this.expertMapper.insertSelective(expertVo);
	}

	@Override
	public void updateExpert(ExpertVo expertVo) {
		// 修改专家信息
		this.expertMapper.updateByPrimaryKeySelective(expertVo);
		
	}

	@Override
	public void deleteExpert(Integer eid) {
		// 删除专家信息
		this.expertMapper.deleteByPrimaryKey(eid);
		
	}

	@Override
	public void deleteBatchExpert(Integer[] eids) {
		// 批量删除专家信息
		for(Integer eid : eids){
			this.deleteExpert(eid);
		}
		
	}

	@Override
	public void resetExpertPwd(Integer eid) {
		// 重置专家用户密码
		Expert expert = new Expert();
		expert.setEid(eid);
		expert.setEpsw(SysConstast.USER_DEFAULT_PWD);
		//更新
		this.expertMapper.updateByPrimaryKeySelective(expert);
	}

	@Override
	public Expert queryAllExpertById(Integer eid) {
		// 查询专家信息(根据select查询)
		return this.expertMapper.selectByPrimaryKey(eid);
	}

	@Override
	public List<Expert> queryAllExpertsByUser() {
		// 用户查询专家信息
		return this.expertMapper.queryAllExpertByUser();
	}

	@Override
	public Expert selectExpertByEno(String eno) {
		// 查询原密码
		return this.expertMapper.queryExpertByEno(eno);
	}

	@Override
	public Expert queryExpertByEno(String eno) {
		// 专家名去查询数据
		return this.expertMapper.queryExpertByEno(eno);
	}

	@Override
	public Expert queryExpertGetArea(String eno) {
		// 根据登录名查询领域
		return this.expertMapper.queryExpertByEno(eno);
	}

	@Override
	public DataGridView loadAllExpert(ExpertVo expertVo) {
		// TODO Auto-generated method stub
		Page<Object> page = PageHelper.startPage(expertVo.getPage(), expertVo.getLimit());
		List<Expert> data =  this.expertMapper.queryAllExpert(expertVo);//函数查询
	    return new DataGridView(page.getTotal(),data);//调用数据数目
		
	}

	@Override
	@Transactional
	public boolean addExpertRegister(Expert expert) {
		// TODO Auto-generated method stub
		//是否存在该key
		try {
			boolean query_result = redisUtils.haskey(expert.getEno());
			boolean set_result = redisUtils.set(expert.getEno(), JSON.toJSONString(expert));
			if((!query_result) && set_result){
				this.expertMapper.insertSelective(expert);
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public List<Expert> queryAllExpertsByArea(String area) {
		// TODO Auto-generated method stub
		return this.expertMapper.queryAllExpertsByArea(area);
	}

	@Override
	public int queryExpertInfoByRoleAddUnname(String unname) {
		// TODO Auto-generated method stub
		String eno = unname; 
		return this.expertMapper.queryExpertInfoByRoleAddUnname(eno);
	}

}
