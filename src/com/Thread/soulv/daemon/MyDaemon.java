package com.Thread.soulv.daemon;

import java.util.concurrent.TimeUnit;

public class MyDaemon implements Runnable{
	@Override
	public void run() {
		try {
			while(true){
				TimeUnit.MILLISECONDS.sleep(1000);
				System.out.println(Thread.currentThread()+" "+this);
			}
		} catch (InterruptedException e) {
			System.out.println("sleep interrupted");
		}
	}
	public static void main(String[] args) throws InterruptedException {
		for(int i = 0;i<10;i++){
			Thread daemon = new Thread(new MyDaemon());
			daemon.setDaemon(true);
			daemon.start();
		}
		System.out.println("all daemon started");
		TimeUnit.MILLISECONDS.sleep(175);
	}

}
