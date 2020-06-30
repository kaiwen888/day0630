package com.kw.controller;

import com.kw.Result;
import com.kw.pojo.DeptBean;
import com.kw.pojo.UserBean;
import com.kw.service.UserService;
import com.kw.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
@RequestMapping("vue")
public class VueController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserList")
    @ResponseBody
    public List<UserBean> findPage(){

        List<UserBean> list = userService.findUser();
        return list;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result delete(Integer id){
        int delete = userService.delete(id);
        if (delete>0){
            return new Result(true, "删除成功");
        }
        return new Result(false, "删除失败");
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public Page findPage(@RequestParam(defaultValue = "1") String pageNum, @RequestParam(defaultValue = "5") String pageSize, UserBean userBean, Model model){

        List<UserBean> list = userService.findUser();
        Page page = new Page(pageNum,list.size(),pageSize);

        System.out.println(userBean);

        List<UserBean> plist = userService.findPage(pageNum, pageSize,userBean);
        page.setList(plist);

        return page;
    }

    @RequestMapping(value = "/toaddUser")
    @ResponseBody
    public List<DeptBean> toaddUser(Model model,UserBean userBean){
        List<DeptBean> deptList = userService.getDeptList();
        return deptList;
    }
}
