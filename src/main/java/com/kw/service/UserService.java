package com.kw.service;

import com.kw.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

public interface UserService {

    List<UserBean> findPage(@RequestParam(defaultValue = "1") String pageNum, @RequestParam(defaultValue = "5") String pageSize,UserBean userBean);

    UserBean findAll(Integer id);

    List<UserBean> findUser();

    UserBean getUserList(Integer id);

    List<PowerBean> getPowerList();

    List<PowerBean> getPower(Integer id);

    UserBean findUserById(Integer id);

    List<DeptBean> getDeptList();

    List<RoleBean> getRoleList(Integer deptid);

    int update(@Param("deptid") Integer deptid, @Param("rid") Integer rid , @Param("id") Integer id);

    int delete(Integer id);

    int addUser(UserBean userBean);

    int updateUser(UserBean userBean);

    UserBean getLogin(UserBean userBean);

    QueryVo jieXiStr1(String str1);

    String jieXiStr2(String str2);

    String getInfo(QueryVo vo);

    String saveData(QueryVo queryVo, String str2);

    void saveStuQj(Integer id, Double qjtime, Date stime, Date etime, String qjcause);

    void saveStuQj2(ProcessBean pb);

    List<QjVo> getStuQjListBySid(Integer sid);

    List<QjVo> getQjshListByUserid(Integer id);

    void saveWdsh(Integer pid, Integer shstatus,Integer userid);
}
