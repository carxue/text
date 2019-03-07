package com.proxy.newtest;

public class MyClassLoader extends ClassLoader {
	@SuppressWarnings("deprecation")
	public Class<?> defineMyClass(byte[] b, int off, int len) {
		return super.defineClass(b, off, len);
	}
}
