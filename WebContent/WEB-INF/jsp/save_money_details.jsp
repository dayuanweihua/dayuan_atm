<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>存款详情</title>
</head>
<body>

	<%
		Object cardNo = session.getAttribute("cardNo");		
		Object saveMoney=session.getAttribute("save_money");
		Object nowBalance=session.getAttribute("nowBalance");
	%>
	<h1 style="color: red">存款详情</h1>
	<h2 style="color: red">
		用户:<%=cardNo%>
		存入金额：<%=saveMoney%>
		您当前余额为：<%=nowBalance%></h2>
	<a href="GoBackHomepage">返回主页</a>
</body>
</html>