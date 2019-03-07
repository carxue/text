package com.reflection;

import java.lang.reflect.Field;

public class ChangNane 
{
   public static void main(String[] args) throws Exception
{
    NameChange nameChange=new NameChange();
	Class<?> classType=nameChange.getClass();
	Field field=classType.getDeclaredField("name");
	field.setAccessible(true);
	System.out.println(field.get(nameChange));
	field.set(nameChange,"xuekui");
	String name1=nameChange.getName();
	System.out.println(name1);
	
}
}
