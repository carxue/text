package com.HashSet1;

import java.util.HashSet;
import java.util.Iterator;

public class Interator1
{
  public static void main(String[] args)
{
	HashSet set=new HashSet();
	set.add("a");
	set.add("b");
	set.add("c");
	set.add("d");
	String b=new String("e");
	String c=new String("e");
	set.add(b);
	set.add(c);
	
	Iterator in=set.iterator();
	while(in.hasNext())
	{
		String str=(String)in.next();
		System.out.println(str);
	}
	/*µÈ¼ÛÓÚ
	 * for(Iterator it=
	 */
}
}
