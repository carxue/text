package com.annotation.jdk8;

public class Man implements Ianimal,Ihuman{

	@Override
	public void listen() {
		System.out.println("我听见一些事情");
		
	}

	@Override
	public void say(String msg) {
		Ianimal.super.say(msg);
	}
	
	
	public static void main(String[] args) {
		Man man = new Man();
		man.listen();
		man.say("人类通过动物来与动物交流");
	}

}
