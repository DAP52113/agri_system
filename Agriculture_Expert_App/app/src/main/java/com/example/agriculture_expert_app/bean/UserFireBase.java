package com.example.agriculture_expert_app.bean;

import java.io.Serializable;

//Firebase用户工具类
public class UserFireBase implements Serializable {
    private String name;
    private String role;
    private String iamge;
    private String token;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIamge() {
        return iamge;
    }

    public void setIamge(String iamge) {
        this.iamge = iamge;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
