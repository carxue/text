package com.Thread.soulv;

public class MoneyThread extends Thread {
	
	private Money money;
	
	private Double due;
	
	public MoneyThread(Money money,double due) {
		this.money = money;
		this.due = due;
	}


	@Override
	public void run() {
		try {
			while(true){
				if(money.getMoney()>=0)
					money.dueMoney(due, this.getName());
				else
					break;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public Money getMoney() {
		return money;
	}


	public void setMoney(Money money) {
		this.money = money;
	}


	public Double getDue() {
		return due;
	}


	public void setDue(Double due) {
		this.due = due;
	}
	
}

