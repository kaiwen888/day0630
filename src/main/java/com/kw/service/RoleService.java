package com.kw.service;

import com.kw.pojo.PowerBean;
import com.kw.pojo.RoleBean;

import java.util.List;

public interface RoleService {

    List<RoleBean> getRoleList();

    List<PowerBean> getPowerList(Integer rid);

    int savePowerAndRole(Integer rid,String ids);
}
