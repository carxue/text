package com.test;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.md5.Md5;

public class TestA {

	@Test
	public void test() throws UnsupportedEncodingException {
		Md5 md = new Md5();
		String[] test = {"111111"};
		for(int i=0;i<test.length;i++){
			String sign = md.getMd5(test[i]);
			System.out.println(sign);
		}
		
	}

}
