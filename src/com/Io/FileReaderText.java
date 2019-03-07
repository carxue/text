package com.Io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;

public class FileReaderText 
{
	public static void main(String[] args) throws Exception
	{
		 FileReader fr=new FileReader("c:/xuekui.txt");
	   BufferedReader br=new BufferedReader(fr);
	   String str;
	   Thread thread=new Thread();
	 

	   while(null!=(str=br.readLine()))
	   {      Date date=new Date();	
		   String st=date.toLocaleString(); 
		   thread.sleep(100);
		   System.out.print(st);
		   System.out.println(str);
	   }
	}
	  
}
