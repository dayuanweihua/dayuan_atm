<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
</head>
<body>
		<%
			String cardNo=(String)session.getAttribute("cardNo");	
			
		%>
		
		<h1 style="color:red">主页</h1>
		<h2 style="color:red">用户:<%=cardNo %></h2>
		<h3>请选择您所需的业务:</h3>
		
		
	<a href="CheckBalanceServlet">查询余额</a>
	<a href="TakeMoneyServlet">取款</a>	
	<a href="SaveMoneyServlet">存款</a>	
	<a href="TransferServlet">转账</a>
	<a href="LoginBackServlet">重新登录</a>

	


</body>
</html>