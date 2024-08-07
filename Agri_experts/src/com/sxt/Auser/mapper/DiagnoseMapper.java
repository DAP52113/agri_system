package com.sxt.Auser.mapper;

import java.util.List;

import com.sxt.Auser.domain.Diagnose;

public interface DiagnoseMapper {
    int deleteByPrimaryKey(Integer dno);

    int insert(Diagnose record);

    int insertSelective(Diagnose record);

    Diagnose selectByPrimaryKey(Integer dno);

    int updateByPrimaryKeySelective(Diagnose record);

    int updateByPrimaryKey(Diagnose record);

	List<Diagnose> queryAllDiagnose();
}