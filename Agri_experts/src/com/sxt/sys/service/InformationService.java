package com.sxt.sys.service;

import java.util.List;

import com.sxt.sys.domain.Information;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.InformationVo;


/*
 * 信息咨询服务类
 * */

public interface InformationService {

	//查询所有的信息
	public DataGridView queryAllInformation(InformationVo informationVo);
	
	
	//修改咨询信息
	public void updateInformation(InformationVo informationVo);
	
	//根据信息id删除信息
	public void deleteInformation(Integer id);
	
	//批量删除咨询信息
	public void deleteBatchInformation(Integer [] ids);


	// 用户操作---查询资讯全部资讯信息
	public List<Information> queryAllInformationByUser();


	//根据id查询资讯信息
	public Information queryAllInformationsById(Integer id);


	//没有限制查询信息
	public List<Information> queryAllInformationByUserNoLimit();

	//根据标题去查数据库
	public List<Information> queryAllInformationByTitle(String title);


	//根据标题传递去查询数据库(文章详情页)
	public Information selectAllInformationByTitle(String title);


	//根据分页查询数据
	public List<Information> selectAllInformationWithLimit(Integer p,
			Integer pagesize);


	//资讯发布
	public void addInformation(InformationVo informationVo);


	//查询未审核的信息
	public List<Information> queryAllInformationBydisable();


	//查询标题信息
	public List<Information> queryInfoByTitle(String title);


	//查询到相关信息
	public List<Information> queryInfoGettitleAndarea();


	//收藏
	public void updateCollectNum(int id, int collect_num);


	//点赞
	public void updateLikeNum(int id, int like_num);





	


	

	


;
}
