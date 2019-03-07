package com.Generic1;

public class XueT<T, E, M, F> {
	private T t;
	private E e;
	private M m;
	private F f;

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public E getE() {
		return e;
	}

	public void setE(E e) {
		this.e = e;
	}

	public M getM() {
		return m;
	}

	public void setM(M m) {
		this.m = m;
	}

	public F getF() {
		return f;
	}

	public void setF(F f) {
		this.f = f;
	}
	
	
	@Override
	public String toString() {
		return "XueT [t=" + t + ", e=" + e + ", m=" + m + ", f=" + f + "]";
	}

	public static void main(String[] args) {
		XueT<String,Integer,Boolean,Double> xueT = new XueT<String,Integer,Boolean,Double>();
		xueT.setE(1);xueT.setF(1d);xueT.setM(true);xueT.setT("xuekui");
		
		System.out.println(xueT.toString());
	}

}
