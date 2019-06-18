package com.biz.bank.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.biz.bank.model.BankBalanceVO;

public class BankService_01 {

	List<BankBalanceVO> balanList;
	FileReader filereader;
	BufferedReader buffer;
	Scanner scan;
	private Object[] balanists;

	public BankService_01(String balance) throws FileNotFoundException {

		scan = new Scanner(System.in);
		balanList = new ArrayList<BankBalanceVO>();
		filereader = new FileReader(balance); // 파일명 삽입
		buffer = new BufferedReader(filereader);

	}

	public void readBalance() throws IOException {
		String reader = "";
		while (true) {
			reader = buffer.readLine();
			if (reader == null)
				break;
			String[] balanceLists = reader.split(":");
			BankBalanceVO vo = new BankBalanceVO();

			vo.setAcc(balanceLists[0]);
			vo.setBalance(Integer.valueOf(balanceLists[1]));
			vo.setDate(balanceLists[2]);
			balanList.add(vo);

		}

	}

	public BankBalanceVO PickAcc(String accNum) {
		String acc = "0001";
		/*
		 * balanceList 에서 0001인 데이터를 찾고 그 계좌의 현잔액을 표시
		 */
		int len = balanList.size();
		int index = 0;
		BankBalanceVO vo = null;
		for (index = 0; index < len; index++) {
			vo = balanList.get(index);
			if (vo.getAcc().equals(accNum)) {
				// System.out.println(vo.getBalance());
				return vo;

			}
		}
		return null;

	}

	/*
	 * 계좌번호로 pickAcc()로부터 vo 값을 추출하고, balance 값과 money 값을 더하여 vo의 balance에 저장하고 콘솔에
	 * 보여주는 코드
	 */
	public void inputMoney(String acc, int money) {

		BankBalanceVO vo = PickAcc(acc);
		int inTotal = money + vo.getBalance();
		vo.setBalance(inTotal);
		int bal = vo.getBalance();

		Date date = new Date(System.currentTimeMillis());

		SimpleDateFormat sf = new SimpleDateFormat("yyyy--MM-dd");
		String curDate = sf.format(date);

		LocalDate localDate = LocalDate.now();
		vo.setDate(localDate.toString());
		System.out.println("-------------------------------------");
		System.out.println(vo);
		System.out.println("-------------------------------------");

	}

	public void outputMoney(String acc, int money) {
		BankBalanceVO vo = PickAcc(acc);
		int inTotal = vo.getBalance() - money  ;
		vo.setBalance(inTotal);
		int bal = vo.getBalance();
		if (bal < money) {
			System.out.println("잔액부족!!");
			return;
		}
		vo.setBalance(bal - money);

		Date date = new Date(System.currentTimeMillis());

		SimpleDateFormat sf = new SimpleDateFormat("yyyy--MM-dd");
		String curDate = sf.format(date);

		LocalDate localDate = LocalDate.now();
		vo.setDate(localDate.toString());
		System.out.println("-------------------------------------");
		System.out.println(vo);
		System.out.println("-------------------------------------");

	}

	public int selectMenu() {
		System.out.println("=================================");
		System.out.println("1.입금       2.출금       -9.종료");
		System.out.println("---------------------------------");
		System.out.println("업무선택>>");
		String strMenu = scan.nextLine();

		int intMenu = 0;
		try {
			intMenu = Integer.valueOf(strMenu);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return intMenu;

	}

}
