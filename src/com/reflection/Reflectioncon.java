package com.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reflectioncon
{
   public Object copyof(Object object) throws Exception
   {
	   Class<?> classType=object.getClass();
	  Object objectCopy=classType.getConstructor(new Class[]{}).newInstance(new Object[]{});
	  Field[] filed=classType.getDeclaredFields();
	  for(Field file:filed)
	  {
		  String name=file.getName(); 
		  String firstLetter=name.substring(0,1).toUpperCase();
		  String getMethodName="get"+firstLetter+name.substring(1);
		  String setMethodName="set"+firstLetter+name.substring(1);
//		  System.out.println(getMethodName+","+setMethodName);
		  Method getMethod=classType.getMethod(getMethodName,new Class[]{});
//		  Method setMethod=classType.getMethod(setMethodName,new Class[]{file.getType()});
		  Object value=getMethod.invoke(object,new Class[]{});
		  System.out.println("--------------: "+value);
//		  setMethod.invoke(objectCopy,new Object[]{value});
		  
		  
	  }
	  
	  return objectCopy;
	  
   }
   public static void main(String[] args) throws Exception
{
	Customer customer=new Customer("Tom",23);
	customer.setId(1L);
	Reflectioncon text=new Reflectioncon();
	Customer customer2=(Customer)text.copyof(customer);
//	System.out.println(customer2.getId()+","+customer2.getAge()+","+customer2.getName());
	
}
   
}
class Customer
{
   private Long id;
   private String name;
   private int age;
   public Customer()
   {
	   
   }
   public Customer(String name,int age)
   {
	   this.name=name;
	   this.age=age;
   }
public Long getId()
{
	return id;
}
public void setId(Long id)
{
	this.id = id;
}
public String getName()
{
	return name;
}
public void setName(String name)
{
	this.name = name;
}
public int getAge()
{
	return age;
}
public void setAge(int age)
{
	this.age = age;
}
   
}