package com.test;

public class JvmThread {
	public void printLog(Object obj){
		synchronized(obj){
			doSomething(obj);
		}
	}
	
	public void doSomething(Object obj){
		System.out.println(obj.toString());
	}
}
