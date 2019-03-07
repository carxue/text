package com.Io;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ByteArrayOut
{
  public static void main(String[] args) throws IOException
{
	ByteArrayOutputStream bytearrayos=new ByteArrayOutputStream();
	String str="hello Ñ¦¿ü";
	byte[] buffer=str.getBytes();
	bytearrayos.write(buffer);
	byte[] result=bytearrayos.toByteArray();
	for(int i=0;i<str.length();i++)
	{
		System.out.println((char)result[i]);
	}
	OutputStream os=new FileOutputStream("xuekui.txt");
	bytearrayos.writeTo(os);
	
}
}
