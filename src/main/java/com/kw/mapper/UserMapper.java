package com.kw.mapper;

import com.kw.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserMapper {

    UserBean findAll(Integer id);

    UserBean getUserList(Integer id);

    List<UserBean> findUser();

    List<UserBean> findPage(HashMap<String, Object> map);

    List<Integer> getPowerByRole(Integer rid);

    List<PowerBean> getPowerList();

   PowerBean getPower(Integer id);

    List<DeptBean> getDeptList();

    List<RoleBean> getRoleList(Integer deptid);

    UserBean findUserById(Integer id);

    int update(@Param("deptid") Integer deptid,@Param("rid") Integer rid ,@Param("id") Integer id);

    int delete(Integer id);

    int addUser(UserBean userBean);

    int updateUser(UserBean userBean);

    UserBean getLogin(UserBean userBean);


    RsBean getRsSmoke(String cardno);

    RsBean getRsWine(String cardno);

    int saveSmoke(SmokeBean smokeBean);

    int saveWine(WineBean wineBean);

    /**
     * service里面的4个方法，调用了mapper中的16个方法，要是没有事务，将会怎么样？
     * 和企业代码的难度差不多
     *
     */
    /**
     * mapper接口中的方法不能重载
     */
    void insertPorcess(ProcessBean pb);

    GradeBean getGradeByGid(@Param("gid") Integer gid);

    void insertProcessPmx(PmxBean pmxBean);

    List<QjVo> getStuQjListBySid(@Param("sid") Integer sid);

    Integer getUserIdByPid(@Param("pid") Integer pid);

    Integer getUserIdByPidMaxShunxu(@Param("pid") Integer pid);

    Integer getUserIdByPidNopass(@Param("pid") Integer pid);

    QjVo getUnameAndRnameById(@Param("id") Integer id);

    List<Integer> getPidsByUserid(@Param("userid") Integer userid);

    QjVo getProcessById(@Param("id") Integer id);

    QjVo getStuInfoBySid(@Param("id") Integer id);

    void updateProcessStatus(@Param("pid") Integer pid,@Param("shstatus") Integer shstatus);

    void updatePmxStatus(@Param("pid") Integer pid, @Param("userid") Integer userid,@Param("shstatus")Integer shstatus);

    Integer getMaxPshunxu(@Param("pid") Integer pid);

    Integer getQjMxInfo(@Param("pid") Integer pid, @Param("userid") Integer userid);

    void updatePmxShunxu(@Param("pid") Integer pid, @Param("pshunxu") Integer pshunxu);
}
