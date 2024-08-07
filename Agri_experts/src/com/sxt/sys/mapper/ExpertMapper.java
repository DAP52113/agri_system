package com.sxt.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.sxt.sys.domain.Expert;

public interface ExpertMapper {
    int deleteByPrimaryKey(Integer eid);

    int insert(Expert record);

    int insertSelective(Expert record);

    Expert selectByPrimaryKey(Integer eid);

    int updateByPrimaryKeySelective(Expert record);

    int updateByPrimaryKey(Expert record);
    
    /*
     * 专家角色登录方法
     * */
    Expert loginExpert(Expert expert);
    
    /*
     * 查询所有专家用户
     * */
    List<Expert> queryAllExpert(Expert expert);

    //用户查询专家信息
	List<Expert> queryAllExpertByUser();

	//查询原密码
	Expert queryExpertByEno(String eno);

	//android端根据专家领域查询数据
	List<Expert> queryAllExpertsByArea(@Param("area")String area);

	int queryExpertInfoByRoleAddUnname(@Param("eno")String eno);

	
    
    
    
    
}