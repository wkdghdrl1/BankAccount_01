package com.biz.bank.exec;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.biz.bank.model.BankBalanceVO;
import com.biz.bank.service.BankService_01;

public class BankExec_01 {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String balance = "src/com/biz/bank/txt/balance.txt";
		// bs 선언부만 try밖으로 빼내고
		// null값으로 초기화한다.
		BankService_01 bs = null;
		try {
			// try내에서 다시 객체를 초기화해서
			// 사용할 수 있도록 생성
			bs = new BankService_01(balance);
			bs.readBalance();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true) {

			int intMenu = bs.selectMenu();

			System.out.println("계좌번호:");
			String accNum = scan.nextLine();

			if (intMenu == -9)
				break;
			if (intMenu == 1) { // 입금처리
				System.out.println("입금금액>>");
				String strInputMoney = scan.nextLine();
				int intInputMoney = Integer.valueOf(strInputMoney);

				bs.inputMoney(accNum, intInputMoney);
			}

			if (intMenu == 2) { // 출금처리
				System.out.println("출금금액>>");
				String strOutputMoney = scan.nextLine();
				int intOutputMoney = Integer.valueOf(strOutputMoney);

				bs.outputMoney(accNum, intOutputMoney);
			}

		}
		/*
		 * if (vo == null) { System.out.println("계좌번호가 없습니다. \n계좌번호를 확인해주세요!!"); } else
		 * { System.out.println(vo.getBalance()); }
		 * 
		 * }
		 */

	}
}
