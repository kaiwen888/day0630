package com.kw.mapper;

import com.kw.pojo.DeptBean;
import com.kw.pojo.RoleBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptMapper {

    List<DeptBean> getDeptList();

    List<Integer> RidsByDeptid(Integer deptid);

    List<RoleBean> getRoleList();

    DeptBean getDeptByDeptid(Integer deptid);

    void deleteRoleByDeptid(@Param("deptid") Integer deptid);

    void updateDeptRole(@Param("deptid") Integer deptid, @Param("rid") Integer rid);
}
