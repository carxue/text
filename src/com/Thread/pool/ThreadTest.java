package com.Thread.pool;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadTest {
	
	private String result_a;

	private String result_b;

	private String result_c;

	public String invokeA() {
		int result = 0;
		for (int i = 0; i < 80000000; i++) {
			result += 2;
		}
		
		return result + "";
	}

	public String invokeB() {
		int result = 0;
		for (int i = 0; i < 10000000; i++) {
			result += 2;
		}
		return result + "";
	}

	public String invokeC() {
		int result = 0;
		for (int i = 0; i < 900000000; i++) {
			result += 2;
		}
		return result + "";
	}

	public static void main(String[] args) throws Exception {
		ThreadTest tt = new ThreadTest();
		ExecutorService threadPool = Executors.newCachedThreadPool();
		List<Callable<String>> callables = new ArrayList<Callable<String>>();
		

		callables.add(new Callable<String>() {
			ThreadTest tt = new ThreadTest();

			public String call() throws Exception {
				return tt.invokeB();
			}
		});
		
		
		callables.add(new Callable<String>() {
			ThreadTest tt = new ThreadTest();

			public String call() throws Exception {
				return tt.invokeA();
			}
		});

		callables.add(new Callable<String>() {
			ThreadTest tt = new ThreadTest();

			public String call() throws Exception {
				return tt.invokeC();
			}
		});
		
		Long t1 = new Date().getTime();
		List<Future<String>> futures = 
		threadPool.invokeAll(callables);
		for (Future<String> future : futures) {
			System.out.println(future.get());
		}
		Long t2 = new Date().getTime();
		System.out.println("--------------: " + (t2 - t1));
		threadPool.shutdown();
		
		tt.invokeA();
		tt.invokeB();
		tt.invokeC();
		Long t3 = new Date().getTime();
		System.out.println("--------------: " + (t3 - t2));
		
	}
}
