package com.sxt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.domain.Information;
import com.sxt.sys.mapper.InformationMapper;
import com.sxt.sys.service.InformationService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.InformationVo;


@Service
public class InformationServiceImpl implements InformationService {

	@Autowired
	private InformationMapper informationMapper;
	
	@Override
	public DataGridView queryAllInformation(InformationVo informationVo) {
		// 查询所有的信息内容
		Page<Object> page = PageHelper.startPage(informationVo.getPage(), informationVo.getLimit());// 分页查询设置
		List<Information> data =  this.informationMapper.queryAllInformations(informationVo); //函数查询
		return new DataGridView(page.getTotal(),data);//调用数据数目
	}

	@Override
	public void updateInformation(InformationVo informationVo) {
		// 修改信息
		this.informationMapper.updateByPrimaryKeySelective(informationVo);

	}

	@Override
	public void deleteInformation(Integer id) {
		// 删除信息

		this.informationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteBatchInformation(Integer[] ids) {
		// 批量删除信息
		for(Integer id: ids){
			this.deleteInformation(id);
		}

	}

	//用户角色模块--查询资讯信息
	@Override
	public List<Information> queryAllInformationByUser() {
		
		return this.informationMapper.queryAllInformationsByUser();
	}

	@Override
	public Information queryAllInformationsById(Integer id) {
	
		return this.informationMapper.queryAllInformationsById(id);
	}

	@Override
	public List<Information> queryAllInformationByUserNoLimit() {
		// TODO Auto-generated method stub
		return this.informationMapper.queryAllInformationByUserNolimit();
	}

	@Override
	public List<Information> queryAllInformationByTitle(String title) {
		// TODO Auto-generated method stub
		return this.informationMapper.queryAllInformationByTitle(title);
	}

	@Override
	public Information selectAllInformationByTitle(String title) {
		// 标题查询数据库
		return this.informationMapper.selectAllInformationByTitle(title);
	}

	@Override
	public List<Information> selectAllInformationWithLimit(Integer p,
			Integer pagesize) {
		// 分页查询
		Integer start = (p-1)*pagesize;
		System.out.println("start"+start);
		return this.informationMapper.selectAllinformationWithLimit(start,pagesize);
	}

	@Override
	public void addInformation(InformationVo informationVo) {
		// 资讯发布
		this.informationMapper.insertSelective(informationVo);
	}

	@Override
	public List<Information> queryAllInformationBydisable() {
		// 查询未审核信息
		return this.informationMapper.queryAllInformationByDisable();
	}

	@Override
	public List<Information> queryInfoByTitle(String title) {
		// TODO Auto-generated method stub
		return this.informationMapper.queryInfoByTitle(title);
	}

	@Override
	public List<Information> queryInfoGettitleAndarea() {
		// TODO Auto-generated method stub
		return this.informationMapper.queryInfoGettitleAndarea();
	}

	@Override
	public void updateCollectNum(int id, int collect_num) {
		// TODO Auto-generated method stub
		this.informationMapper.updateCollectNum(id,collect_num);
	}

	@Override
	public void updateLikeNum(int id, int like_num) {
		// TODO Auto-generated method stub
		this.informationMapper.updateLikeNum(id,like_num);
	}













}
