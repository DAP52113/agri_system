package com.sxt.Auser.domain;

public class Userchat {
    private Integer userid;

    private String username;

    private String userphoto;

    private String userintroduce;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserphoto() {
        return userphoto;
    }

    public void setUserphoto(String userphoto) {
        this.userphoto = userphoto == null ? null : userphoto.trim();
    }

    public String getUserintroduce() {
        return userintroduce;
    }

    public void setUserintroduce(String userintroduce) {
        this.userintroduce = userintroduce == null ? null : userintroduce.trim();
    }
}