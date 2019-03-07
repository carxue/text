package com.reflection;

import java.lang.reflect.Method;

public class ReflectionText1 
{
 
	  public int adds(int a,int b)
	  {
		  return a+b;
	  }
	  public String names(String name)
	  {
		  return name;
	  }
  
	  public static void main(String[] args)
	{
		Class<?> classType=ReflectionText1.class;
		Object result=null;
		try
		{
			Object reflectionText1= classType.newInstance();
			Method addMethod=classType.getMethod("adds",new Class[]{int.class,int.class});
			result=addMethod.invoke(reflectionText1,new Object[]{6,8});
		}
		catch (InstantiationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println((Integer)result);
	}
}
