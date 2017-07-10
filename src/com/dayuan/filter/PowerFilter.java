package com.dayuan.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 1、权限过滤器，用于过滤所有符合要求的Servlet 2、实现方法强制转换为HttpServletRequest,HttpServletResponse
 * 获取HttpSession实例session，获取session的attribute("cardNo"),返回一个obj
 * 通过请求的URI（即资源路径,是一个字符串），如果这个字符串以什么结尾endsWith("Servlet类名")
 * 并且obj==null，就重定向到登录页面 Servlet Filter implementation class PowerFilter
 * 最后如果满足obj==null，执行了动作后，return,不让它继续往下传递
 */
@WebFilter("/*")
public class PowerFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public PowerFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("cardNo");
		  
		if (req.getRequestURI().endsWith("CheckBalanceServlet")
				|| req.getRequestURI().endsWith("TransferServlet")
				|| req.getRequestURI().endsWith("TakeMoneyActionServlet")
				||req.getRequestURI().endsWith("GoBackHomepage")
				||req.getRequestURI().endsWith("SaveMoneyServlet")
				||req.getRequestURI().endsWith("SaveMoneyActionServlet")
				||req.getRequestURI().endsWith("不用过滤重新登录LoginBackServlet")
				||req.getRequestURI().endsWith("TakeMoneyServlet")
				||req.getRequestURI().endsWith("不用过滤登录Servlet")) {
			if (obj == null) {
				resp.sendRedirect("loginpage.jsp");
				return;
			}
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
