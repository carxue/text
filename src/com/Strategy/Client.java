package com.Strategy;

public class Client
{
  public static void main(String[] args)
{
  Add add=new Add();
  Environment en=new Environment(add);
  System.out.println(en.conculate(3,4));
  Sub sub=new Sub();
  en.setStrategy(sub);
  System.out.println(en.conculate(8,9));
  Devide devide=new Devide();
  en.setStrategy(devide);
  System.out.println(en.conculate(8,2));
  
  
}
}
