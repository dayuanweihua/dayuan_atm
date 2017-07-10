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

/**	过滤所有的servlet，把请求的字符设置为UTF-8，
 * 1.先强制转换为HTTP request，2.req.setCharacterEncoding("UTF-8");
 * Servlet Filter implementation class CharacterEncodFilter
 */
@WebFilter("/*")
public class CharacterEncodFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CharacterEncodFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
			
		HttpServletRequest req=(HttpServletRequest)request;
		req.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
