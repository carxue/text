package com.Thread;

public class ClientXue
{
	public static void main(String[] args)
	{
		Sample sample = new Sample();
		Thread t1 = new IncreateThread(sample);
		Thread t2 = new DecreateThread(sample);
		
		t1.start();
		t2.start();
		

	}
}
