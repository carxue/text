package com.Io;

import java.io.File;
import java.io.FilenameFilter;

public class CheckText
{
	public static void main(String[] args) 
	{
		File file = new File("C:/xuekui");
		String[] s=file.list();
//		String[] s = file.list(new FilenameFilter()
//		{
//           @Override
//        public boolean accept(File arg0, String arg1)
//        {
//        	if(arg1.endsWith(".text"))
//        	{
//        		return true;
//        	}
//        	return false;
//        }
//		});
		for(String str:s)
		{
			if(str.endsWith(".java"))
			{
				System.out.println();
			}
		
			
		}

	}

}
