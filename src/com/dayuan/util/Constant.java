package com.dayuan.util;

/**
 * 1xxx 鍗＄浉鍏崇紪鐮� 2xxx 鐢ㄦ埛鐩稿叧缂栫爜 3xxx 杞处鐩稿叧缂栫爜
 */
public interface Constant {

	/**
	 * 鎿嶄綔澶辫触
	 */
	String OPER_FAIL="0";
	/**
	 * 操作成功
	 */
	String OPER_SUCCESS="1";
	int SUCCESS = 1;

	/**
	 * 鎿嶄綔澶辫触
	 */
	int FAIL = 0;

	/**
	 * 閾惰鍗″崱鍙蜂笉瀛樺湪
	 */
	int CARD_ERROR = 1001;

	/**
	 * 閾惰鍗″瘑鐮侀敊璇�
	 */
	int PASSWORD_ERROR = 1002;
	
	/**
	 * 鍗′腑浣欓涓嶈冻
	 */
	int MONEY_FEW=1003;
}
