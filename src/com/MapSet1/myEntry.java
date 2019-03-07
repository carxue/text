package com.MapSet1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class myEntry
{
  public static void main(String[] args)
{
	HashMap map=new HashMap();
	map.put("a","aa");
	map.put("b","bb");
	Set set=map.entrySet();
	for(Iterator it=set.iterator();it.hasNext();)
	{
		Map.Entry entry=(Map.Entry)it.next();
		String key=(String)entry.getKey();
		String value=(String)entry.getValue();
		System.out.println(key+":"+value);
		
	}
}
}
