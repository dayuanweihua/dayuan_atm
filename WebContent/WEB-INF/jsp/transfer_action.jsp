<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>转账操作页面</title>
</head>
<body>
	<h1 style="color:red">转账操作页</h1>
	<%String cardNo=(String)session.getAttribute("cardNo"); %>
	<h2 style="color:red">转账人:<%=cardNo %></h2>
	<%String money=request.getParameter("transferMoney"); %>
	
	
	<form action="ransfer_action.jsp">
		转入账户：<input type="text" name="input_user"/><br/>
		转账金额：<input type="text" name="input_money"/><br/>
		<input type="submit" name="transfer_submit" value="确认">
	</form>
	<br/>
	<a href="GoBackHomepage">返回主页</a>
</body>
</html>