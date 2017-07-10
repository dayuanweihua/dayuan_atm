<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test jsp</title>
</head>
<body>
	test jsp
<input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" /><br/> 
	<img src="file:///D:/2.jpg" >
	<img src="file:///D:\picture\2.jpg" >
	<img src="file:///D/picture/2.jpg" >
	<img  src="https://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E9%AB%98%E6%B8%85%E5%8A%A8%E6%BC%AB&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=1640220652,2632338153&os=2240667731,2897694834&simid=&pn=0&rn=1&di=1&ln=1647&fr=&fmq=1462357247335_R&fm=&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=&istype=2&ist=&jit=&bdtype=-1&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fpic67.nipic.com%2Ffile%2F20150514%2F21036787_181947848862_2.jpg&rpstart=0&rpnum=0&adpicid=0"/>
	<img alt="" src="https://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E9%AB%98%E6%B8%85%E5%8A%A8%E6%BC%AB&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=1187232368,3307975561&os=3782864634,3333526882&simid=4261045733,734115665&pn=8&rn=1&di=24160027430&ln=1647&fr=&fmq=1462357247335_R&fm=&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=0,0&istype=2&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fb.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Fb21bb051f81986183ffa25944eed2e738bd4e665.jpg&rpstart=0&rpnum=0&adpicid=0">
	<%
		StringBuffer url=request.getRequestURL();
		String uri=request.getRequestURI();
		System.out.println("url:"+url+",uri:"+uri);
		
		Cookie[] cookies=request.getCookies();
		for(Cookie c:cookies){
			System.out.println("Cookie name:"+c.getName()+",Cookie value:"+c.getCode());
		}
		%>
	<%
	String applicationInit=application.getInitParameter("adminEmail");
	System.out.println("application init:"+applicationInit);
	
	String configInit=config.getInitParameter("adminEmail");
	System.out.println("congif init:"+configInit);
	System.out.println(getServletContext().getInitParameter("username"));
	
	String agent=request.getHeader("user-agent");
	String cookie=request.getHeader("Cookie");
	String accept=request.getHeader("accept");
	System.out.println("accept:"+accept);
	System.out.println("agent:"+agent+",Cookie:"+cookie);
	
	Integer in=request.getIntHeader("1");
	System.out.println(in);
	
	String localName=request.getLocalName();
	int localPort=request.getLocalPort();
	System.out.println("localName:"+localName+",localPort:"+localPort);
	
	
	
	
	
	
	%>
	<%
	
		String s=config.getInitParameter("username");
	System.out.println(s);
%>
	<%
	
	System.out.println(request.getLocalAddr());
	
	%>
<%	String test=(String)getServletContext().getAttribute("test");
	String action=(String)application.getAttribute("action");
	System.out.println(test+"--"+action);	
%>
<%application.setAttribute("action2","hello2"); %>
</body>
</html>