package com.kw.pojo;

import java.util.Date;

public class UserBean {

    private Integer id;
    private String username;
    private String password;
    private Integer gid;

    private Integer age;
    private Date birthday;

    private String sbirthday;
    private String ebirthday;
    private Integer totalPage;
    private Integer currentPage;

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setSbirthday(String sbirthday) {
        this.sbirthday = sbirthday;
    }

    public void setEbirthday(String ebirthday) {
        this.ebirthday = ebirthday;
    }

    public String getSbirthday() {
        return sbirthday;
    }

    public String getEbirthday() {
        return ebirthday;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public Date getBirthday() {
        return birthday;
    }

    private Integer deptid;
    private Integer rid;

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getDeptid() {
        return deptid;
    }

    public Integer getRid() {
        return rid;
    }

    private DeptBean deptBean;

    public RoleBean getRoleBean() {
        return roleBean;
    }

    private RoleBean roleBean;

    public DeptBean getDeptBean() {
        return deptBean;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gid=" + gid +
                ", age=" + age +
                ", birthday=" + birthday +
                ", sbirthday='" + sbirthday + '\'' +
                ", ebirthday='" + ebirthday + '\'' +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                ", deptid=" + deptid +
                ", rid=" + rid +
                ", deptBean=" + deptBean +
                ", roleBean=" + roleBean +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
