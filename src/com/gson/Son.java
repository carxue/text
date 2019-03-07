package com.gson;

public class Son extends Father {
	
	public void runson(){
		super.runfather();
	}
	
	@Override
	protected void print() {
		System.out.println("son");
	}
	public static void main(String[] args) {
		Son s = new Son();
		s.runson();
	}
}
