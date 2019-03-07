package com.Thread;

public class ClientText1
{
	public static void main(String[] args)
	{
		Bank bank = new Bank();
		Thread m1 = new ThreadManey(bank);
		bank=new Bank();
		Thread m2 = new ThreadManey(bank);
		m1.start();
		m2.start();
	}
}

class Bank
{
	private int maney = 1000;
	private Object object =new Object();

	public  int getManey(int number)
	{
		synchronized(object)
		{
		if (number < 0)
		{
			return -1;
		}
		if (number > maney)
		{
			return -2;
		}
		if (maney < 0)
		{
			return -3;
		}
		else
		{
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{

				e.printStackTrace();
			}
			maney -= number;
		}
		return maney;
	}
	}
}

class ThreadManey extends Thread
{
	private Bank bank;

	public ThreadManey(Bank bank)
	{
		this.bank = bank;
	}

	@Override
	public void run()
	{
		System.out.println("left maney:"+bank.getManey(800));
	}
}
