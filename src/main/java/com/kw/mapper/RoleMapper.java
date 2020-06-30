package com.kw.mapper;

import com.kw.pojo.PowerBean;
import com.kw.pojo.RoleBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    List<RoleBean> getRoleList();

    List<Integer> getPowerByRole(Integer rid);

    List<PowerBean> getPowerList();

    int deletePowerByRole(Integer rid);

    int savePowerAndRole(@Param("rid") Integer rid,@Param("id") Integer id);
}
