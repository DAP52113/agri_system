package com.sxt.Auser.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sxt.Auser.domain.Consult;

public interface ConsultMapper {
    int deleteByPrimaryKey(Integer cno);

    int insert(Consult record);

    int insertSelective(Consult record);
 
    Consult selectByPrimaryKey(Integer cno);

    int updateByPrimaryKeySelective(Consult record);

    int updateByPrimaryKey(Consult record);

	List<Consult> queryAllConsults();

	List<Consult> queryAllConsultsByArea(String area);

	Consult queryAllConsultsByTitle(@Param("title")String title);

	int queryCountConsultNum(@Param("counselor")String counselor);

	List<Consult> queryFromUserInfo(@Param("counselor")String counselor);
}