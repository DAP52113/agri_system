package com.sxt.Auser.mapper;

import java.util.List;

import com.sxt.Auser.domain.Diagnosereply;

public interface DiagnosereplyMapper {
    int deleteByPrimaryKey(Integer rid);

    int insert(Diagnosereply record);

    int insertSelective(Diagnosereply record);

    Diagnosereply selectByPrimaryKey(Integer rid);

    int updateByPrimaryKeySelective(Diagnosereply record);

    int updateByPrimaryKey(Diagnosereply record);

	List<Diagnosereply> queryAllDiagnoseReplies();
}