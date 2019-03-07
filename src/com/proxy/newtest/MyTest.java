package com.proxy.newtest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;


public class MyTest {
	public static void main(String[] args) throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException {
//		File file =new File(".");
//		InputStream inputStream = new FileInputStream(file.getCanonicalFile()+"\\bin\\com\\proxy\\newtest\\Programmer.class");
		File file =new File("E://Programmer.class");
		InputStream inputStream = new FileInputStream(file);
		
		System.out.println(file.getCanonicalFile());
		byte[] result = new byte[1024];
		
		int count = inputStream.read(result);
		MyClassLoader loader = new MyClassLoader();
		Class<?> clazz = loader.defineMyClass(result, 0, count);
		System.out.println(clazz.getCanonicalName());
		
		Object o = clazz.newInstance();
		try {
			clazz.getMethod("code", null).invoke(o, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
