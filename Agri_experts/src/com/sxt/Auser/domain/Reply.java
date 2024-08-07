package com.sxt.Auser.domain;

import java.util.Date;

public class Reply {
    private Integer rno;

    private String expert;

    private String content;

    private Date date;

    private String replyuser;

    private Integer forconsultid;

    public Integer getRno() {
        return rno;
    }

    public void setRno(Integer rno) {
        this.rno = rno;
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

    public Integer getForconsultid() {
        return forconsultid;
    }

    public void setForconsultid(Integer forconsultid) {
        this.forconsultid = forconsultid;
    }
}