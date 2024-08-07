package com.sxt.Auser.domain;

import java.util.Date;



public class Message {
    private Integer id;

    private String send;

    private String receive;

    private String content;

    
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send == null ? null : send.trim();
    }

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive == null ? null : receive.trim();
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
}