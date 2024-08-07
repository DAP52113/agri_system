package com.example.agriculture_expert_app.bean;

import java.io.Serializable;

/*
 * 设置数据库资讯实体类
 * */
public class Infomation implements Serializable{

	    private Integer id;

	    private String eno;

	    private String province;

	    private String city;

	    private String district;

	    private String area;

	    private String content;

	    private Integer disable;

	    private String title;

	    private String wentiphoto;

	    private Integer roleId;

	    private String date;

		private Integer likeNum;

		private Integer collectNum;

		public Integer getLikeNum() {
			return likeNum;
		}

		public void setLikeNum(Integer likeNum) {
			this.likeNum = likeNum;
		}

		public Integer getCollectNum() {
			return collectNum;
		}

		public void setCollectNum(Integer collectNum) {
			this.collectNum = collectNum;
		}

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public String getEno() {
	        return eno;
	    }

	    public void setEno(String eno) {
	        this.eno = eno == null ? null : eno.trim();
	    }

	    public String getProvince() {
	        return province;
	    }

	    public void setProvince(String province) {
	        this.province = province == null ? null : province.trim();
	    }

	    public String getCity() {
	        return city;
	    }

	    public void setCity(String city) {
	        this.city = city == null ? null : city.trim();
	    }

	    public String getDistrict() {
	        return district;
	    }

	    public void setDistrict(String district) {
	        this.district = district == null ? null : district.trim();
	    }

	    public String getArea() {
	        return area;
	    }

	    public void setArea(String area) {
	        this.area = area == null ? null : area.trim();
	    }

	    public String getContent() {
	        return content;
	    }

	    public void setContent(String content) {
	        this.content = content == null ? null : content.trim();
	    }

	    public Integer getDisable() {
	        return disable;
	    }

	    public void setDisable(Integer disable) {
	        this.disable = disable;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title == null ? null : title.trim();
	    }

	    public String getWentiphoto() {
	        return wentiphoto;
	    }

	    public void setWentiphoto(String wentiphoto) {
	        this.wentiphoto = wentiphoto == null ? null : wentiphoto.trim();
	    }

	    public Integer getRoleId() {
	        return roleId;
	    }

	    public void setRoleId(Integer roleId) {
	        this.roleId = roleId;
	    }

	    public String getDate() {
	        return date;
	    }

	    public void setDate(String date) {
	        this.date = date == null ? null : date.trim();
	    }
}
