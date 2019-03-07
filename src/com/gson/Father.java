package com.gson;

public class Father {

	public void runfather() {
		print();
	}

	protected void print() {
		System.out.println("father");
	}
	public static void main(String[] args) {
		Father f= new Father();
		f.runfather();
	}
}
