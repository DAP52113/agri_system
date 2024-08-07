package com.sxt.Auser.domain;

//用户收藏实体类
public class Collect {
    private Integer collectid;

    private Integer informationid;

    private String unname;

    public Integer getCollectid() {
        return collectid;
    }

    public void setCollectid(Integer collectid) {
        this.collectid = collectid;
    }

    public Integer getInformationid() {
        return informationid;
    }

    public void setInformationid(Integer informationid) {
        this.informationid = informationid;
    }

    public String getUnname() {
        return unname;
    }

    public void setUnname(String unname) {
        this.unname = unname == null ? null : unname.trim();
    }
}