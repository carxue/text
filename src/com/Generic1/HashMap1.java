package com.Generic1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;



public class HashMap1
{
	public static void main(String[] args)
	{
		 Map<String,String> map=new HashMap<String,String>();
         map.put("a","aa");
         map.put("b","bbb");
         map.put("c","cc");
         Set<Map.Entry<String,String>> set=map.entrySet();
         for(Iterator<Map.Entry<String,String>> it=set.iterator();it.hasNext();)
         {
        	 Map.Entry<String,String> entry=it.next();
        	 String key=entry.getKey();
        	 String value=entry.getValue();
        	 System.out.println(key+":"+value);
         }
        	 
         
         
   
	}
  
   
}
