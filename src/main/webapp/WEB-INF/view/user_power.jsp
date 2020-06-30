<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Title</title>
	<script type="text/javascript" src="../../js/jquery-1.8.2.js"></script>
	<script type="text/javascript">

	</script>
</head>
</head>
<body>
<center><h1>${bean.username}拥有的权限</h1></center>
<table align="center" cellspacing="0" cellpadding="0">
	<c:forEach var="p" items="${list}">
		<tr style="text-align: center">
			<td>${p.pname}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>