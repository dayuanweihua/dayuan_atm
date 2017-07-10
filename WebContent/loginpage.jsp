<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
</head>
<body>	
	<h1 style="color:red">welcome login page</h1>
	<%Object count=application.getAttribute("count"); %>
	当前在线人数<%=count %><br/>
	
	<form   method="GET" action="LoginServlet">	
		账户：<input type="text" name="cardNo" /><br/>
		密码：<input type="text" name="password" /><br/>
			<input type="submit" name="submit" value="确认登录" />		
	</form> 	
		${1}	
</body>
</html>