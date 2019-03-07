package com.Thread.soulv.yield;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyCallabel implements Callable<String>{
	private Integer end=0;
	private String username;
	
	
	public MyCallabel(Integer end, String username) {
		super();
		this.end = end;
		this.username = username;
	}


	public Integer getEnd() {
		return end;
	}


	public void setEnd(Integer end) {
		this.end = end;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	@Override
	public String call() throws Exception {
		int ret = 0;
		for(int i=0;i<this.end;i++){
			ret+=i;
			System.out.println(username+" :"+i);
		}
		return ret+"";
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		MyCallabel mc = new MyCallabel(3,"薛奎");
		MyCallabel mc1 = new MyCallabel(4,"小雪");
		MyCallabel mc2 = new MyCallabel(5,"啊啊");
		ExecutorService es = Executors.newCachedThreadPool();
		Future<String> f = es.submit(mc);
		Future<String> f1 = es.submit(mc1);
		Future<String> f2 = es.submit(mc2);
		es.shutdown();
		System.out.println(f.get()+" , "+f1.get()+" , "+f2.get());
		
		
	}

}
