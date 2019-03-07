package com.exception;

public class ExceptionTest {
	public static void main(String[] args) {
		try {
			XException.test();
		} catch (UserException e) {
			System.out.println(e.getMsg());
		} catch (XException e) {
			System.out.println(e.getMsg());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
