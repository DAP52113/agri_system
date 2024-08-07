package com.example.agriculture_expert_app.bean;

import java.io.Serializable;
import java.util.Date;

//专家回复信息实体类，实现序列化
public class Reply implements Serializable {
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
        this.expert = expert;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        this.replyuser = replyuser;
    }

    public Integer getForconsultid() {
        return forconsultid;
    }

    public void setForconsultid(Integer forconsultid) {
        this.forconsultid = forconsultid;
    }
}
