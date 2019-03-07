package com.Generic1;

public class Generic<T> {
	private T foo;

	public T getFoo() {
		return foo;
	}

	public void setFoo(T foo) {
		this.foo = foo;
	}

	public static void main(String[] args) {
		Generic<String> fool = new Generic<String>();
		Generic<Boolean> fool1 = new Generic<Boolean>();
		fool.setFoo(new String("xuekui"));
		fool1.setFoo(new Boolean(true));
		System.out.println((String) fool.getFoo());
		System.out.println((Boolean) fool1.getFoo());
	}
}
