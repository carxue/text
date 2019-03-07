package com.model.factory;

public abstract class Humen {
	private String color;
	private String langurage;
	
	public abstract void say();

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getLangurage() {
		return langurage;
	}

	public void setLangurage(String langurage) {
		this.langurage = langurage;
	}

}
