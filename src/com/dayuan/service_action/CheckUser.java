package com.dayuan.service_action;

public class CheckUser {
	public static int checkUser(String username,String password){
		if(username.equals("zhangsan")&&password.equals("123123")){
			return 1;
		}else if(username.equals("lisi")&&password.equals("123123")){
			return 1;
		}
		return 0;
	}
}
