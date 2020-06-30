package com.kw.service.impl;

import com.kw.mapper.DeptMapper;
import com.kw.pojo.DeptBean;
import com.kw.pojo.RoleBean;
import com.kw.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<DeptBean> getDeptList() {
        List<DeptBean> beanList = deptMapper.getDeptList();
        return beanList;
    }

    @Override
    public List<Integer> RidsByDeptid(Integer deptid) {
        List<Integer> list = deptMapper.RidsByDeptid(deptid);
        return list;
    }

    @Override
    public List<RoleBean> getRoleList() {
        List<RoleBean> beanList = deptMapper.getRoleList();
        return beanList;
    }

    @Override
    public DeptBean getDeptByDeptid(Integer deptid) {
        DeptBean bean = deptMapper.getDeptByDeptid(deptid);
        return bean;
    }

    @Override
    public void updateDeptRole(Integer deptid, Integer[] rids) {
        if (deptid != null){
            deptMapper.deleteRoleByDeptid(deptid);
            if (rids != null && rids.length>=1){
                for (Integer rid:rids){
                    System.out.println(rid);
                    deptMapper.updateDeptRole(deptid,rid);
                }
            }
        }
    }
}
