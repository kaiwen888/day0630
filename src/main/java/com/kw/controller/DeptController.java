package com.kw.controller;

import com.kw.pojo.DeptBean;
import com.kw.pojo.RoleBean;
import com.kw.pojo.UserBean;
import com.kw.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/getDeptList")
    public String findUserById( Model model){

            List<DeptBean> deptList = deptService.getDeptList();

            model.addAttribute("dlist",deptList);

            return "dept_list";


    }

    @RequestMapping(value = "/toDeptRole")
    public String toDeptRole( Integer deptid,Model model) {

        List<Integer> list = deptService.RidsByDeptid(deptid);
        List<RoleBean> roleList = deptService.getRoleList();
        DeptBean bean = deptService.getDeptByDeptid(deptid);

        model.addAttribute("dept",bean);
        model.addAttribute("list",list);
        model.addAttribute("rlist",roleList);

            return "dept_role";


    }

    @RequestMapping(value = "/saveDeptRole")
    public String saveDeptRole(Integer deptid,Integer[] rids){
            deptService.updateDeptRole(deptid,rids);

            return "redirect:getDeptList.do";


    }
}
