package com.Strategy;

public class Environment
{
  private Strategy strategy;
  public Environment(Strategy strategy)
  {
	  this.strategy=strategy;
  }
public Strategy getStrategy()
{
	return strategy;
}
public void setStrategy(Strategy strategy)
{
	this.strategy = strategy;
}
public int conculate(int a,int b)
{
	return strategy.conculate(a,b);
}
  
}
