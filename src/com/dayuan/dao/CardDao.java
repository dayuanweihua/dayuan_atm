package com.dayuan.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.dayuan.javabean.Card;
import com.dayuan.util.Constant;
import com.dayuan.util.DbUtil;

public class CardDao {

	
	private static Connection conn =DbUtil.getConnection();
	/**
	 * dao层用于连接数据库 根据银行卡号查询银行卡信息
	 * 
	 * @param cardNo
	 * @return
	 * @throws Exception
	 */
	// public static void main(String[] args) {
	// CardDao cardDao = new CardDao();
	// try {
	// cardDao.transfer("6225666666666666666", "6225555555555555555", 2);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	public Card getCardByCardId(int id) throws Exception {
	
		PreparedStatement ps = null;
		try {
			
			ps = conn// 设置一个缓存的sql语句,用于后面调用
					.prepareStatement("select * from a_card where id=?");

			ps.setInt(1, id);// 执行sql语句，并指定刚才的？为cardNo

			ResultSet rs = ps.executeQuery();// 执行缓存的sql语句，返回该执行的结果集

			if (rs != null && rs.next()) { // 判断结果集是否为空，并且结果集中是否还有下一个元素
				Card card = new Card(); // 新建一个对象card来存储结果集中的数据,即把数据库中的数据
										// 提取出来，放到card中，来完成数据库数据的映射。
				// 获取结果集中的所有字段，一一对应的设置为card的ivar
				card.setId(rs.getInt("id"));
				card.setCardNo(rs.getString("card_no"));
				card.setMoney(rs.getInt("money"));
				card.setPassword(rs.getString("password"));
				card.setPersonId(rs.getInt("persion_id"));
				card.setVersion(rs.getInt("version"));
				return card;// 返回Card类型的一个对象card
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return null;
	}

	public Card getCardByCardNo(String cardNo) throws Exception {
		
		PreparedStatement ps = null;
		try {
			
			ps = conn// 设置一个缓存的sql语句,用于后面调用
					.prepareStatement("select * from a_card where card_no=?");

			ps.setString(1, cardNo);// 执行sql语句，并指定刚才的？为cardNo

			ResultSet rs = ps.executeQuery();// 执行缓存的sql语句，返回该执行的结果集

			if (rs != null && rs.next()) { // 判断结果集是否为空，并且结果集中是否还有下一个元素
				Card card = new Card(); // 新建一个对象card来存储结果集中的数据,即把数据库中的数据
										// 提取出来，放到card中，来完成数据库数据的映射。

				// 获取结果集中的所有字段，一一对应的设置为card的ivar
				card.setId(rs.getInt("id"));
				card.setCardNo(rs.getString("card_no"));
				card.setMoney(rs.getInt("money"));
				card.setPassword(rs.getString("password"));
				card.setPersonId(rs.getInt("person_id"));
				card.setVersion(rs.getInt("version"));
				return card;// 返回Card类型的一个对象card
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return null;

	}

	public int transfer(String transferOut, String transferInto, int money)
			throws Exception {
		Connection conn = DbUtil.getConnection();// 连接数据库
		PreparedStatement psOut = null;
		try {
			conn.setAutoCommit(false); // 开启事务
			psOut = conn.prepareStatement( // 预编译sql语句
					"update a_card set money=?,version=version+1 where id=? and version=?");

			Card outCard = getCardByCardNo(transferOut);// 通过卡号获取转出账户信息,并且
														// 转出账户余额>转账金额
			if (outCard.getMoney() < money * 100) {// 判断账户余额是否足够
				System.out.println("您的余额不足，请重新选择");
				return Constant.MONEY_FEW;
			}

			psOut.setInt(1, outCard.getMoney() - money * 100);// 设置？？?
			psOut.setInt(2, outCard.getId());
			psOut.setInt(3, outCard.getVersion());
			int c = psOut.executeUpdate(); // 获取执行后影响行数
			if (c <= 0) { // 如果影响行数<=0,说明执行失败
				conn.rollback(); // 事务回滚
				// transfer(transferOut, transferInto, money); //继续执行转账动作
			}
			// 成功则提交事务

			Card inCard = getCardByCardNo(transferInto);// 转入账户也是一样的流程
			psOut.setInt(1, inCard.getMoney() + money * 100);
			psOut.setInt(2, inCard.getId());
			psOut.setInt(3, inCard.getVersion());
			c = psOut.executeUpdate();
			if (c <= 0) {
				// transfer(transferOut, transferInto, money);
				conn.rollback();
			}
			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DbUtil.closeAll(psOut, conn);
		}
		System.out.println("转账成功，转账：" + money + "元");
		return Constant.SUCCESS;
	}

	/**
	 * 取钱，取钱时已经进入了主页，不需要再验证账户和密码 首先通过刚才的卡号获取银行卡信息中账户余额
	 * 再判断余额是否大于等于取款金额,大于等于则可以取钱，小于则不能取钱
	 * 
	 * @param cardNo
	 * @param money
	 * @return
	 * @throws Exception
	 */
	public int daoTakeMoney(String cardNo, int money) throws Exception {
	
		PreparedStatement ps = null;
		try {
			Card card = getCardByCardNo(cardNo);
			while (true) {
				if (card.getMoney() >= money) {
					conn.setAutoCommit(false);
					ps = conn
							.prepareStatement("update a_card set money=?,version=version+1 where card_no=? and version=?");
					ps.setInt(1, card.getMoney() - money);
					ps.setString(2, cardNo);
					ps.setInt(3, card.getVersion());
					int c = ps.executeUpdate();
					if (c <= 0) {
						conn.rollback();
					}
					conn.commit();
					System.out.println("操作成功，取款金额：" + money / 100.0 + "元");
					return Constant.SUCCESS;
				} else {
					System.out.println("你的余额不足，请重新选择转账金额");
				}
			}
		} finally {
			DbUtil.closeAll(ps, conn);
		}
	}

	/**
	 * 悲观锁实现
	 * 
	 * @return
	 * @throws Exception
	 */
	public int transferForUpdate(String outCardNo, String inCardNo,
			int transferMoney, Connection conn) throws Exception {

		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		if (conn == null) {
			conn = DbUtil.getConnection();
		}
		// CardService cardService=new CardService();
		try {
			conn.setAutoCommit(false);
			Card outTemp = getCardByCardNo(outCardNo);
			Card outCard = getCardByCardId(outTemp.getId());

			Card inTemp = getCardByCardNo(inCardNo);
			Card inCard = getCardByCardId(inTemp.getId());

			// 余额不足，无法转账，只能存款后再转账，或者退出
			// if(outCard.getMoney()<transferMoney*100){
			// System.out.println("余额不足，请重新选择");
			// return Constant.MONEY_FEW;
			// }
			// 查询两个账户
			ps1 = conn
					.prepareStatement("select * from a_card where id=? for update");
			ps1.setInt(1, outCard.getId());
			ps1.setInt(1, inCard.getId());
			ps1.executeQuery();

			// if(!rs.next()){ //为什么不用判断，ResultSet rs=
			// conn.rollback();
			// }
			// 执行转出账户转钱
			ps2 = conn
					.prepareStatement("update a_card set money=? where id=? ");
			ps2.setInt(1, outCard.getMoney() - transferMoney * 100);
			ps2.setInt(2, outCard.getId());
			ps2.executeUpdate();
			// if(c<=0){ int c=
			// conn.rollback();
			// }
			// 执行转入账户收钱
			ps2.setInt(1, inCard.getMoney() + transferMoney * 100);
			ps2.setInt(2, inCard.getId());
			ps2.executeUpdate();
			// if(c<=0){
			// conn.rollback();
			// }
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			throw new SQLException(e.getMessage());
		} finally {
			DbUtil.closeAll(ps1, ps2, conn);
		}
		return Constant.SUCCESS;
	}

	public void daoSaveMoney(String cardNo, int putMoney) throws Exception {
	
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			Card card = getCardByCardNo(cardNo);
			ps = conn
					.prepareStatement("update a_card set money=?,version=version+1 where id=? and version=?");
			ps.setInt(1, card.getMoney() + 100 * putMoney);
			ps.setInt(2, card.getId());
			ps.setInt(3, card.getVersion());
			int c = ps.executeUpdate();
			if (c <= 0) {
				conn.rollback();
			}
			conn.commit();
			System.out.println("操作成功存入金额：" + putMoney + "元");
			// System.out.println("余额为：+card.getMoney() );没有连接数据库，数据为旧数据
		} catch (SQLException e) {
			conn.rollback();
			throw new SQLException(e.getMessage());
		} finally {
			DbUtil.closeAll(ps, conn);
		}
	}

	public int daoCheckPassword(String cardNo,String password) throws Exception{
		Card card=getCardByCardNo(cardNo);
		if(password.equals(card.getPassword())){
			return 1;
		}
		return 0;		
	}
	
	/**
	 * 返回大小为4的String数组，第一个元素保存卡号是否存在，若存在则第二个元素保存密码
	 * 数组，有4个元素，分别为：1、状态码，2、card密码3、card主键，4、card余额
	 * @param cardNo
	 * @return
	 * @throws Exception
	 */
	public String[] daoCheckCardExists2(String cardNo) throws Exception{
		Card card=getCardByCardNo(cardNo);
		if(card==null){
			return new String[]{"0"};
		}
		return new String[]{"1",card.getPassword(),String.valueOf(card.getId()),String.valueOf(card.getMoney())};
	}
	
	/**
	 * select * from a_momey_stream;
	 * 
	 * select SUBSTR(oper_time,1,10)from a_money_stream;
	 * 
	 * select * from a_money_stream where card_id=1 and oper_type=1 and
	 * oper_time>='2017-5-12 00:00:' and oper _time<='2017-05-12 23:59:59';
	 * 
	 * select * from a_money_stream where card_id=1 and oper_type=1 and
	 * SUBSTR(oper_time,1,10)='2017-05-12';
	 * 
	 * @param cardNo
	 * @param takeMoney
	 * @throws Exception
	 */
	public void daoCheckTakeMoneySum(String cardNo, int takeMoney)
			throws Exception {
		
		//Date date=new Date();
//		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
//		String time=format.format(date);
		
		Card card = getCardByCardNo(cardNo);
		Connection conn = DbUtil.getConnection();
		int id=card.getId();
		
		
		PreparedStatement ps = conn.prepareStatement(
		  "select * from a_money_stream where card_id=? and oper_type=1 and SUBSTR(oper_time,1,10)=?");
			ps.setInt(1,id);
		//	ps.setDate(2,time);
	}

	// public static void main(String[] args) {
	// Connection conn = DbUtil.getConnection();
	// CardDao cardDao = new CardDao();
	// try {
	// cardDao.transferForUpdate("6225666666666666666",
	// "6225555555555555555", 1, conn);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
}
