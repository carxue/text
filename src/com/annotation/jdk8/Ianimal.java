package com.annotation.jdk8;

public interface Ianimal {

	public void listen();

	public static void see(String what) {
		System.out.println("Animal see:" + what);
	}

	public default void say(String msg) {
		System.out.println("Animal howl:" + msg);
	}
}
