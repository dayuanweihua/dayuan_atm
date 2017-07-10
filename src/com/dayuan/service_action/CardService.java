package com.dayuan.service_action;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dayuan.dao.CardDao;
import com.dayuan.javabean.Card;
import com.dayuan.util.Constant;
import com.dayuan.util.DbUtil;
import com.dayuan.util.MD5Util;

/**
 * 涓氬姟閫昏緫灞�
 * 
 * @author 24725
 * 
 */
public class CardService {
	/**
	 * 閾惰鍗″崱鍙烽暱搴︿负鍥哄畾鍊�19,鐢ㄦ埛鍒ゆ柇鐢ㄦ埛杈撳叆鐨勮处鎴烽暱搴︽槸鍚﹀尮閰嶃��
	 */
	private static final int CARDNO_LENGTH = 19;
	private static final int PASSWORD_LENGTH = 6;

	private CardDao cardDao = new CardDao();// 瀹炰緥鍖朌AO灞傜殑涓�涓璞�

	/**
	 * 楠岃瘉鍗″彿鏄惁瀛樺湪锛屼笉瀛樺湪杩斿洖1001锛屽瓨鍦ㄨ繑鍥�1 棣栧厛鐢ㄥ崱鍙蜂綅鏁伴獙璇侊紝濡傛灉浣嶆暟涓嶄负19鐨勶紝璇锋眰鐢ㄦ埛妫�鏌ヨ緭鍏ョ殑鍗″彿鏄惁姝ｇ‘锛屽苟瑕佹眰鐢ㄦ埛鏀规鍚庤緭鍏ャ��
	 * 杩欐牱鐨勫ソ澶勬槸鍑忓皯浜嗕竴娆℃暟鎹簱鐨勮闂��
	 * 
	 * @param cardNo
	 * @return
	 */
	public String checkCardExists(String cardNo) {
		BufferedReader in = null;
		try {

			// 棣栧厛鐢ㄥ垽鏂敤鎴疯緭鍏ュ崱鍙烽暱搴﹀垽鏂槸鍚︽纭�
			while (cardNo.length() != CARDNO_LENGTH) {
				System.out.println("鎮ㄨ緭鍏ョ殑鍗″彿闀垮害涓嶆纭紝鎴栧崱鍙蜂笉瀛樺湪锛岃鏍稿疄鍚庡啀杈撳叆");
				in = new BufferedReader(new InputStreamReader(System.in));
				cardNo = in.readLine();
			}
			while (true) {
				Card card = cardDao.getCardByCardNo(cardNo); // 鑾峰彇鍗′俊鎭�
				System.out.println("妫�鏌ュ崱鍙锋槸鍚﹀瓨鍦�");
				if (card == null) {
					System.out.println("鎮ㄨ緭鍏ョ殑鍗″彿闀垮害涓嶆纭紝鎴栧崱鍙蜂笉瀛樺湪锛岃鏍稿疄鍚庡啀杈撳叆");
					in = new BufferedReader(new InputStreamReader(System.in));
					cardNo = in.readLine();
				} else {
					System.out.println("楠岃瘉鎴愬姛");
					return cardNo;//
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cardNo; // 鎴愬姛鍒欒繑鍥為獙璇佷负姝ｇ‘鐨刢ardNo
	}
	
	public int checkUser(String username,String password){
		try {
			Card card=cardDao.getCardByCardNo(username);
			if(card.getCardNo()==username){
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	
	/**
	 * 鍙栭挶锛屽彇閽辨椂宸茬粡杩涘叆浜嗕富椤碉紝涓嶉渶瑕佸啀楠岃瘉璐︽埛鍜屽瘑鐮� 棣栧厛閫氳繃鍒氭墠鐨勫崱鍙疯幏鍙栭摱琛屽崱淇℃伅涓处鎴蜂綑棰�
	 * 鍐嶅垽鏂綑棰濇槸鍚﹀ぇ浜庣瓑浜庡彇娆鹃噾棰�,澶т簬绛変簬鍒欏彲浠ュ彇閽憋紝灏忎簬鍒欎笉鑳藉彇閽�
	 * 
	 * @param cardNo
	 * @param money
	 * @return
	 */
	public void serviceTakeMoney(String cardNo, int money) {
		try {
			cardDao.daoTakeMoney(cardNo, money);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * checkPassword鏂规硶楠岃瘉鐢ㄦ埛杈撳叆鐨勫瘑鐮佹槸鍚︽纭紝鐢ㄦ埛鏈変笁娆¤緭鍏ユ満浼�
	 * 
	 * @param cardNo
	 * @param password
	 * @return
	 */
	public int checkPassword(String cardNo, String password) {
		// 涓轰粈涔堝垰鎵嶅凡缁忔鏌ヤ簡璐︽埛浜嗭紝鎬庝箞杩樿妫�鏌ヤ竴娆�,杩欐牱涓嶅張鍥炲埌绗竴姝ヤ簡鍚�,涓嶅彲浠ョ洿鎺ヨ繛鎺ユ暟鎹簱鍚楋紵
		BufferedReader in=null; 
		try {
			// 棣栧厛鐢ㄥ垽鏂敤鎴疯緭鍏ュ瘑鐮侀暱搴﹀垽鏂槸鍚︽纭�
			
			int count=1;
			
			try {
				Card card = cardDao.getCardByCardNo(cardNo);
				if (card == null) {
					return Constant.CARD_ERROR;
				}
				while(count<=2){
				if (password.length() != PASSWORD_LENGTH
						|| !card.getPassword().equals(
								MD5Util.MD5(password + "dayuan"))) {
					System.out.println("鎮ㄨ緭鍏ョ殑瀵嗙爜闀垮害鏈夎锛屾垨鑰呭瘑鐮侀敊璇紝璇烽噸鏂拌緭鍏�");
					System.out.println("鎮ㄨ繕鏈夛細" + (3 - count) + "娆℃満浼�");
					in = new BufferedReader(new InputStreamReader(System.in));// 绗竴娆¤緭鍏�
					password = in.readLine();
					count++;
					if(count==3){
						System.out.println("鎮ㄨ緭鍏ユ鏁拌繃澶氾紝璐︽埛宸茬粡閿佸畾锛岃鍒版煖鍙拌В閿侊紝绯荤粺鍗冲皢閫�鍑�");
						return Constant.FAIL;
					}
				}
				System.out.println("count"+count);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.SUCCESS;
	}
	/**
	 * 检查form表单里的password
	 * @param password
	 * @return
	 */
	public int checkPassword2(String cardNo, String password){
		int code=0;
		try {
			code=cardDao.daoCheckPassword(cardNo, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return code;
	}
	
	public String[] checkCardExists2(String cardNo){
			String[] strings=null;
		try {
			strings=cardDao.daoCheckCardExists2(cardNo);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strings;
	}
	
	public int getMoneyByCardNo(String cardNo) {
		try {
			Card card= cardDao.getCardByCardNo(cardNo);
				return card.getMoney();
			}catch (Exception e) {
				e.printStackTrace();
				return Constant.FAIL;
			}
	}

	public void transferService(String transferOut, String transferInto,
			int transferMoney) {
		try {
			cardDao.transfer(transferOut, transferInto, transferMoney);
		} catch (Exception e) {
			System.out.println("鍙戠敓寮傚父锛岃鍙栨秷鎿嶄綔");
			e.printStackTrace();
		}
	}

	public void saveMoney(String cardNo, int putMoney) {
		try {
			cardDao.daoSaveMoney(cardNo, putMoney);
		} catch (Exception e) {
			System.out.println("鍙戠敓寮傚父锛岃鍙栨秷鎿嶄綔");
			e.printStackTrace();
		}
	}

	public void checkTakeMoneySum(String cardNo, int takeMoney) {
		try {
			cardDao.daoCheckTakeMoneySum(cardNo,takeMoney);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

	
}
