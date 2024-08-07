package com.sxt.Auser.domain;

import java.util.Date;

public class Words {
    private Integer id;

    private String username;

    private String content;

    private Date date;

    private String foruser;

    private String forarticleid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getForuser() {
        return foruser;
    }

    public void setForuser(String foruser) {
        this.foruser = foruser == null ? null : foruser.trim();
    }

    public String getForarticleid() {
        return forarticleid;
    }

    public void setForarticleid(String forarticleid) {
        this.forarticleid = forarticleid == null ? null : forarticleid.trim();
    }
}