package com.Thread.soulv.callabel;

import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Priority implements Runnable{
	private String username;
	private int countDown = 5;
	private volatile double d;
	private int priority;
	
	public Priority(String username,int priority) {
		this.priority = priority;
		this.username = username;
	}
	
	public String toString(){
		return "["+username+"]: "+this.countDown;
	}
	
	@Override
	public void run() {
		Thread.currentThread().setPriority(priority);
		while(true){
			for(int i = 1;i<100000;i++){
				d +=(Math.PI+Math.E)/(double)i;
				if(i%1000 ==0)
					Thread.yield();
			}
			System.out.println(this);
			if(--countDown ==0) return;
		}
	}

	public static void main(String[] args) throws UnknownHostException {
		ExecutorService es = Executors.newCachedThreadPool();
		for(int i =0;i<5;i++)
			es.execute(new Priority(i+"",Thread.MIN_PRIORITY));
		es.execute(new Priority(5+"",Thread.MAX_PRIORITY));
		es.shutdown();
		
	}
}
