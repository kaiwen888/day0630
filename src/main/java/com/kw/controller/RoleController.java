package com.kw.controller;

import com.alibaba.fastjson.JSON;
import com.kw.pojo.PowerBean;
import com.kw.pojo.RoleBean;
import com.kw.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/getRoleList")
    public String getRoleList(Model model){
        List<RoleBean> roleList = roleService.getRoleList();
        model.addAttribute("rlist",roleList);
        return "role_list";
    }

    @RequestMapping("/getPowerByRole")
    public String getPowerByRole(Model model,Integer rid){
        List<PowerBean> list = roleService.getPowerList(rid);
        String string = JSON.toJSONString(list);
        model.addAttribute("json",string);
        model.addAttribute("rid",rid);
        return "role_power";
    }

    @RequestMapping("/savePower")
    public String savePower(Integer rid,String ids){
        System.out.println(ids);
        roleService.savePowerAndRole(rid,ids);
        return "redirect:getRoleList.do";
    }


}
