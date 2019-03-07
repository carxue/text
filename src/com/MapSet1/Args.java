package com.MapSet1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Args
{
   public static void main(String[] args)
{
	for(int i=0;i<args.length;i++)
	{
		System.out.println(args[i]);
    }
	HashMap map=new HashMap();
	for(int j=0;j<args.length;j++)
	{
		if(map.get(args[j])==null)
		{
			map.put(args[j],new Integer(1));
		}
		else
		{
		    map.put(args[j],new Integer(((Integer)map.get(args[j])).intValue()));
		}
	}
	Set set=map.keySet();
	for(Iterator in=set.iterator();in.hasNext();)		
	{
		String key=(String)in.next();
		Integer value=(Integer)map.get(key);
		System.out.println(key+":"+value);
	}
}
}
