package com.Thread.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ExceptionThread implements Runnable {
	@Override
	public void run() {
		System.out.println("==================="+Thread.currentThread().getUncaughtExceptionHandler().toString());
		throw new RuntimeException("运行时异常!");
	}
	public static void main(String[] args) {
		try{
		ExecutorService executor = Executors.newCachedThreadPool(new HandlerThreadfactory());
		executor.execute(new ExceptionThread());
		}catch(RuntimeException e){
			System.out.println("异常");
		}
	}
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("caught:"+e);
	}
}

class HandlerThreadfactory implements ThreadFactory{

	@Override
	public Thread newThread(Runnable r) {
		System.out.println(this+"  creating new Thread");
		Thread t = new Thread();
		System.out.println("created:"+t);
		t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		System.out.println("eh: "+t.getUncaughtExceptionHandler());
		return t;
	}
	
}
