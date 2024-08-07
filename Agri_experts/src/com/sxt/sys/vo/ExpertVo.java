package com.sxt.sys.vo;

import com.sxt.sys.domain.Expert;

public class ExpertVo extends Expert {
	//分页参数
	private Integer page;
	private Integer limit;
	private String code;
	// 接收多个角色id
	private Integer[] eids;
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer[] getEids() {
		return eids;
	}

	public void setEids(Integer[] eids) {
		this.eids = eids;
	}


	
	
	
}
