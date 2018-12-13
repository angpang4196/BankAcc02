package com.biz.bank.exec;

import java.util.Scanner;

import com.biz.bank.service.BankService;

public class BankExec {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String strFileName = "src/com/biz/bank/bankbalance.txt";
		BankService bs = new BankService(strFileName);

		while (true) {
			bs.readFile();

			System.out.print("1.입금 2.출금 3.조회 4.종료 중 입력 >>");
			Scanner scan = new Scanner(System.in);
			String strWrite = scan.nextLine();
			int intWrite = Integer.valueOf(strWrite);
			if (intWrite == 1) {
				bs.bankInput(); // 입금
			}
			if (intWrite == 2) {
				bs.bankOutput(); // 출금
			}
			if (intWrite == 3) {
				bs.viewInfo(); // 조회
			}
			if (intWrite == 4) {
				System.out.println("찾아주셔서 감사합니다.");
				break; // 종료
			}
			
			bs.saveInfo();

		}
	}
}
