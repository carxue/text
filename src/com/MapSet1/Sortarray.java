package com.MapSet1;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

public class Sortarray
{
  public static void main(String[] args)
{
	TreeMap map=new TreeMap();
	Random random=new Random();
	
	for(int i=0;i<50;i++)
	{
		Integer in=new Integer(random.nextInt(41)+10);
		if(map.get(in)==null)
		{
			map.put(in,new Integer(1));
		}
		else
		{
			map.put(in,new Integer((((Integer)map.get(in)).intValue()+1)));
		}
		Collection col=map.values();
	    Integer inn=(Integer)Collections.max(col);
	    LinkedList list=new LinkedList();
		Set set=map.keySet();
		for(Iterator it=set.iterator();it.hasNext();)
		{
			Integer key=(Integer)it.next();
			Integer value=(Integer)map.get(key);
			
			if(value.intValue()==inn.intValue())
			{
				list.add(key);
			}
			System.out.println(key+":"+value);
		}
		//未理解
	
		System.out.println("出现次数最多的是:"+inn);
		System.out.print("出现的数是:");
		for(Iterator in4=list.iterator();in4.hasNext();)
		{
			System.out.print(in4.next()+"  ");
		}
	
			
	}
}
}
