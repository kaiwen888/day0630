package com.kw.controller;

import com.alibaba.fastjson.JSON;
import com.kw.pojo.*;
import com.kw.service.RoleService;
import com.kw.service.UserService;
import com.kw.utils.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getPowerJson")
    public String getPowerList(Model model,HttpSession session){
        UserBean login = (UserBean) session.getAttribute("login");
        if (login!=null){
            List<PowerBean> list = userService.getPowerList();
            String s = JSON.toJSONString(list);
            model.addAttribute("json",s);
        }
        return "left";
    }

    @RequestMapping(value = "/getUserList")
    public String findPage(@RequestParam(defaultValue = "1") String pageNum, @RequestParam(defaultValue = "5") String pageSize,UserBean userBean, Model model){

        List<UserBean> list = userService.findUser();
        Page page = new Page(pageNum,list.size(),pageSize);

        System.out.println(userBean);

        List<UserBean> plist = userService.findPage(pageNum, pageSize,userBean);
        page.setList(plist);
        model.addAttribute("list", page.getList());
        // 总页数
        model.addAttribute("totalPage", page.getPageCount());
        // 总条数
        model.addAttribute("count", page.getCount());
        // 当前页数
        model.addAttribute("currentPage", page.getCurrentPage());
        return "user_list";
    }

    @RequestMapping(value = "/toUserDeptRole")
    public String findUserById(Integer id,Model model){
       if (id != null){
           UserBean userBean = userService.findUserById(id);

           List<DeptBean> deptList = userService.getDeptList();

           List<RoleBean> roleList = userService.getRoleList(userBean.getDeptBean().getDeptid());

           System.out.println(userBean);
           System.out.println(roleList);

           model.addAttribute("user",userBean);
           model.addAttribute("dlist",deptList);
           model.addAttribute("rlist",roleList);

           return "user_deptrole";

       }

       return "user_list";
    }

    @RequestMapping(value = "/getRlistJosn")
    @ResponseBody
    public List<RoleBean> getRoleList(Integer deptid){
       if (deptid != null){

           List<RoleBean> roleList = userService.getRoleList(deptid);

           System.out.println(roleList);
           return roleList;

       }

       return null;
    }

    @RequestMapping(value = "/saveUserDeptRole")
    public String update(@Param("deptid") Integer deptid, @Param("rid") Integer rid , @Param("id") Integer id) {
        int update = userService.update(deptid, rid, id);

        return "redirect:getUserList.do";
    }

    @RequestMapping(value = "/delete")
    public String delete(Integer id){
        int delete = userService.delete(id);
        return "redirect:getUserList.do";
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ResponseBody
    public String addUser(UserBean userBean) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String sbirthday = userBean.getSbirthday();
        Date birthday = sf.parse(sbirthday);
        userBean.setBirthday(birthday);
        int i = userService.addUser(userBean);
        if(i>0){
            return "ok";
        }else {
            return "no";
        }
    }

    @RequestMapping(value = "/toinsertUser")
    @ResponseBody
    public List<RoleBean> toinsertUser(Integer deptid ,Model model){
        System.out.println(deptid);
        List<RoleBean> rList = userService.getRoleList(deptid);
        return rList;
    }

    @RequestMapping(value = "/toaddUser")
    public String toaddUser(Model model,UserBean userBean){
        List<DeptBean> deptList = userService.getDeptList();
        model.addAttribute("dlist",deptList);
        return "user_add";
    }

    @RequestMapping(value = "/toupdateUser")
    public String toupdateUser(Model model,Integer id){
        UserBean userList = userService.getUserList(id);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = userList.getBirthday();
        String sbirthday = sf.format(birthday);
        userList.setSbirthday(sbirthday);
        List<DeptBean> deptList = userService.getDeptList();
        List<RoleBean> roleList = userService.getRoleList(userList.getDeptid());
        System.out.println(userList);
        System.out.println(deptList);
        System.out.println(roleList);
        model.addAttribute("user",userList);
        model.addAttribute("dlist",deptList);
        model.addAttribute("rlist",roleList);
        return "user_update";
    }

    @RequestMapping(value = "/updateUser")
    @ResponseBody
    public String updateUser(Model model,UserBean userBean) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String sbirthday = userBean.getSbirthday();
        Date birthday = sf.parse(sbirthday);
        userBean.setBirthday(birthday);
        int i = userService.updateUser(userBean);
        if(i>0){
            return "ok";
        }else {
            return "no";
        }
    }

    @RequestMapping("/getLogin")
    public String getLogin(HttpSession session, UserBean userBean){
        UserBean login = userService.getLogin(userBean);
        if (login!=null){
            session.setAttribute("login",login);
            return "main";
        }
        return "../../index";
    }

    @RequestMapping("/selectPower")
    public String selectPower(Model model, Integer id) {
        UserBean bean = userService.findAll(id);
        System.out.println(bean.getRid());
        System.out.println(bean.getRoleBean().getRid());
        if (bean != null){
            List<PowerBean> list = userService.getPower(bean.getRoleBean().getRid());
            model.addAttribute("bean",bean);
            model.addAttribute("list",list);
            System.out.println(bean);
            System.out.println(list);
        }
        return "user_power";
    }





    /**学生请假流程
     * 保存我的审核
     */
    @RequestMapping("/saveWdsh")
    public String saveWdsh(HttpServletRequest request, Integer pid, Integer shstatus){
        UserBean ub = (UserBean)request.getSession().getAttribute("ub");
        userService.saveWdsh(pid,shstatus,ub.getId());
        return "redirect:getQjShList.do";
    }




    /**
     * 1号员工是 乐柠主任 22号角色
     * 2号员工是 乐柠院长 21号角色
     *
     *
     * 乐柠讲师  23号角色
     * 乐柠的辅导员 24号角色
     * 乐柠的学生是 25号角色
     */

    /**
     * 我的审核 23 号权限 需要乐柠的讲师、辅导员、主任和院长
     * 还有把23的父亲也赋给  乐柠的这些角色、还有爷爷
     */

    /**
     * 管理登录上来查询我的审核
     */

    @RequestMapping("/getQjShList")
    public String getQjShList(HttpServletRequest request,Model model){
        UserBean ub = (UserBean)request.getSession().getAttribute("ub");
        /**
         * 先拿着我的id去流程明细表中查询一下，有没有需要我审核的流程，有的话，再去流程表中把流程查出来，
         * 和学生是相反的，学生是先查流程，再查明细，老师是先查明细，再查流程
         * select pid from t_pmx a where a.pstatus =1 and userid=37 其实我就需要pid，查询流程id，因为我的页面展示的时候，
         * 我需要知道这个是谁请假的，请了多次时间，什么时候开始的，那个班级的
         */

        List<QjVo> list = userService.getQjshListByUserid(ub.getId());
        model.addAttribute("list", list);
        return "qjsh_list";
    }



    /**
     * 这个注解虽然不是spring的但是他由Spring来管理啊
     * 400错误，说明类型对不上，日期类型不一样，需要日累类型转换
     * 直接使用实体类来接收，需不需要来转换
     * 因为我么带过来的日期有小时，还有日期需要处理
     * 在控制层处理，也可以在entity里面处理
     */
    @RequestMapping("/saveStuQj2")
    public String saveStuQj2(ProcessBean pb){
        /**
         * 接下来则么处理，开始走我们的业务流程，三个流程，属于业务型的代码，所以我们可以全部在sevice层搞定
         */

        userService.saveStuQj2(pb);
        return "redirect:getStuJtList.do";
    }


    /**
     *
     */
    @RequestMapping("/getStuJtList")
    public String getStuJtList(HttpServletRequest request,Model model){
        /**
         * 需要把这个学生的情况请查询到，然后再去查询这个学的请假情况，我们先直接去去页面，发起请假
         */
        UserBean ub = (UserBean)request.getSession().getAttribute("ub");

        /**
         *   <th>编号</th>            流程表
         *   <th>请假时长</th>         流程表
         *   <th>请假时间</th>         流程表
         *   <th>开始时间</th>         流程表
         *   <th>结束时间</th>         流程表
         *   <th>角色</th>
         *   <th>操作者</th>
         *   <th>审批状态</th>
         */

        /**
         * 角色名称怎么出来，在流程表中，要查询这个流程有没有结束，
         * 要是结束了，我们需要去明细表中查询，是谁结束的这个流程
         *  通过的话，那就是顺序码最大的那个人的的用户id
         *  没有通过的话，我去查询谁的在明细表中，查询谁把我决绝的，操作的的id，查询他的审批码是2
         *  流程没有结束的情况下
         *  直接去明细表中，查询流程状态等于1的这个人就OK啦
         */

        /**
         * 我们写一个请假的vo然后需要什么数据就往里面扔里面就OK啦
         */

        /**
         * vo里面第一次只能查出来一部分字段，最好不要去联查，出来之后在判断查询
         */
        List<QjVo> list = userService.getStuQjListBySid(ub.getId());
        System.out.println(list);
        model.addAttribute("list", list);
        return "stuqj_list";
    }


    /**
     * 转发到学生请假的页面
     */
    @RequestMapping("/toStuQj")
    public String toStuQj(HttpServletRequest request,Model model){
        UserBean ub = (UserBean)request.getSession().getAttribute("ub");
        model.addAttribute("stu", ub);
        return "stu_qj";
    }










}
