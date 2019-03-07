package com.reflection;

import java.lang.reflect.Method;

public class ReflectionText
{
  public static void main(String[] args) throws Exception
{
	Class<?> classType=Class.forName("java.lang.String");
	Method[] meth=classType.getDeclaredMethods();
	for(Method m:meth)
	{
		System.out.println(m);
	}
	
}
}
