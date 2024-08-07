package com.sxt.sys.service;

import com.sxt.sys.domain.News;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.NewsVo;


/*
 * 系统公告接口
 * */
public interface NewsService {

	/**
	 * 查询所有公告
	 * @param 
	 * @return
	 */
	public DataGridView queryAllNews(NewsVo newsVo);
	/**
	 * 添加公告
	 * @param 
	 */
	public void addNews(NewsVo newsVo);

	/**
	 * 修改公告
	 * @param 
	 */
	public void updateNews(NewsVo newsVo);
	/**
	 * 根据id删除公告
	 * @param 
	 */
	public void deleteNews(Integer id);
	/**
	 * 批量删除公告
	 * @param 
	 */
	public void deleteBatchNews(Integer [] ids);
	public News queryAllNewsById(Integer id);
}
