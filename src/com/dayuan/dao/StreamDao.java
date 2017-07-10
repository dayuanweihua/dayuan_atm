package com.dayuan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.dayuan.util.DbUtil;

public class StreamDao {
	public static void main(String[] args) {
		StreamDao sd=new StreamDao();
		try {
			sd.transferStream("1000","1002","sss",1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void transferStream(String outCard, String inCard,String inUserName, int money)
			throws Exception {
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		Connection conn = null;
	
		try {
			conn = DbUtil.getConnection();
			conn.setAutoCommit(false);

			ps1 = conn
					.prepareStatement("insert into a_money_stream(oper_type,oper_money) values(?,?)",Statement.RETURN_GENERATED_KEYS);
			ps1.setInt(1, 3);
			ps1.setInt(2, money);
			ps1.executeUpdate();

			ResultSet rs = ps1.getGeneratedKeys();
			int tempId = 0;
			if (rs.next()) {
				tempId = rs.getInt(1);
			}
		
			ps2 = conn.prepareStatement(
					"insert into a_transfer(out_card,in_card,in_user_name,stream_id) values(?,?,?,?)");
			ps2.setString(1,outCard);
			ps2.setString(2,inCard);
			ps2.setString(3,inUserName);
			ps2.setInt(4,tempId);
			ps2.executeUpdate();
			
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw new Exception(e.getMessage());
		} finally {
			DbUtil.closeAll(ps1,ps2, conn);
		}
	}
}
