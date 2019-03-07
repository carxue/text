package com.Thread;

public class IncreateThread  extends Thread
{
	private Sample sample;
	public  IncreateThread(Sample sample)
	{
		this.sample=sample;
	}
   @Override
public void run()
{
	   for(int i=0;i<1;i++)
	   {
		   try
		{
			Thread.sleep((long)Math.random()*1000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	sample.increate();
}
}
