package com.Strategy;

public class Sub implements Strategy
{

	@Override
	public int conculate(int a, int b)
	{
		return a-b;
	}

}
