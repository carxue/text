package com.TCPIP;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class Url
{
	public static void main(String[] args) throws Exception
	{
		URL url = new URL("http://www.2345.com");
		URLConnection conn = url.openConnection();
		InputStream is=conn.getInputStream();
		OutputStream os=new FileOutputStream("c:/xueTest.txt");
		byte[] buffer=new byte[2048];
		int length=0;
		while(-1!=(length=is.read(buffer,0,buffer.length)))
		{
			os.write(buffer,0,buffer.length);
		}
		is.close();
		os.close();
		
	}

}
