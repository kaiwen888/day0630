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
    <script type="text/javascript">

    </script>
</head>
<body>
<center>
    <form action="getUserList.do" method="post">
        按照用户名:<input type="text" name="username"><br>
        按照年龄:<input type="text" name="sbirthday">--至--
        <input type="text" name="ebirthday">
        <input type="submit" value="查询">
    </form>
</center>
<center><a href="toaddUser.do"><butto>添加</butto></a></center>
        <table id="mytable" border="1" cellpadding="0" cellspacing="0" align="center" style="width: 700px">
            <tr>
                <th>编码</th>
                <th>姓名</th>
                <th>密码</th>
                <th>年龄</th>
                <th>生日</th>
                <th>部门</th>
                <th>角色</th>
                <th>操作</th>
            </tr>
            <c:forEach var="u" items="${list}">
                <tr style="text-align: center; vertical-align: center; height: 30px">
                    <td>
                        ${u.id}
                    </td>
                    <td>
                            ${u.username}
                    </td>
                    <td>
                            ${u.password}
                    </td>
                    <td>
                            ${u.age}
                    </td>
                    <td>
                        <fmt:formatDate value="${u.birthday}" pattern="yyyy-MM-dd"></fmt:formatDate>
                    </td>
                    <td>
                            ${u.deptBean.dname}
                    </td>
                    <td>
                            ${u.roleBean.rname}
                    </td>
                    <td>
                        <a href="toUserDeptRole.do?id=${u.id}"><button>分配部门和职业</button></a>
                        <a href="delete.do?id=${u.id}"><button>删除</button></a>
                        <a href="toupdateUser.do?id=${u.id}"><button>修改</button></a>
                        <a href="selectPower.do?id=${u.id}"><button>查看权限</button></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="getUserList.do"><button>第一页</button> </a>
        <a href="/getUserList.do?pageNum=${currentPage-1 }">上一页</a>
        <a href="/getUserList.do?pageNum=${currentPage+1 }">下一页</a>
        <a href="/getUserList.do?pageNum=${totalPage }">最后一页</a>

        当前第${currentPage }页，共${totalPage }页，总条数为${count }
</body>
</html>
