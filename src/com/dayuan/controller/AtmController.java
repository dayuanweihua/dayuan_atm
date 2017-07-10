package com.dayuan.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.dayuan.service_action.CardService;
import com.dayuan.util.Constant;

/**
 * 控制层，通过用户的指令执行ATM操作流程 例如：输入银行卡卡号，判断卡号是否存在。是则继续下一步，请求输入密码，检查密码，正确则进入主页。
 * 主页：1:查询余额 2:取款 3：转账 4：存款 5：退出 1.查询余额，使用刚才验证后的用户输入的账户来查询余额。
 * 2.取款，使用刚才验证后的用户输入的账户来对数据库进行操作，完成取款。 3.转账，首先要求用户输入对方账户，判断对方账户是否存在，
 * 是则要求用户输入转账金额，判断用户余额是否大于转账金额，是则转账成功，否则，返回主页（或者请求用户重新输入转账金额）
 * 4.存款，使用刚才验证后的用户输入的账户来获取账户信息，然后对账户进行存款。 5.退出
 * 
 * @author wei
 * 
 */
public class AtmController {
	public CardService cardService = new CardService();

	public void checkCardPassword(String cardNo) {
		// -----校验银行卡密码开始-------
		BufferedReader in = null;
		int code = 0;
		System.out.println("请输入银行卡密码");
		in = new BufferedReader(new InputStreamReader(System.in));
		String password;
		try {
			password = in.readLine();
			code = cardService.checkPassword(cardNo, password);
			if (code != 1) {
				if (code == Constant.PASSWORD_ERROR) {
					System.out.println("银行卡密码输入错误");
				}
				System.out.println(code);
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// public void checkCardNo() {
	// BufferedReader in = null;
	// int code = 0;
	// String cardNo;
	//
	// // System.out.println("ATM机启动成功");
	//
	// // -----校验银行卡卡号开始--------
	// String[] strs = checkCardInfo();
	// code = Integer.parseInt(strs[1]);
	// int count = 0;
	// while (true) {
	// if (count > 1) {
	// System.out.println("您输入银行卡次数已达上限，系统即将退出");
	// return;
	// }
	// if (code == Constant.CARD_ERROR) {
	// System.out.println("银行卡不存在，请重新输入!");
	// strs = checkCardInfo();
	// code = Integer.parseInt(strs[1]);
	// count++;
	// continue;
	// }
	// cardNo = strs[0];
	// break;
	// // -----校验银行卡卡号结束-------}
	// }
	// }

	public void atm() {

		BufferedReader in = null;
		System.out.println("欢迎使用xxxx银行ATM系统");
		try {
			// 流程第一步，验证卡号是否存在
			in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("请输入银行卡号");
			String cardNo = in.readLine();
			while (true) {
				cardNo = cardService.checkCardExists(cardNo);// 返回此方法里检验的为正确的cardNo
				if (cardNo == Constant.OPER_FAIL) {
					System.out.println("卡号不存在，请重新输入");
				} else {
					System.out.println("银行卡号验证成功，请输入密码");
					break;
				}
			}
			// 卡号验证成功后进入流程第二步，验证银行卡密码是否正确,只能验证三次
			// System.out.println("银行卡号验证成功，请输入密码");
		
				
					in = new BufferedReader(new InputStreamReader(System.in));// 第一次输入
					String password = in.readLine();
					int c = cardService.checkPassword(cardNo, password);// 这里不需要返回此方法验证后正确的密码，因为后面用不到了
					if (c == Constant.FAIL) {
						return;
					} else if (c == Constant.SUCCESS) {
						System.out.println("密码验证成功，即将进入主页面");			
					} 
					
				

			
			// 流程第三步，卡号、密码全部验证成功后，进入ATM机主页。
			mainPage(cardNo);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取银行卡卡号
	 */
	// public String[] checkCardInfo() {
	//
	// try {
	// System.out.println("请输入银行卡卡号");
	// BufferedReader in = new BufferedReader(new InputStreamReader(
	// System.in));
	// String cardNo = in.readLine();
	// int code = cardService.checkCardExists(cardNo);
	// return new String[] { cardNo, String.valueOf(code) };
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return new String[] { "", String.valueOf(Constant.FAIL) };
	// }

	public void mainPage(String cardNo) {
		try {
			/**
			 * 1:查询余额 2:取款 3：转账 4：存款 5：退出
			 */
			System.out.println("---请选择功能---");
			System.out.println("1:查询余额  2:取款  3：转账  4：存款  5：退出");

			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));

			int oper = Integer.parseInt(in.readLine());

			switch (oper) {
			case 1: // 输入1查询余额
				int money = cardService.getMoneyByCardNo(cardNo);
				System.out.println("余额" + (money / 100.0) + "元");
				mainPage(cardNo);
				break;
			case 2:// 输入2进行取款操作
				System.out.println("你正在进行取款操作");
				System.out.println("请输入取款金额,以整数位单位");
				in = new BufferedReader(new InputStreamReader(System.in));
				
				try {
					int takeMoney = 100*Integer.parseInt(in.readLine());
					cardService.checkTakeMoneySum(cardNo,takeMoney);
					cardService.serviceTakeMoney(cardNo, takeMoney);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				mainPage(cardNo);
				break;
			case 3:// 输入3进行转账操作
				in = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("请输入要转入的账户");
				String transferInto = in.readLine();
				transferInto = cardService.checkCardExists(transferInto);// 获取正确的转入卡号
				in = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("请输入转账金额");
				int transferMoney =Integer.parseInt(in.readLine());			
				//CardDao cardDao = new CardDao();
				cardService.transferService(cardNo, transferInto, transferMoney);
				mainPage(cardNo);
				break;
			case 4:// 输入4进行存款操作
				in = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("请输入存款金额,以整数为单位");
				int putMoney = Integer.parseInt(in.readLine());
				cardService.saveMoney(cardNo, putMoney);
				mainPage(cardNo);
				break;
			case 5:// 输入5退出
				System.out.println("欢迎再次使用xxxx银行ATM系统，再见！");
				break;
			default:
				System.out.println("");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
