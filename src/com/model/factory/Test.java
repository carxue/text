package com.model.factory;

public class Test {
	public static void main(String[] args) {
		HumenFactory hf = new HumenFactory();
		Chi chi = hf.createHuman(Chi.class);
		chi.say();
		Jap jap = HumenFactory.createStaHuman("Jap", Jap.class);
		jap.say();
		Jap jap1 = HumenFactory.createStaHuman("Jap", Jap.class);
		jap1.say();
		Chi chi1 = HumenFactory.createStaHuman("Chi", Chi.class);
		chi1.say();
	}
}
