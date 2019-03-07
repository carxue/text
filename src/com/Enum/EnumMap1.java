package com.Enum;
import java.util.EnumMap;
import java.util.Map;

public class EnumMap1
{
   public static void main(String[] args)
{
	Map<Doop,String> map=new EnumMap<Doop,String>(Doop.class);
	map.put(Doop.TRUN_LEFT,"1");
	map.put(Doop.TRUN_RIGHT,"2");
	map.put(Doop.TRUN_SHOOT,"3");
	System.out.println(map);
	System.out.println("--------------------------");
	for(Doop d:Doop.values())
	{
		System.out.println(map.get(d));
	}
	System.out.println(Doop.TRUN_RIGHT);
}
}
enum Doop
{
	TRUN_RIGHT,TRUN_LEFT,TRUN_SHOOT
}
