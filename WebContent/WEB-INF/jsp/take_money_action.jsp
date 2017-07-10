<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>取款操作页面</title>
</head>
<body>
	<%
		String cardNo = (String) session.getAttribute("cardNo");
		Object balance = session.getAttribute("balance");
	%>
	<h1 style="color: red">取款操作页面</h1>
	<h2 style="color: red">
		用户:<%=cardNo%>
		余额：<%=balance%></h2>
	<form action="TakeMoneyActionServlet">
		取款金额：<input type="text" name="take_money"  /> 
		<input type="submit" name="take_money_submit" value="确认">
	</form>
	<a href="GoBackHomepage">返回主页</a>
</body>
</html>