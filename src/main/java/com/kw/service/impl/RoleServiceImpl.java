package com.kw.service.impl;

import com.kw.mapper.RoleMapper;
import com.kw.pojo.PowerBean;
import com.kw.pojo.RoleBean;
import com.kw.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleBean> getRoleList() {
        List<RoleBean> beanList = roleMapper.getRoleList();
        return beanList;
    }

    @Override
    public List<PowerBean> getPowerList(Integer rid) {
        List<Integer> list = roleMapper.getPowerByRole(rid);
        List<PowerBean> powerList = roleMapper.getPowerList();
        if (list!=null && list.size()>=1){
            if (powerList!=null && powerList.size()>=1){
                for (Integer ids : list){
                    for (PowerBean powerBean : powerList){
                        if (ids.equals(powerBean.getId())){
                            powerBean.setChecked(true);
                            break;
                        }
                    }
                }
            }
        }
        return powerList;
    }

    @Override
    public int savePowerAndRole(Integer rid, String ids) {

        roleMapper.deletePowerByRole(rid);

        if (ids!=null && ids.length() >=1){
            String[] idss = ids.split(",");
            for (String pid:idss) {
                int id = Integer.parseInt(pid);
                roleMapper.savePowerAndRole(rid,id);

            }
        }
        return 0;
    }
}
