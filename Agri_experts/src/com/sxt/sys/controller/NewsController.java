package com.sxt.sys.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;









import com.sxt.sys.domain.Admin;
import com.sxt.sys.domain.News;
import com.sxt.sys.service.NewsService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;
import com.sxt.sys.utils.WebUtils;
import com.sxt.sys.vo.NewsVo;


/*
 * 系统公告管理控制器
 * */
@RestController
@RequestMapping("news")
public class NewsController {

	@Autowired
	private NewsService newsService;
	
	@RequestMapping("loadAllNews")
	public DataGridView loadAllNews(NewsVo newsVo){
	
		//查询所有系统公告并加载
		return this.newsService.queryAllNews(newsVo);
	}
	/*
	 * 添加系统公告
	 * */
	@RequestMapping("addNews")
	public ResultObj addNews(NewsVo newsVo){
		try {
			newsVo.setCreatetime(new Date());// 设置添加时间
			Admin admin = (Admin) WebUtils.getHttpSession().getAttribute("admin");
			newsVo.setOpername(admin.getAdminname()); //设置公告添加人员
			this.newsService.addNews(newsVo);
			return ResultObj.ADD_SUCCESS;// 添加成功
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;// 添加失败
		}
	}
	
	//修改公告文件
	@RequestMapping("updateNews")
	public ResultObj updateNews(NewsVo newsVo){
		try {
			this.newsService.updateNews(newsVo);
			return ResultObj.UPDATE_SUCCESS; // 修改成功
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR; // 修改失败
		}
	}
	
	
	//删除系统公告文件
	@RequestMapping("deleteNews")
	public ResultObj deleteNews(NewsVo newsVo){
		try {
			this.newsService.deleteNews(newsVo.getId());
			return ResultObj.DELETE_SUCCESS; //删除公告文件成功
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;// 删除失败(JSON格式)
		}
		
	}
	
	//批量删除系统公告文件
	@RequestMapping("deleteBatchNews")
	public ResultObj deleteBatchNews(NewsVo newsVo){
		try {
			this.newsService.deleteBatchNews(newsVo.getIds());
			return ResultObj.DELETE_SUCCESS; //删除成功
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR; // 删除失败(JSON格式)
		}
	}
	
	
	// 实现工作台系统公告显示：通过loadNewsById请求进行后端查询
	@RequestMapping("loadNewsById")
	public News loadNewsById(Integer id){
		return this.newsService.queryAllNewsById(id);// 根据id进行查询
	}
	
	
	
	
}
