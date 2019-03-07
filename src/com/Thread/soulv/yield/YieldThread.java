package com.Thread.soulv.yield;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class YieldThread extends Thread {
	private Integer start;
	
	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public YieldThread(Integer start) {
		super();
		this.start = start;
	}

	@Override
	public void run() {
		int i = 0;
		while(i<10){
			i++;
			if(i<10){
				System.out.println(" "+this.getName()+":"+i);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
			}if(i==start)
				YieldThread.yield();
		}
	}
	
	public static void main(String[] args) {//689
		YieldThread y = new YieldThread(5);
		YieldThread y1 = new YieldThread(8);
		YieldThread y2 = new YieldThread(3);
		ExecutorService es = Executors.newFixedThreadPool(3);
		es.execute(y);es.execute(y1);es.execute(y2);
		es.shutdown();
		/*ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(y);exec.execute(y1);exec.execute(y2);
		exec.shutdown();*/
		
	}
}

class Yield1Thread extends Thread {
	@Override
	public void run() {
		int k = 0;
		while(true){
			k++;
			if(k<10){
				System.out.print(" k:"+k);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
			}
			else
				break;
		}
	}
}