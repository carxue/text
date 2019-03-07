package com.Thread;

public class Threadtext
{
	public static void main(String[] args)
	{
	  Thread1 t=new Thread1();
	  Thread2 th=new Thread2();
	  t.start();
	  th.start();
	}
  
}
class Thread1 extends Thread
{
	@Override
	public void run()
	{
		for(int i=0;i<50;i++)
	 	{
	 		System.out.println("Xuekui"+i);
	 	}
	}
 	
}
class Thread2 extends Thread
{
	@Override
	public void run()
	{
		for(int i=0;i<50;i++)
	 	{
	 		System.out.println("Hello"+i);
	 	}
	}
 	
}
