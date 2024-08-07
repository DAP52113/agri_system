package com.sxt.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sxt.sys.domain.Information;
import com.sxt.sys.utils.DataGridView;

public interface InformationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Information record);

    int insertSelective(Information record);

    Information selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKey(Information record);
    
    //查询所有咨询内容
    List<Information> queryAllInformations(Information information);

    //用户操作查询所有咨询内容
	List<Information> queryAllInformationsByUser();

	//根据信息id去查询
	Information queryAllInformationsById(Integer id);

	//无限制的查询信息
	List<Information> queryAllInformationByUserNolimit();

	// 根据标题去查询标题
	 List<Information> queryAllInformationByTitle(String title);

	// 根据标题去查询数据库
	Information selectAllInformationByTitle(String title);

	//分页查询
	List<Information> selectAllinformationWithLimit(@Param("start")Integer start,
			@Param("pagesize")Integer pagesize);

	//查询未审核信息
	List<Information> queryAllInformationByDisable();

	//查询标题信息
	List<Information> queryInfoByTitle(String title);

	//查询标题，领域
	List<Information> queryInfoGettitleAndarea();

	//收藏
	void updateCollectNum(@Param("id")int id, @Param("collectNum")int collect_num);

	//点赞
	void updateLikeNum(@Param("id")int id, @Param("likeNum")int like_num);

	

	
    
}