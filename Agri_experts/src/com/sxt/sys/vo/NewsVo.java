package com.sxt.sys.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.sxt.sys.domain.LogLogin;
import com.sxt.sys.domain.News;

public class NewsVo extends News {


	    //分页参数
		private Integer page;
		private Integer limit;
	
		/*
		 * 扩展时间
		 * */
		@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
		private Date startTime;// 时间变化
		@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
		private Date endTime;
		
		
		
		// 接收多个角色id
		private Integer[] ids;

		public Integer[] getIds() {
			return ids;
		}

		public void setIds(Integer[] ids) {
			this.ids = ids;
		}
		public Integer getPage() {
			return page;
		}
		public void setPage(Integer page) {
			this.page = page;
		}
		public Integer getLimit() {
			return limit;
		}
		public void setLimit(Integer limit) {
			this.limit = limit;
		}
	
	
}
