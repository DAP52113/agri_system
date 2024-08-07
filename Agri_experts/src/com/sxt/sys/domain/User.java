package com.sxt.sys.domain;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class User implements Serializable{
    private Integer id;

    @Expose
    private String unname;

    private String upsw;

    private String introduce;

    private String uphoto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnname() {
        return unname;
    }

    public void setUnname(String unname) {
        this.unname = unname == null ? null : unname.trim();
    }

    public String getUpsw() {
        return upsw;
    }

    public void setUpsw(String upsw) {
        this.upsw = upsw == null ? null : upsw.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getUphoto() {
        return uphoto;
    }

    public void setUphoto(String uphoto) {
        this.uphoto = uphoto == null ? null : uphoto.trim();
    }
}