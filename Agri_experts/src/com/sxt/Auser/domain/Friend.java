package com.sxt.Auser.domain;

public class Friend {
    private Integer friendid;

    private Integer userid;

    private Integer frienduserid;

    private String friendauthorized;

    public Integer getFriendid() {
        return friendid;
    }

    public void setFriendid(Integer friendid) {
        this.friendid = friendid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getFrienduserid() {
        return frienduserid;
    }

    public void setFrienduserid(Integer frienduserid) {
        this.frienduserid = frienduserid;
    }

    public String getFriendauthorized() {
        return friendauthorized;
    }

    public void setFriendauthorized(String friendauthorized) {
        this.friendauthorized = friendauthorized == null ? null : friendauthorized.trim();
    }
}