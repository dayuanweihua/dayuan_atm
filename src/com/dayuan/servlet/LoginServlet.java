package com.dayuan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dayuan.dao.CardDao;
import com.dayuan.javabean.Card;
import com.dayuan.service.CheckUser;
import com.dayuan.service_action.CardService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}
	
	
	
	



	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		super.service(arg0, arg1);
	}







	@Override
	public void init() throws ServletException {
		System.out.println("init LoginServlet");
	}

	/**		登录页面，有用户输入的账户和密码，可以根据这两个信息获取到账户的所有信息，保存在一个数组中.
	 * 		数组，有4个元素，分别为：1、状态码，2、card密码3、card主键，4、card余额
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("loginServlet doGet...");
		String cardNo = req.getParameter("cardNo");
		String password = req.getParameter("password");
		
		//创建CardService实例，用于连接数据库验证卡信息，		
		CardService cardService=new CardService();	
		//此方法返回一个数组，第一个元素表示登录状态码，1成功第二个元素保存密码，0失败，
		String[] strings=cardService.checkCardExists2(cardNo);
		
		//如果为0则表示卡号错误,返回登录页面
		if(Integer.parseInt(strings[0])==0){
			System.out.println("卡号错误，返回重新登录");
			resp.sendRedirect("loginpage.jsp");
			return;
		}
		
		//验证密码，正确则设置会话属性，1、状态码，2、card密码3、card主键，4、card余额,并进入主页，错误则返回登录页面
		if(password.equals(strings[1])){
			//生成一个session
			HttpSession session = req.getSession();
			session.setAttribute("cardNo", cardNo);
			session.setAttribute("password", password);
			session.setAttribute("cardId",strings[2]);
			session.setAttribute("balance",strings[3]);
			System.out.println("卡号，密码验证成功，进入主页");
			req.getRequestDispatcher("/WEB-INF/jsp/homepage.jsp").forward(req, resp);
			return;
		}else{
			System.out.println("密码错误，返回重新登录");
			resp.sendRedirect("loginpage.jsp");
		}		
		// String sessionC=(String)session.getAttribute("cardNo");
		// System.out.println("sessionAttribute:"+sessionC);
		//验证银行卡号和密码
		
		// System.out.println("code="+code);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("loginServlet doPost");
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
