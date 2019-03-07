package com.proxy.cglib;

import com.proxy.newtest.Programmer;

import net.sf.cglib.proxy.Enhancer;

public class Test {
	public static void main(String[] args) {
		Programmer programmer  = new Programmer();
		Hacker hacker = new Hacker();
		//cglig中加强器，用了创建动态代理
		Enhancer enhancer = new Enhancer();
		//设置要创建动态代理的类
		enhancer.setSuperclass(programmer.getClass());
		enhancer.setCallback(hacker);
		Programmer proxy = (Programmer) enhancer.create();
		proxy.code();
	}
}
