package com.Thread.soulv;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MoneyTest {
	public static void main(String[] args) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS");
		Money myMoney = new Money();
		myMoney.setMoney(30d);
		MoneyThread mt = new MoneyThread(myMoney, 2d);
		MoneyThread mt1 = new MoneyThread(myMoney, 2d);
		mt.setPriority(1);
		mt.start();
		mt1.start();
	}
}
