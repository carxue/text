package com.HashSet1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Shiyan
{
  public static void main(String[] args)
{
		HashMap map1 = new HashMap();
		map1.put("test", "testValue");
		map1.put("test1", "testValue1");
		map1.put("test2", "testValue2");
		
		HashMap map2 = new HashMap();
		map2.put("test", "testValue");
		map2.put("test1", "testValue1");
		map2.put("test2", "testValue2");
		
		System.out.println(map1.hashCode());
		System.out.println(map2.hashCode());

        List list=new ArrayList();
        list.add("xuekui");
        list.add("I love you!");
        Iterator it=list.iterator();
        while(it.hasNext())
        {
        	System.out.println((String)it.next());
        }
}
}
