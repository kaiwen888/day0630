<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/8
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="../../js/jquery-1.8.2.js"></script>
    <script>



        function getrolelist (obj) {
            /**
             *ajax去获取列表在查询，异步操作
             */


            $.post("toinsertUser.do", {deptid:obj.value}, function (data) {
                    var rselect  = $("[name=rid]");
                    //rselect.html("<option value='-1'>--请选择角色--</option>");
                    document.getElementsByName("rid")[0].length=1;
                    var rlist = data;

                    for(var x=0;x<rlist.length;x++){
                        //要拼接一个option，在之前要先把原来的删掉
                        rselect.append("<option value="+rlist[x].rid+">"+rlist[x].rname+"</option>")
                    }
                }
            )
        }

        var add = function () {
            var id  = $("[name=id]").val();
            var username  = $("[name=username]").val();
            var password  = $("[name=password]").val();
            var age  = $("[name=age]").val();
            var sbirthday  = $("[name=sbirthday]").val();
            var deptid  = $("[name=deptid]").val();
            var rid  = $("[name=rid]").val();
            var bean = {id:id,username:username,password:password,age:age,deptid:deptid,rid:rid}
            // $.post("addUser.do", {userBean:bean,bithday:birthday}, function (data) {
            //         var rlist = data;
            //
            //         if (rlist=="ok"){
            //             location.href="getUserList.do"
            //         }else {
            //             alert("添加失败")
            //         }
            //     }
            // )
            alert(sbirthday)
            $.ajax({
                type: "POST",
                url: "addUser.do",
                data: {id:id,username:username,password:password,age:age,sbirthday:sbirthday,deptid:deptid,rid:rid},
                success: function(data){
                    var rlist = data;
                    if (rlist=="ok"){
                        location.href="getUserList.do"
                    }else {
                        alert("添加失败")
                    }
                }
            });
        }
    </script>
</head>
<body>

<form action="addUser.do" method="post">
         编码:<input type="text" name="id" placeholder="请输入编码"><br><br>

            姓名:<input type="text" name="username" placeholder="请输入姓名"><br><br>

            密码:<input type="text" name="password" placeholder="请输入密码"><br><br>

            年龄:<input type="text" name="age" placeholder="请输入年龄"><br><br>


            生日:<input type="text"  name="sbirthday" placeholder="请输入生日"><br><br>

            分配部门:<select name="deptid" onchange="getrolelist(this)">
                <option>--请选择部门--</option>
                <c:forEach var="d" items="${dlist}">
                    <option value="${d.deptid}">${d.dname}</option>
                </c:forEach>
            </select><br><br>

        分配角色: <select name="rid">
                 <option>--请选择角色--</option>
            </select><br><br>

    <input type="button" onclick="add()" value="添加">
</form>
</body>
</html>
