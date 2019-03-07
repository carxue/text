package com.watched.observer;

import java.util.Observable;
import java.util.Observer;

public class NumObserver implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		NumObservable my = (NumObservable) o;
		System.out.println("Data has changed to "+my.getData());
	}

}
