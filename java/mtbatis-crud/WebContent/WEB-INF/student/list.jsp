<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 
<style type="text/css">
	table{
		border-collapse: collapse; 
		width:500px;
		text-align:center;
	}
	table th,table td{
		border: 1px solid black; 
	}
</style>
 
</head>
<body>
	  <a href="/mtbatis-crud/StudentServlet?cmd=input">添加</a> 
	<!-- <a href="/StudentServlet/WebContent/WEB-INF/student/edit.jsp">添加</a> -->
	<table>
		<tr>
			<th>用户名</th>
			<th>密码</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${stus}" var="stu">
			<tr>
				<td>${stu.username}</td>
				<td>${stu.password}</td>
				<td><a href="/mtbatis-crud/StudentServlet?cmd=delete&id=${stu.id}">删除</a>|
					<a href="/mtbatis-crud/StudentServlet?cmd=input&id=${stu.id}">修改</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>