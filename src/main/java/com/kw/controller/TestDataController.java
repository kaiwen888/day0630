package com.kw.controller;

import com.kw.pojo.InfoBean;
import com.kw.pojo.QueryVo;
import com.kw.pojo.UserBean;
import com.kw.service.UserService;
import com.kw.utils.JieXiXml;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 创作时间：2020/4/16 10:35
 * 作者：李增强
 */

@RestController
public class TestDataController {

    @Resource
    private UserService userService;

    /**
     * 7、post的对象参数
     */
    @RequestMapping("/testPost5")
    public InfoBean testPost5(@RequestBody InfoBean infoBean, String meaning, Integer flag){
        infoBean.setUname(infoBean.getUname()+"===兄弟我看到你了");
        System.out.println(meaning+flag);
        return infoBean;
    }

    /**
     * 6、post的对象参数
     */
    @RequestMapping("/testPost4")
    public InfoBean testPost4(@RequestBody InfoBean infoBean){
        infoBean.setUname(infoBean.getUname()+"===兄弟我看到你了");
        return infoBean;
    }

    /**
     * 5、post的对象参数
     */
    @RequestMapping("/testPost3")
    public String testPost3(@RequestBody InfoBean infoBean){
        return infoBean.toString();
    }



    /**
     * 5、post的普通参数
     */
    @RequestMapping(value = "/testPost2",method = RequestMethod.POST)
    public String testPost2(String name,Integer age){
        return name+"兄弟你终于来了，你的年龄竟然是"+age+"岁了?";
    }


    /**
     * 4、对应的是无参的post请求
     */
    @RequestMapping(value = "/testPost1",method = RequestMethod.POST)
    public String testPost1(){
        System.out.println(123);
        return "123";
    }


    /**
     * 2、3、get有参的和2和3的测试
     */
    @RequestMapping("/testGet2")
    public String testGet2(String name,Integer age){
        return name+"兄弟你终于来了，你的年龄竟然是"+age+"岁了?";
    }


    /**
     * 1、对应的是无参的get请求
     */
    @RequestMapping("/testGet1")
    public String testGet1(){
        System.out.println(123);
        return "123";
    }



    @RequestMapping("/getDataInterface")
    public String getDataInterface(String str1,String str2){
        /**
         * 这两个参数是对方传递过来的，模拟真实，在开发总部的时候，不要去考虑分部，按照文档来
         */
        str1="<MEG><UNAME>admin</UNAME><PWD>admin</PWD><CODE>01</CODE></MEG>";
        str2="<CONTENT><CARDNO>xy0003</CARDNO></CONTENT>";

        QueryVo vo =  userService.jieXiStr1(str1);
        if (vo==null){
            /**
             * 解析失败，几乎不可能，第一次项目对接有可能，后续就不能了
             */
            return "<result><MEG><CODE>0</CODE></MEG></result>";
        }else{
            /**
             * 参数1解析成功
             */
            /**
             * 去登录
             */
            UserBean userBean = new UserBean();
            userBean.setUsername(vo.getUname());
            userBean.setPassword(vo.getPwd());
            UserBean ru = userService.getLogin(userBean);
            /**
             * 判断是否为空，要是为空登录失败，不为空登录成功
             */
            if(ru==null){
                return "<result><MEG><CODE>1</CODE></MEG></result>";
            }else{
                /**
                 * 登录成功了，去解析参数2
                 */
                String cardno = userService.jieXiStr2(str2);
                if(cardno==null){
                    return "<result><MEG><CODE>0</CODE></MEG></result>";
                }else{
                    /**
                     * 都解析成功了，然后去查询，用户code和cardno去查询
                     */
                    vo.setCardno(cardno);
                    /**
                     * 查询返回，返回要是空，就没有查到，返回要是不是空，把返回的接口，拼成需要的mxl返回回去
                     * 总厂这边开始建立两个数据库，一个香烟库，一个酒水的库，对应的实体类等
                     */
                    /**
                     * vo里面有code和cardno去查询
                     */
                    String rs = userService.getInfo(vo);
                    if(rs==null){
                        /**
                         * 没有查询到
                         */
                        return "<result><MEG><CODE>2</CODE></MEG></result>";
                    }else{
                        return "<result><MEG><CODE>3</CODE></MEG>"+rs+"</result>";
                    }

                }
            }
        }
    }

    @RequestMapping("/reciDataInterface")
    public String reciDataInterfac(String str1,String str2){

        QueryVo queryVo = JieXiXml.jieXiStr1(str1);
        if (queryVo!=null){

            UserBean userBean = new UserBean();
            userBean.setUsername(queryVo.getUname());
            userBean.setPassword(queryVo.getPwd());
            UserBean login = userService.getLogin(userBean);
            if (login==null){
                return "<MEG><CODE>0</CODE><CONTENT>用户名或者密码错误</CONTENT></MEG>";
            }else {
                return userService.saveData(queryVo,str2);
            }
        }
        return "<MEG><CODE>0</CODE><CONTENT>用户名和密码为空！</CONTENT></MEG>";

    }
}
