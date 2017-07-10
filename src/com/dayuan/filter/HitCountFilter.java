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

/**
 * Servlet Filter implementation class HitCountFilter
 */
@WebFilter("/*")
public class HitCountFilter implements Filter {
	
	private int count;
	
    /**
     * Default constructor. 
     */
    public HitCountFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**	查看url，host，pv
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		count++;
		HttpServletRequest req=(HttpServletRequest)request;
		String url=req.getRequestURL().toString();
		String host=req.getRemoteHost();
//		System.out.println("url："+url);
//		System.out.println("host:"+host);
//		System.out.println("pv："+count);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
