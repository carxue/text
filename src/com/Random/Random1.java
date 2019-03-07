package com.Random;

import java.util.Random;

public class Random1
{
	public static int random(int a)
	{
		Random random1 = new Random();
		return random1.nextInt(a);
	}

	public static void main(String[] args)
	{
		int b = 10;
		for (int i = 0; i < 20; i++)
		{
			System.out.println(random(b));
		}
	}
}
