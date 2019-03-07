package com.Thread.local;

import java.util.Random;

public class ThreadLocalHolder {
	private static ThreadLocal<Integer> tl = new ThreadLocal<Integer>() {
		private Random rand = new Random(47);
		protected synchronized Integer initialValue() {
			return rand.nextInt(10000);
		}
	};
	public static void main(String[] args) {
		ThreadLocalHolder th = new ThreadLocalHolder();
		System.out.println(th.tl.get());
		Random rands = new Random(4);
		System.out.println(rands.nextInt(3));
		
		
	}
}
