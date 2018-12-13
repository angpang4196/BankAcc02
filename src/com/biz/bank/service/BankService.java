package com.biz.bank.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.biz.bank.vo.BankVO;

public class BankService {

	List<BankVO> bankList;

	String strFileName;

	public BankService(String strFileName) {
		bankList = new ArrayList();
		this.strFileName = strFileName;
	}

	public void readFile() {
		FileReader fr;
		BufferedReader buffer;

		try {
			fr = new FileReader(strFileName);
			buffer = new BufferedReader(fr);

			while (true) {
				String strRead = buffer.readLine();
				if (strRead == null)
					break;
				String[] strSp = strRead.split(":");

				BankVO vo = new BankVO();

				vo.setStrId(strSp[0]);
				vo.setIntBalance(Integer.valueOf(strSp[1]));
				vo.setStrLastDate(strSp[2]);

				bankList.add(vo);

			}
			buffer.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // readFile method end

	public BankVO findId(String strId) {
		for (BankVO vo : bankList) {
			if (vo.getStrId().equals(strId)) {
				System.out.println(vo);
				return vo;
			}
		}
		System.out.println("계좌번호 오류");
		return null;
	} // findId method end (RETURN)

	public void bankInput() {
		Scanner scan = new Scanner(System.in);

		System.out.print("계좌번호를 입력 해 주세요 >>>");
		String strWrite = scan.nextLine();
		BankVO b = findId(strWrite);
		if(b == null) {
			System.out.println("계좌번호 없음");
			return;
		} 
		
		int iB = b.getIntBalance();
		System.out.print("입금액 >> ");
		String strBalance = scan.nextLine();
		
		int intB = Integer.valueOf(strBalance);
		int lB = iB + intB;
		b.setIntBalance(lB);
		System.out.println("입금완료");
		
		SimpleDateFormat v = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss", Locale.KOREA);
		Date d = new Date();
		String strDate = v.format(d);
		b.setStrLastDate(strDate);
		
		System.out.println("================================================");
		System.out.println("계좌번호 : " + b.getStrId());
		System.out.println("입금액 : " + intB);
		System.out.println("잔액 : " + lB);
		System.out.println("거래 날짜/시간 : " + strDate);
		System.out.println("------------------------------------------------");
		
	} // bankInput method end 
	
	public void bankOutput() {
		Scanner scan = new Scanner(System.in);

		System.out.print("계좌번호를 입력 해 주세요 >>>");
		String strWrite = scan.nextLine();
		BankVO b = findId(strWrite);
		if(b == null) {
			System.out.println("계좌번호 없음");
			return;
		} 
		
		int iB = b.getIntBalance();
		System.out.print("출금액 >> ");
		String strBalance = scan.nextLine();
		
		int intB = Integer.valueOf(strBalance);
		if(intB > iB) {
			System.out.println("잔액 부족입니다.");
			return;
		}
		int lB = iB - intB;
		b.setIntBalance(lB);
		System.out.println("잠시만 기달려주세요");
		
		SimpleDateFormat v = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss", Locale.KOREA);
		Date d = new Date();
		String s = v.format(d);
		b.setStrLastDate(s);
		
		System.out.println("================================================");
		System.out.println("계좌번호 : " + b.getStrId());
		System.out.println("출금액 : " + intB);
		System.out.println("잔액 : " + lB);
		System.out.println("거래 날짜/시간 : " + s);
		System.out.println("------------------------------------------------");
		System.out.println("출금이 완료되었습니다.");
		
	}
	
	public void viewInfo() {
		Scanner scan = new Scanner(System.in);
		System.out.println("계좌번호를 입력 해 주세요 >>");
		String strWrite = scan.nextLine();
		
		BankVO b = findId(strWrite);
		
		System.out.println("================================================");
		System.out.println("계좌번호 : " + b.getStrId() );
		System.out.println("잔액 : " + b.getIntBalance());
		System.out.println("최근 거래 일자 : " + b.getStrLastDate());
		System.out.println("------------------------------------------------");
	}
	
	public void saveInfo() {
		String strFileName = "src/com/biz/bank/bankbalance1.txt";
		PrintWriter pw ;
		
		try {
			pw = new PrintWriter(strFileName);
			
			for(BankVO vo : bankList) {
				pw.println(vo.getStrId() + ":" + vo.getIntBalance() + ":" + vo.getStrLastDate());
			}
			pw.close();
			System.out.println("저장완료");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
