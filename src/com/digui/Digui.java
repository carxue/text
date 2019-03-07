package com.digui;

import java.io.File;

public class Digui
{
	public void go(File file)
	{
		if(file.isFile()||file.list().length==0)
		{
			file.delete();
		}
		else
		{
			File[] file1=file.listFiles();
			for(File file2:file1)
			{
				go(file2);
				file2.delete();
			}
		}
	}
	
	
	
	
	public static void main(String[] args) throws Exception
	{
		Digui digui=new Digui();
		digui.go(new File("xuekui.txt"));
	}
  
   
	
}
