package com.Thread.soulv.interrupt;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SleepBlocked implements Runnable {
	private String name;

	public SleepBlocked(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		try {
			System.out.println(name + ":start " + Thread.interrupted());
			TimeUnit.SECONDS.sleep(1);
			System.out.println(name + ":-----");
		} catch (InterruptedException e) {
			System.out.println("InterruptedException");
		}
		System.out.println(name + ":end " + Thread.interrupted());
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		/*
		 * SleepBlocked s1 = new SleepBlocked("s1"); SleepBlocked s2 = new
		 * SleepBlocked("s2"); Thread t1 = new Thread(s1); Thread t2 = new
		 * Thread(s2); t1.start();t1.interrupt(); t2.start();
		 */

		/*
		 * IoBlock ib = new IoBlock(System.in); ExecutorService es =
		 * Executors.newCachedThreadPool(); Future f = es.submit(ib);
		 * TimeUnit.SECONDS.sleep(3); System.out.println("system exist");
		 * f.cancel(true);
		 * System.out.println("interrupt end to:"+ib.getClass().getName());
		 * es.shutdown();
		 */

		/*
		 * SynBlock sb = new SynBlock(); ExecutorService es =
		 * Executors.newCachedThreadPool(); es.submit(sb); es.shutdown();
		 */

		/*
		 * ShutDowThread shut1 = new ShutDowThread("xuekui",5l); ShutDowThread
		 * shut2 = new ShutDowThread("little snow",10l); ShutDowThread shut3 =
		 * new ShutDowThread("shut3",10l); ExecutorService es =
		 * Executors.newCachedThreadPool(); es.submit(shut1);es.submit(shut2);
		 * TimeUnit.SECONDS.sleep(7l); List<Runnable> runList =
		 * es.shutdownNow(); for(Runnable rSun:runList)
		 * System.out.println(":::::"+run.toString());
		 */
		
		ExecutorService es = Executors.newCachedThreadPool();
		ServerSocket ss = new ServerSocket(8080);
		InputStream is =new Socket("localhost",8080).getInputStream();
		es.execute(new IoBlock(System.in));
		es.execute(new IoBlock(is));
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("will shutdown all thread!");
		es.shutdownNow();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("closed all thread!"+is.getClass().getName());
		is.close();
		System.out.println("closeing:"+System.in.getClass().getName());
		System.in.close();

	}
}

class IoBlock implements Runnable {
	private InputStream is;

	public IoBlock(InputStream is) {
		this.is = is;
	}

	@Override
	public void run() {
		try {
			byte[] b = new byte[1024];
			is.read(b, 0, 1024);
			System.out.println(new String(b, 0, b.length));
		} catch (IOException e) {
			if (Thread.currentThread().isInterrupted()) {
				System.out.println("Interrupted");
			} else {
				throw new RuntimeException(e);
			}
		}
		System.out.println("exist IoBlock run");
	}
}

class SynBlock implements Runnable {

	private synchronized void f() {
		while (true) {
			System.out.println("f come");
			Thread.yield();
			System.out.println("f go");
		}
	}

	public SynBlock() {
		new Thread() {
			@Override
			public void run() {
				f();
			}
		}.start();
	}

	@Override
	public void run() {
		System.out.println("try to call SynBlock's f()!");
		f();
		System.out.println("exist SynBlock's f()!");
	}
}

class ShutDowThread extends Thread {

	private String threadName;
	private Long sleepMiu;

	public Long getSleepMiu() {
		return sleepMiu;
	}

	public void setSleepMiu(Long sleepMiu) {
		this.sleepMiu = sleepMiu;
	}

	public ShutDowThread(String threadName, Long sleepMiu) {
		this.threadName = threadName;
		this.sleepMiu = sleepMiu;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	@Override
	public void run() {
		try {
			System.out.println("threadName 【" + threadName + "】 begin sleep :"
					+ sleepMiu + "m");
			TimeUnit.SECONDS.sleep(sleepMiu);
			System.out.println("threadName 【" + threadName
					+ "】 is wake up && end :" + sleepMiu + "m sleep");
		} catch (InterruptedException e) {
			System.out.println(threadName + ":isInterrupted:" + e.getMessage());
		}
	}
}
