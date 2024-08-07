package com.sxt.Auser.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sxt.Auser.domain.Collect;
import com.sxt.Auser.vo.CollectVo;
//收藏接口
public interface CollectMapper {
    int deleteByPrimaryKey(Integer collectid);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer collectid);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

	int queryInformationByinforId(@Param("informationid")int id);

	
	void addInformation(@Param("informationid")int id, @Param("unname")String unname);

	int queryCollectNum(@Param("unname")String unname);

	//查询用户收藏的所有信息
	List<CollectVo> queryInformationByCollectId(@Param("unname")String unname);
	
}