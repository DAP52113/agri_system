package com.sxt.Auser.domain;

public class Like {
    private Integer likeid;

    private Integer informationid;

    private String unname;

    public Integer getLikeid() {
        return likeid;
    }

    public void setLikeid(Integer likeid) {
        this.likeid = likeid;
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