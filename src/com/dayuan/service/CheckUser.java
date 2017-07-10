package com.dayuan.service;

public class CheckUser {
	public static int checkUser(String username,String password){
		//System.out.println("check");
		//System.out.println("username:"+username+",password:"+password);
		if((username!=null&&username.equals("zhangsan"))&&(password!=null&&password.equals("123123"))){
			//System.out.println("123");
			return 1;
		}else if((username!=null&&username.equals("lisi"))&&(password!=null&&password.equals("123123"))){
			return 1;
		}
		
		return 0;
	}
}
