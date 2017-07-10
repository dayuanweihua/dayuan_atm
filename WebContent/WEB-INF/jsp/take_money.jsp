<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>取款详情页面</title>
</head>
<body>

	<%
		Object cardNo =  session.getAttribute("cardNo");
		Object takeMoney = session.getAttribute("takeMoney");
		Object nowMoney=session.getAttribute("nowMoney");
		if (cardNo == null) {
	%>
	<jsp:forward page="loginpage.jsp"></jsp:forward>
	<%
		}
	%>
	<h1 style="color: red">取款详情页面</h1>
	<h2 style="color: red">
		用户:<%=cardNo%>
		取款金额：<%=takeMoney%>
		当前余额：<%=nowMoney %>
	</h2>
	<a href="GoBackHomepage">返回主页</a>
</body>
</html>