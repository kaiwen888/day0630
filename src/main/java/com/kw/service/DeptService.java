package com.kw.service;

import com.kw.pojo.DeptBean;
import com.kw.pojo.RoleBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptService {

    List<DeptBean> getDeptList();

    List<Integer> RidsByDeptid(Integer deptid);

    List<RoleBean> getRoleList();

    DeptBean getDeptByDeptid(Integer deptid);

    void updateDeptRole(@Param("deptid") Integer deptid,Integer[] rids);
}
