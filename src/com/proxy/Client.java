package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client {

	static public void main(String[] args) throws Throwable {

		RealSubject rs = new RealSubject(); 
		InvocationHandler ds = new DynamicSubject(rs);
		Class cls = rs.getClass();

        //cls.getInterfaces() 查找到该类的所有接口
		Subject subject = (Subject) Proxy.newProxyInstance(
				cls.getClassLoader(), cls.getInterfaces(), ds);
		subject.request();
		subject.response();
		
		System.out.println("--------------------");
		for(int i=0;i<cls.getInterfaces().length;i++)
			System.out.println(cls.getInterfaces()[i]);
	     }
	
}
