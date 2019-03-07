package com.list;
import java.util.*;
public class arraylist
{
public static void main(String[] args)
{
	ArrayList list=new ArrayList();
	list.add(new Integer(54));
	list.add(new Integer(2));
	Object[] in=list.toArray();
	for(int i=0;i<in.length;i++)
	{
		System.out.println(((Integer)in[i]).intValue());
	}
}
}
