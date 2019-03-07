package com.Thread.soulv;

public class Money {
	private static Double money= 0.0d;

	public synchronized void dueMoney(Double due,String threadName) throws InterruptedException{
		if(getMoney()>=0){
			setMoney(getMoney()-due);
			System.out.println("线程:"+threadName+" 扣减了一次额度:￥"+due+" 剩余:"+getMoney());
			Thread.sleep(500);
		}else{
			System.out.println("你的金额已经用完!");
		}
	}

	public  Double getMoney() {
		return money;
	}

	public  void setMoney(Double money) {
		Money.money = money;
	}
	
}
