package com.dayuan.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**	这个Servlet是用于指出一些注意事项。
 * 1、可以在一个Servlet中进行所有的页面跳转
 * 	实现方式：在jsp页面中的form表单的 action属性后面添加参数method="xxx",
 * 	然后在那个单独的Servlet中获取到这个参数，
 * 	如果这个参数不为空，并且为某个值，就执行请求分派。
 * 	例：<form action="TakeMoneyServlet?method=takeMoney" method="POST">
 * 	 	String method=request.getParameter("method");
 * 		if(method==null||method.equals(""){
 * 			request.getRequestDispatcher(
 * 				"WEB-INF/jsp/homepage.jsp"）.forward(request,response);
 * 		}else if(method.equals("某一动词")){
 * 			分派到执行那个动作的jsp去
 * 		}
 * 	这种方式不常用，一般是每个控制流转都会写一个Servlet
 * Servlet implementation class NoticeServlet
 */
@WebServlet("/NoticeServlet")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
