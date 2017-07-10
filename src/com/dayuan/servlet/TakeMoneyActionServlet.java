package com.dayuan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TakeMoneyActionServlet
 */
@WebServlet("/TakeMoneyActionServlet")
public class TakeMoneyActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakeMoneyActionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int takeMoney=Integer.valueOf(request.getParameter("take_money"));
		HttpSession session=request.getSession();
		int balance=Integer.valueOf((String)session.getAttribute("balance"));
		int nowMoney=balance-takeMoney;
		session.setAttribute("takeMoney",takeMoney);
		session.setAttribute("nowMoney",nowMoney);
		request.getRequestDispatcher("/WEB-INF/jsp/take_money.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
