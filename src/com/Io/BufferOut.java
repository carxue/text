package com.Io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class BufferOut {
	public static void main(String[] args) throws Exception {
		OutputStream os = new FileOutputStream("1.text");
		BufferedOutputStream bos = new BufferedOutputStream(os);
		bos.write("123123dsfsdf佛挡杀佛".getBytes());
		bos.close();
	}
}
