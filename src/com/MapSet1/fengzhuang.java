package com.MapSet1;

import java.util.HashMap;
import java.util.Map;
public class fengzhuang
{
	public static void main(String[] args)
	{
		  Map<String,Integer> map=new HashMap<String,Integer>();
          for(String a:args)
          {
        	  map.put(a,(null==map.get(a))?1:map.get(a)+1);
          }
          System.out.println(map);
	}
 
}
