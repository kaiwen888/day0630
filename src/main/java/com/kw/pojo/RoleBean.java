package com.kw.pojo;

public class RoleBean {
    private Integer rid;
    private String rname;

    public DeptBean getDeptBean() {
        return deptBean;
    }

    private DeptBean deptBean = new DeptBean();

    @Override
    public String toString() {
        return "RoleBean{" +
                "rid=" + rid +
                ", rname='" + rname + '\'' +
                ", deptBean=" + deptBean +
                '}';
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public Integer getRid() {
        return rid;
    }

    public String getRname() {
        return rname;
    }
}
