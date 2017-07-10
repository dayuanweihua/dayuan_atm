package com.dayuan.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dayuan.service_action.CardService;

/**
 * Servlet implementation class CheckBalanceServlet
 */
@WebServlet("/CheckBalanceServlet")
public class CheckBalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckBalanceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
  	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  		
    	System.out.println("检查余额中");
  		//HttpSession session=req.getSession();
  		//Object obj=session.getAttribute("cardNo");
  		//验证是否是当前会话,是则连接数据库，获取余额,进入余额详情页，不是则让用户重新登录		
  			req.getRequestDispatcher("WEB-INF/jsp/check_balance.jsp").forward(req,resp);
  		
  	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
