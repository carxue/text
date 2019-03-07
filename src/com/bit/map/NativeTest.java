package com.bit.map;

public class NativeTest {
	public native int plusTest(int a, int b);

	static {
		System.loadLibrary("NativeTestImpl");
	}

	public static void main(String[] args) {
		NativeTest nativeTest = new NativeTest();
		int a = 1, b = 2;
		int sum = nativeTest.plusTest(a, b);
		System.out.println(a + "加" + b + "的和等于" + sum);
	}
}
