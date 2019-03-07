package com.Strategy1;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class UpNameSort implements SortInterface, Comparator<Pepole>
{

	
	public void sort(List<Pepole> list)
	{
		Collections.sort(list,this);

	}
	public int compare(Pepole o1,Pepole o2)
	{
		int result=	o1.getName().compareTo(o2.getName());
		if(0==result)
		{
			result=o1.getId()-o2.getId();
		}
		return result;
	}

}
