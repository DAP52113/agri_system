package com.sxt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.domain.News;
import com.sxt.sys.mapper.NewsMapper;
import com.sxt.sys.service.NewsService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.NewsVo;


@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsMapper newsMapper;
	
	@Override
	public DataGridView queryAllNews(NewsVo newsVo) {
		// 查询所有公告
		Page<Object> page = PageHelper.startPage(newsVo.getPage(), newsVo.getLimit());// 分页查询设置
		List<News> data =  this.newsMapper.queryAllNews(newsVo); //函数查询
		return new DataGridView(page.getTotal(),data);//调用数据数目
	}

	@Override
	public void addNews(NewsVo newsVo) {
		// 添加公告
		this.newsMapper.insertSelective(newsVo);
	}

	@Override
	public void updateNews(NewsVo newsVo) {
		// 修改公告
		this.newsMapper.updateByPrimaryKeySelective(newsVo);
	}

	@Override
	public void deleteNews(Integer id) {
		// 删除公告
		this.newsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteBatchNews(Integer[] ids) {
		// 批量删除公告
		for(Integer id: ids){
			this.deleteNews(id);
		}
		
	}

	@Override
	public News queryAllNewsById(Integer id) {
		// 根据id查询信息news
		return newsMapper.selectByPrimaryKey(id);
	}

}
