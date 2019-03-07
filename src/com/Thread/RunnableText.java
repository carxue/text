package com.Thread;

public class RunnableText
{
	public static void main(String[] args)
	{
		Runnable r = new MyThread();
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);
		t1.start();
		t2.start();

	}
}

class MyThread implements Runnable
{
	int i;

	@Override
	public void run()
	{
		//局部变量在 上面的t1,t2在new时会分别拷贝一份局部变量所有与
		//成员变量是不同的。他们会共享同一个成员变量
		//int i=0;
		while (true)
		{
			System.out.println("你-->  " + (i++) + "  一下");
			try
			{
				Thread.sleep((long) 800);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			if (i == 50)
				break;
			if(i==20)
			{
				System.out.println("-----------------------     -----------------------");
			}
			if(i==30)
			{
				System.out.println("-------------------          -------------------------");
			}
			if(i==40)
			{
				System.out.println("------------------    爽死了,╮(╯▽╰)╭   ----------------");
			}
		}
	}
}