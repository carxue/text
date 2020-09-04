package com.annotation.jdk8;

public interface Ihuman {

	public void listen();

	public static void see(String what) {
		System.out.println("Human see:" + what);
	}

	public default void say(String msg) {
		System.out.println("Human say:" + msg);
	}
}
