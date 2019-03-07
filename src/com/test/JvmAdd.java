package com.test;

public class JvmAdd {
	
	int age;
	private String name;
	public final static String flag ="1";
	
	public Object newObject(){
		return new Object();
	}
	
	int add(int d1, int d2) {
		return d1 + d2;
	}
	
	int referenceAdd(){
		return add(12,13);
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
