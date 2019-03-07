package com.digui;

import java.io.File;
import java.util.ArrayList;

public class FileDiGui
{
	public void readout(File file)
	{
		if (file.isFile() || file.listFiles().length == -1)
		{
			return;
		}
		else
		{
			int count = -1;
			File[] files = file.listFiles();
			for (File s : files)
			{
				count++;
				if (!s.isFile())
				{
					for (int i = 0; i < count; i++)
					{
						System.out.print("        ");
					}
					System.out.println(s.getName());
					readout(s);
				}
				else
				{
					for (int i = 0; i < count; i++)
					{
						System.out.print("        ");
					}
					System.out.println(s.getName());
				}
				
			}
			
		}
	}
	private static File[] sorted(File[] files)
	{
		ArrayList<File> list=new ArrayList<File>();
		for(File file:files)
		{
			if(file.isDirectory())
			{
				list.add(file);
			}
		}
		for(File file:files)
		{
			if(file.isFile())
			{
				list.add(file);
			}
		}
		return list.toArray(new File[files.length]);
	}

	public static void main(String[] args)
	{
		File file = new File("d:/");
		FileDiGui fileDiGui = new FileDiGui();
		fileDiGui.readout(file);
	}

}
