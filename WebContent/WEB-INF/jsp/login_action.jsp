

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String password = request.getParameter("password");
		String username = request.getParameter("username");

		//CardService cardService = new CardService();
		System.out.println(username);
		//int code = cardService.checkUser(username, password);
		int code=1;
		if (code == 1) {
			session.setAttribute("password", password);
			session.setAttribute("username", username);
	%>
	<jsp:forward page="homepage.jsp"></jsp:forward>
	<%
		} else if (code == 0) {
	%>
	<script type="text/javascript">
		history.back()
	</script>
	<%
		}
	%>
</body>
</html>