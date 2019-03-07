package com.book.refactoring;

import junit.framework.TestCase;

public class ChapterOne extends TestCase{
	public static void main(String[] args) {
		
		
	}
	
	
	public static Object getClassLoader(){
		return Thread.currentThread().getContextClassLoader();
	}
	
	
}
