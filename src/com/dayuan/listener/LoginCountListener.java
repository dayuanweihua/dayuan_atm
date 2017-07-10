package com.dayuan.listener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * 监听者会话属性绑定类（会话中属性改变触发），用于监听有多少个在线用户，并且保证一个用户只能在一个地方登陆。 实现方法：
 * 1、获取绑定对象的名字，判断此名字是否和登陆成功后所设定的会话的cardNo属性一致。
 * 2、一致则把此对象对应的值（即：se.getVlue()），add进一个Set（选Set是为了保证没有重复的值）。
 * 3、这样就能获取到登录的用户（se.getCode())，和登陆的人数(Set的size())
 * 4、最后用一个HashMap来存储cardNo作为key，session作为value
 * 5、如果session==null，则意味着该用户第一次登录，则count++，并且把当前用户的cardNo，session存入HashMap
 * 6、如果session！=null，则意味着该用户已经登录，就要移除刚才的会话属性cardNo，再重新放入此次会话的key value。
 * 并提示刚才的用户已经被挤下线。（在会话中设置一个消息属性，在jsp中展示，用于提示用户。）
 * 二、如果触发了会话属性移除事件
 * 1、计数器减一 count--
 * 2、从Set中移除这个会话的属性对象的值，这样就能获取谁下线了，和当前在线人数Set的size
 * 3、同时移除HashMap中对应的键值对，（se.getCode(),se.getSession())。以保证用户登录的一致性。 Application
 * Lifecycle Listener implementation class LoginCountListener
 *
 */
@WebListener
public class LoginCountListener implements HttpSessionAttributeListener {
	private int count;
	private Set<String> users = new HashSet<String>();
	private HashMap<String, Object> userMap=new HashMap<String,Object>();

	/**
	 * Default constructor.
	 */
	public LoginCountListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
	 */
	public void attributeAdded(HttpSessionBindingEvent se) {
		HttpSession sessionA=se.getSession();
		String cardNo=(String) sessionA.getAttribute("cardNo");	
		String name = se.getName();		
		if (name.equals("cardNo")) {
			users.add(cardNo);
			System.out.println(cardNo + "用户登录,在线人数" + users.size());

			Object obj = userMap.get(cardNo);
			if (obj == null) {
				count++;
				userMap.put(cardNo, se.getSession());
			} else {
				HttpSession session = (HttpSession) obj;
				session.removeAttribute("cardNo");
				userMap.put(cardNo, se.getSession());
				System.out.println(cardNo + "用户被迫下线");
				session.setAttribute("msg", "您的账户再其他地方已登录，已结束此次会话");
				//session.invalidate();
			}
		}
	}

	/**
	 * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
	 */
	public void attributeRemoved(HttpSessionBindingEvent se) {
		count--;
		
		String cardNo=(String) se.getCode();
			users.remove(cardNo);
			System.out.println(cardNo + "用户已下线,当前在线人数：" + users.size());
			userMap.remove(cardNo);
		
	}

	/**
	 * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
	 */
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
	}

}
