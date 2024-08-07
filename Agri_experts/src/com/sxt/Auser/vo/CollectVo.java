package com.sxt.Auser.vo;

import java.util.List;

import com.sxt.Auser.domain.Collect;
import com.sxt.sys.domain.Information;

//收藏实体类拓展类
public class CollectVo  extends Collect{

	//拓展一个一对多的编程接口
	private List<Information> informations;

	public List<Information> getInformations() {
		return informations;
	}

	public void setInformations(List<Information> informations) {
		this.informations = informations;
	}
		

	
}
