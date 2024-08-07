package com.sxt.sys.domain;

public class Admin {
    private Integer id;

    private String adminname;

    private String adminpsw;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname == null ? null : adminname.trim();
    }

    public String getAdminpsw() {
        return adminpsw;
    }

    public void setAdminpsw(String adminpsw) {
        this.adminpsw = adminpsw == null ? null : adminpsw.trim();
    }
}