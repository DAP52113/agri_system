package com.sxt.Auser.domain;

public class Messagechat {
    private Integer messageid;

    private Integer messagesender;

    private Integer messagereciver;

    private String messagedate;

    private String messageinfo;

    private String messagetype;

    private String messageread;

    public Integer getMessageid() {
        return messageid;
    }

    public void setMessageid(Integer messageid) {
        this.messageid = messageid;
    }

    public Integer getMessagesender() {
        return messagesender;
    }

    public void setMessagesender(Integer messagesender) {
        this.messagesender = messagesender;
    }
   
    

    public Integer getMessagereciver() {
        return messagereciver;
    }


	public void setMessagereciver(Integer messagereciver) {
        this.messagereciver = messagereciver;
    }

    public String getMessagedate() {
        return messagedate;
    }

    public void setMessagedate(String messagedate) {
        this.messagedate = messagedate == null ? null : messagedate.trim();
    }

    public String getMessageinfo() {
        return messageinfo;
    }

    public void setMessageinfo(String messageinfo) {
        this.messageinfo = messageinfo == null ? null : messageinfo.trim();
    }

    public String getMessagetype() {
        return messagetype;
    }

    public void setMessagetype(String messagetype) {
        this.messagetype = messagetype == null ? null : messagetype.trim();
    }

    public String getMessageread() {
        return messageread;
    }

    public void setMessageread(String messageread) {
        this.messageread = messageread == null ? null : messageread.trim();
    }
}