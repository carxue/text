package com.Io;

import java.io.FileInputStream;
import java.io.InputStream;

public class OutPut 
{
	public static void main(String[] args) throws Exception 
	{
		InputStream is=new FileInputStream("C:/xuekui.txt");
		byte[] buffer=new byte[200];
		int length=0;
		while((length=is.read(buffer,0,200))!=-1)
		{
			String str=new String(buffer,0,length);
			System.out.println(str);
		}
		is.close();
	}
   
}
