package com.MapSet1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Map
{
 public static void main(String[] args)
{
	HashMap set=new HashMap();
	set.put("a","xue");
	set.put("b","kui");
	set.put("c","hello");
//	System.out.println(set);
    String value=(String)set.get("b");
    System.out.println(value);
    Set set1=set.keySet();
    int count=0;
    for(Iterator in=set1.iterator();in.hasNext();)
    { 
    	in.next();
    	String key=(String)in.next();
    	String value1=(String)set.get(key);
    	System.out.println(key+"="+value1);
    }
    
}
}
