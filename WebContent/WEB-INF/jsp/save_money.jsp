<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>存款操作页面</title>
</head>
<body>
	<% String cardNo = (String)session.getAttribute("cardNo");%>
	<h1 style="color:red">存款操作页面</h1>
	<h2 style="color:red">用户:<%=cardNo %></h2>
	<form action="SaveMoneyActionServlet">
		存款金额:<input type="text" name="save_money">
		<input type="submit" name="save_money_submit">
	</form>
	<a href="GoBackHomepage">返回主页</a>
</body>
</html>