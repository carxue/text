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
		//�ֲ������� �����t1,t2��newʱ��ֱ𿽱�һ�ݾֲ�����������
		//��Ա�����ǲ�ͬ�ġ����ǻṲ��ͬһ����Ա����
		//int i=0;
		while (true)
		{
			System.out.println("��-->  " + (i++) + "  һ��");
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
				System.out.println("------------------    ˬ����,�r(�s���t)�q   ----------------");
			}
		}
	}
}