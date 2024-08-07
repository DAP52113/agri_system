package com.sxt.Auser.domain;

import java.util.Date;

public class Diagnosereply {
    private Integer rid;

    private String expert;

    private String content;

    private Date date;

    private String replyuser;

    private Integer fordiagnoseid;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getExpert() {
        return expert;
    }

    public void setExpert(String expert) {
        this.expert = expert == null ? null : expert.trim();
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

    public String getReplyuser() {
        return replyuser;
    }

    public void setReplyuser(String replyuser) {
        this.replyuser = replyuser == null ? null : replyuser.trim();
    }

    public Integer getFordiagnoseid() {
        return fordiagnoseid;
    }

    public void setFordiagnoseid(Integer fordiagnoseid) {
        this.fordiagnoseid = fordiagnoseid;
    }
}