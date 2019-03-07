package com.TCPIP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class URLConnectionTest
{
 public static void main(String[] args) throws Exception
{
	URL url=new URL("http://www.qq.com");
	BufferedReader br=new BufferedReader(new InputStreamReader(
			url.openStream()));
	String str=null;
	while(null!=(str=br.readLine()))
	{
		System.out.println(str);
	}
	br.close();
}
}
