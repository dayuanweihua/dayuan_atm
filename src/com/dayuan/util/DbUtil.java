package com.dayuan.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("数据库驱动加载成功");
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/atm?useSSL=true", "root",
					"123123");
			System.out.println("数据库连接成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeAll(AutoCloseable... objs) {
		for (AutoCloseable obj : objs) {
			try {
				if (obj != null) {
					try {
						obj.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
