package com.kw.service.impl;

import com.kw.mapper.UserMapper;
import com.kw.pojo.*;
import com.kw.service.UserService;
import com.kw.utils.JieXiXml;
import com.kw.utils.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserBean> findPage(@RequestParam(defaultValue = "1") String pageNum, @RequestParam(defaultValue = "5") String pageSize,UserBean userBean) {
        List<UserBean> list = userMapper.findUser();
        Page page = new Page(pageNum,list.size(),pageSize);

        String sql = "";

        if (userBean.getSbirthday()!=null && userBean.getSbirthday()!="" && userBean.getEbirthday()!=null && userBean.getEbirthday()!=""){
            sql = "SELECT u.*, d.dname,r.rname FROM t_user u LEFT JOIN t_dept d ON u.deptid = d.deptid LEFT JOIN t_role r ON u.rid = r.rid WHERE u.birthday >= '"+userBean.getSbirthday()+"' AND u.birthday <= '"+userBean.getEbirthday()+"' and u.username LIKE '%"+userBean.getUsername()+"%' LIMIT "+page.getStartRecord()+","+page.getPageSize()+"";
        }else if (userBean.getUsername()!=null) {
               sql = "SELECT u.*, d.dname,r.rname FROM t_user u LEFT JOIN t_dept d ON u.deptid = d.deptid LEFT JOIN t_role r ON u.rid = r.rid WHERE u.username LIKE '%"+userBean.getUsername()+"%' LIMIT "+page.getStartRecord()+","+page.getPageSize()+"";
        }else {
             sql = "SELECT u.*, d.dname,r.rname FROM t_user u LEFT JOIN t_dept d ON u.deptid = d.deptid LEFT JOIN t_role r ON u.rid = r.rid LIMIT "+page.getStartRecord()+","+page.getPageSize()+"";

        }
        HashMap<String,Object> map = new HashMap<>();
        map.put("sql",sql);
        List<UserBean> plist = userMapper.findPage(map);
        return plist;
    }

    @Override
    public UserBean findAll(Integer id) {
        UserBean list = userMapper.findAll(id);
        return list;
    }

    @Override
    public UserBean getUserList(Integer id) {
        UserBean userList = userMapper.getUserList(id);
        return userList;
    }

    @Override
    public List<UserBean> findUser() {
        List<UserBean> list = userMapper.findUser();
        return list;
    }

    @Override
    public List<PowerBean> getPower(Integer rid) {

        List<PowerBean> list = new ArrayList<PowerBean>();
        List<Integer> integerList = userMapper.getPowerByRole(rid);
        for (Integer id:integerList) {
            PowerBean power = userMapper.getPower(id);
            list.add(power);
        }
        return list;
    }

    @Override
    public List<PowerBean> getPowerList() {
        List<PowerBean> list = userMapper.getPowerList();
        return list;
    }

    @Override
    public UserBean findUserById(Integer id) {
        UserBean userBean = userMapper.findUserById(id);
        return userBean;
    }

    @Override
    public List<DeptBean> getDeptList() {
        List<DeptBean> beanList = userMapper.getDeptList();
        return beanList;
    }

    @Override
    public List<RoleBean> getRoleList(Integer deptid) {
        List<RoleBean> beanList = userMapper.getRoleList(deptid);
        return beanList;
    }

    @Override
    public int update(@Param("deptid") Integer deptid, @Param("rid") Integer rid , @Param("id") Integer id) {
        int update = userMapper.update(deptid, rid, id);
        return update;
    }

    @Override
    public int delete(Integer id) {
        int delete = userMapper.delete(id);
        return delete;
    }

    @Override
    public int addUser(UserBean userBean) {
        int i = userMapper.addUser(userBean);
        return i;
    }

    @Override
    public int updateUser(UserBean userBean) {
        int i = userMapper.updateUser(userBean);
        return i;
    }

    @Override
    public UserBean getLogin(UserBean userBean) {

        UserBean login = userMapper.getLogin(userBean);
        return login;
    }

    @Override
    public QueryVo jieXiStr1(String str1) {
        return JieXiXml.jieXiStr1(str1);
    }

    @Override
    public String jieXiStr2(String str2) {
        return JieXiXml.jieXiStr2(str2);
    }

    @Override
    public String getInfo(QueryVo vo) {
        RsBean rs=null;
        if("01".equals(vo.getCode())){
            /**
             * 要是code是01去查询香烟，到香烟的数据库里面去查询
             */
            rs = userMapper.getRsSmoke(vo.getCardno());

        }else if("02".equals(vo.getCode())){
            /**
             * 要是code是02去查询酒水，到酒水的数据库里面去查询
             */
            rs = userMapper.getRsWine(vo.getCardno());
        }

        String str = JieXiXml.pinRsStr(vo.getCode(),rs);
        if(str!=null){
            return str;
        }
        return null;
    }

    @Override
    public String saveData(QueryVo queryVo, String str2) {
        if ("01".equals(queryVo.getCode())){
            SmokeBean smokeBean = JieXiXml.jieXiStr2Smoke(str2);
            if (smokeBean==null){
                return "<MEG><CODE>0</CODE><CONTENT>参数2解析失败</CONTENT></MEG>";
            }else {
                try {
                    userMapper.saveSmoke(smokeBean);

                    return "<MEG><CODE>1</CODE><CONTENT>保存成功了</CONTENT></MEG>";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "<MEG><CODE>0</CODE><CONTENT>数据保存失败</CONTENT></MEG>";
                }
            }
        }else if (queryVo.getCode().equals("02")){
            WineBean wineBean = JieXiXml.jieXiStr2Wine(str2);
            if (wineBean==null){
                return "<MEG><CODE>0</CODE><CONTENT>参数2解析失败</CONTENT></MEG>";
            }else {
                try {
                    userMapper.saveWine(wineBean);
                    return "<MEG><CODE>1</CODE><CONTENT>保存成功了</CONTENT></MEG>";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "<MEG><CODE>0</CODE><CONTENT>数据保存失败</CONTENT></MEG>";
                }
            }
        }
        return null;
    }

    @Override
    public void saveStuQj(Integer id, Double qjtime, Date stime, Date etime, String qjcause) {
        /**
         * 时间的判断 ，可以使用 两个日期进行做差，当然也可以使用  qjtime（需要在页面固定单位）
         * qjtime：固定他的但是是天
         */
        /**
         * 在进入请假流程之前，需要查询出学生的班级和班级的讲师以及辅导员，也可以把主任和院长查出来
         */


        /**
         *判断学生请假的长度，只是用来判断审核流程的站点的，学生请假进库和这个时间没有关系不管请多长时间，都需要进库
         */

        /**
         * 1、先把请假流程保存进去再说,需要把流程的id拿回来，因为我们保存流程明细的时候，需要流程的id。
         * 保存返回的id，只有是在实体的时候返回过，直接用方法返回，可以吗？可以试一下
         * 没有能把插入的id作为返回值返回回来的办法，那么就把数控封装成实体类
         */

        ProcessBean pb = new ProcessBean();
        pb.setSid(id);
        pb.setQjcause(qjcause);
        pb.setEtime(etime);
        pb.setStime(stime);
        pb.setQjtime(qjtime);
        /**
         * 流程状态，0表示 正在审核中，1表示审核通过，2表示审核未通过
         * 这个是整合流程的状态，整合请假流程现在的状态，供请假者来查询
         * 在明细表中的，记录的是每一个审批的状态
         */
        pb.setQjstatus(0);
        userMapper.insertPorcess(pb);
        System.out.println("pb========="+pb.getId());

        /**
         * 流程保存成功，接下来，根据时间保存流程明细，需要根据学生的sid去把学生班级信息查询出来，因为我们要查询学生班级的讲师和辅导员id
         * 查询到学生班级情况后，就有了讲师id和辅导员id，这样就能生成明细了，主任id和院长id这个是固定的，可以在班级里面加入，
         * 我们没有更大的单位了，还有教研室，还有学院（主任和id是固定的）
         */
        /**
         * 流程明细全部进去了，谁先来处理。都是0,但是因为讲师先审批，所以新增的时候，其他的都是0，讲师是1，表示正在审核，2表示已审核
         * 要是讲师或者辅导员，审核过了，不管是审核未通过还是审核通过，只要操作了，就把顺序码改成2，表示这个流程已经操作完了
         * 每个人登录上来，我们只需要查看id是自己的，然后顺序码是1的，那些流程明细，表示该我审核了。2表示我已经处理过了。0的表示还没有到我这里来。就OK啦
         */

        if(qjtime<=1){
            /**
             * 小于等于1的全部在这里了，给明细里面爆出一个讲师和辅导员的明细就OK啦
             */

        }else if(qjtime<=3){
            /**
             * 在这个里面没有必要在写  >1 && <=3，给明细表中，主任加一个明细
             */
        }else{
            /**
             * 给院长一个明细
             */
        }
    }

    @Override
    public void saveStuQj2(ProcessBean pb) {

        pb.setQjstatus(0);
        userMapper.insertPorcess(pb);
        /**
         * 把假条保存好了，把保存后假条流程id来回来了
         * 接下来要保存流程明细（这个假条都需要谁来审核）
         * 需要给流程明细里面插入数据了，首先需要查询出这个学生的班级信息，
         * 最起码需要查询出讲师和班主任
         */
        UserBean ub = userMapper.findUserById(pb.getSid());
        if(ub!=null&&ub.getGid()!=null){
            /**
             * 需要去查询班级
             */
            GradeBean gb = userMapper.getGradeByGid(ub.getGid());
            /**
             * 班级里面就有讲师和辅导员了，
             * 要是这个班级没有讲师或者辅导员，这个学生的假条怎么提交，直接提给主任的，主任需要把讲师和班主任的都审核了
             * 钉钉就是这样子的，要是没有讲师的都是主任审核，然后辅导员审核，再到主任，因为1911这个班级
             */
            /**
             * 万一你的流程里面找不到下一个审核者，你们当时怎么处理的，直接递交给下一个审核者的领导。钉钉就是干的
             */
            PmxBean pmxBean  = new PmxBean();
            /**
             * 流程id
             */
            pmxBean.setPid(pb.getId());
            pmxBean.setStatus(0);

            /**
             * 现在这个写法就是小于1天，讲师和辅导员审核，大于1天，小于3天，主任审核，大于直接院长审核
             *
             * 需要的大于1天的先讲师和辅导员审核
             *
             */

            /**
             * 小于等于1的全部在这里了，给明细里面爆出一个讲师和辅导员的明细就OK啦
             */
            /**
             * 没有辅导员和讲师的情况，不判断了，想判断自己再次判断
             */
            /**
             * 第一个肯定要给讲师-辅导员-主任-院长
             * 流程明细里面出现两个状态码，
             * status这个表示流程处理的意见状态：0表示 正在审核中，1表示审核通过，2表示审核未通过
             * pstatus:这个是流程处理的状态码： 0没有没到我这里，  1表示该我处理了，2表示我已经处理过了
             * 需要判断流程有没有结束：需要判断的状态：处理顺序   1234
             */

            /**
             *讲师
             */


            /**
             * status是是意见状态，审核的时候，才会出现
             * 设置的是  psatus
             */
            pmxBean.setPstatus(1);
            /**
             * 这个是处理的顺序，讲师1
             */
            pmxBean.setPshunxu(1);

            /**
             * 处理人的id
             */
            pmxBean.setUserid(gb.getTid());

            /**
             * 保存讲师的处理流程明细
             */
            userMapper.insertProcessPmx(pmxBean);


            /**
             * 处理辅导员的业务流程,这时候不该处理
             */


            pmxBean.setPshunxu(2);
            pmxBean.setPstatus(0);
            pmxBean.setUserid(gb.getFid());

            userMapper.insertProcessPmx(pmxBean);


            if(pb.getQjtime() >1){
                /**
                 * 在这个里面没有必要在写  >1 && <=3，给明细表中，主任加一个明细
                 */
                pmxBean.setPshunxu(3);
                /**
                 * 目前我们给班级没有设置主任和院长，所以这两个写死，可以在班级里面增加主任和院长
                 */
                pmxBean.setUserid(1);
                userMapper.insertProcessPmx(pmxBean);
            }

            if(pb.getQjtime()>3){
                /**
                 * 给院长一个明细
                 */
                pmxBean.setPshunxu(4);
                /**
                 * 目前我们给班级没有设置主任和院长，所以这两个写死，可以在班级里面增加主任和院长
                 */
                pmxBean.setUserid(2);
                userMapper.insertProcessPmx(pmxBean);
            }
        }
    }

    @Override
    public List<QjVo> getStuQjListBySid(Integer sid) {
        List<QjVo> list = userMapper.getStuQjListBySid(sid);
        /**
         * 循环里面进行判断开始判断
         */
        for (QjVo vo : list) {
            Integer qjstatus = vo.getQjstatus();
            Integer userid=0;
            if(qjstatus==0){
                /**
                 * 正在审核中
                 */
                vo.setStatusStr("正在审核中");
                /**
                 * 需要查询目前谁正在审核，使用流程id去查询，去明细表中查询pstatus等于1的，就是正在审核
                 * 能查出来，userid
                 */
                userid = userMapper.getUserIdByPid(vo.getId());
                /**
                 * 三种状态中都要用户id查出来，所以用户信息我们最后统一查询一下，一个状态下，就一个用户
                 */


            }else if(qjstatus==1){
                /**
                 * 表示审核通过
                 */
                vo.setStatusStr("审核通过");
                /**
                 * 审核通过了，肯定是最后一个人审核的，也就是审核吗最大的那个人，
                 * select userid from t_pmx where pshunxu = (select max(pshunxu) from t_pmx where pid = 11) and pid=11
                 */
                userid = userMapper.getUserIdByPidMaxShunxu(vo.getId());
            }else{
                /**
                 * 审核没通过
                 */
                vo.setStatusStr("审核未通过");
                /**
                 * 审核未通过，去好谁审核没有通过，找审核吗是2的
                 * 审核未通过
                 */
                userid = userMapper.getUserIdByPidNopass(vo.getId());
            }
            QjVo vvo = userMapper.getUnameAndRnameById(userid);
            vo.setUname(vvo.getUname());
            vo.setRname(vvo.getRname());
        }
        return list;
    }

    @Override
    public List<QjVo> getQjshListByUserid(Integer id) {
        /**
         * 去查询有没有需要我审核的流程id的集合
         */
        List<Integer> pids = userMapper.getPidsByUserid(id);
        List<QjVo> list = null;
        if(pids!=null&&pids.size()>=1){
            list = new ArrayList<QjVo>();
            for (Integer pid : pids) {
                /**
                 * 先按照流程id去查询流程表里面有的信息
                 */
                QjVo vo = userMapper.getProcessById(pid);
                /**
                 * 查询出来的vo中，只有学生的id，没有学生名字和班级的名字
                 * 又要去查询这个学生的另外两个字段
                 */
                QjVo voo = userMapper.getStuInfoBySid(vo.getSid());
                vo.setUname(voo.getUname());
                vo.setGname(voo.getGname());
                list.add(vo);
            }
        }
        return list;
    }

    @Override
    public void saveWdsh(Integer pid, Integer shstatus, Integer userid) {
        /**
         * 保存我的审核
         */
        if (shstatus==1){
            /**
             * 审核通过了，
             *  需要判断我的审核是不是最后一个，要是最后一个，就要把流程表中的状态改成  改成 审核成功，然后把流程明细里面状态改掉
             *  要是不是最后一个，把自己的流程明细里面的状态改掉，改成已处理，然后把自己审核的状态该进去，然后把流程交给下一个人
             *  怎么交给，把pstatus改成1，怎么样判断下一个人   pshunxu +1,
             *  怎么样判断自己是不是最后流程审核的最后一人，把流程审核最后一人的pshun  max（pshunxu），查出来，和自己pshunxu比较
             */

            /**
             * 接下来，我们需要使用 流程id和userid去明细表中查询，   查询目前我审核的这个流程的详细信息
             * 在查询，目前审核的这个这个整合流程的最大  顺序，是不是最后一个
             * vo里面没有顺序，那就不要用vo了，字节用一个数字表示方便
             */

            Integer pshunxu = userMapper.getQjMxInfo(pid,userid);
            Integer maxpshunxu = userMapper.getMaxPshunxu(pid);
            /**
             * 他俩要是相等，就是最后一步了，要是不相等，表示不是最后一步审核
             */
            if(pshunxu==maxpshunxu){
                userMapper.updateProcessStatus(pid,shstatus);
            }else{
                /**
                 * 不通过的话，把流程明细改成一下，然后把任务交给下一个
                 */
                /**
                 * 流程交给下一步，因为有自己的流程顺序码，+1，就是下一个人
                 */
                userMapper.updatePmxShunxu(pid,pshunxu+1);
            }
            userMapper.updatePmxStatus(pid,userid,shstatus);
        }else{

            /**
             * 审核不通过，直接把流程该更流程明细和流程就OK啦，直接结束流程
             * 直接改成两张表就ok
             * 要是想写方法复用，那么就不要在xml中把状态写死，传递给xml
             */
            userMapper.updateProcessStatus(pid,shstatus);
            userMapper.updatePmxStatus(pid,userid,shstatus);
        }
    }

}
