package com.watched.observer;

import java.util.Observable;

public class NumObservable extends Observable {
	
	private int data = 0;

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
		setChanged();
		notifyObservers();
	}
	
}
