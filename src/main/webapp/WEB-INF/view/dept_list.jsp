<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/8
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <table border="1" cellpadding="0" cellspacing="0" align="center " style="width: 500px">
            <tr>
                <th>编码</th>
                <th>部门</th>
                <th>操作</th>
            </tr>
            <c:forEach var="d" items="${dlist}">
                <tr style="text-align: center; vertical-align: center; height: 30px">
                    <td>
                        ${d.deptid}
                    </td>
                    <td>
                            ${d.dname}
                    </td>
                    <td>
                        <a href="toDeptRole.do?deptid=${d.deptid}"><button>选择角色</button></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
</body>
</html>
