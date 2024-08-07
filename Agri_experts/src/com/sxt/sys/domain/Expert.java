package com.sxt.sys.domain;

public class Expert {
    private Integer eid;

    private String eno;

    private String ename;

    private String epsw;

    private String esex;

    private String eprof;

    private String province;

    private String city;

    private String district;

    private String area;

    private String photo;

    private String roleId;

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getEno() {
        return eno;
    }

    public void setEno(String eno) {
        this.eno = eno == null ? null : eno.trim();
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    public String getEpsw() {
        return epsw;
    }

    public void setEpsw(String epsw) {
        this.epsw = epsw == null ? null : epsw.trim();
    }

    public String getEsex() {
        return esex;
    }

    public void setEsex(String esex) {
        this.esex = esex == null ? null : esex.trim();
    }

    public String getEprof() {
        return eprof;
    }

    public void setEprof(String eprof) {
        this.eprof = eprof == null ? null : eprof.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }
}