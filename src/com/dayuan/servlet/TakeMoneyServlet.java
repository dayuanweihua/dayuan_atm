package com.dayuan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dayuan.javabean.Card;
import com.dayuan.service_action.CardService;

/**
 * Servlet implementation class TakeMoneyServlet
 */
@WebServlet("/TakeMoneyServlet")
public class TakeMoneyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakeMoneyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
  	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("取款验证中");
//    	HttpSession session=req.getSession();
//  		Object obj=session.getAttribute("cardNo");
  		//String cardNo=(String)obj;
  		//int money=Integer.valueOf(req.getParameter("take_money"));
  		//CardService cardService=new CardService();
  		//int balance=cardService.getMoneyByCardNo(cardNo);
  		//balance>=money
  		//cardService.serviceTakeMoney(cardNo, money);
//  		if(obj!=null){
//  			System.out.println("验证成功，进入取款操作页面取款");  			
  			req.getRequestDispatcher("/WEB-INF/jsp/take_money_action.jsp").forward(req,resp);
//  		}else{
//  			resp.sendRedirect("loginpage.jsp");
//  		}
  	}

  

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
