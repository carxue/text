package com.list;
import java.util.ArrayList;
public class HelloXue
{
   public static void main(String[] args)
{
	ArrayList arrayList=new ArrayList();
	arrayList.add("xue");
	arrayList.add("kui");
	arrayList.add("!!!");
	String s=(String)arrayList.get(0);
	String s1=(String)arrayList.get(1);
	String s2=(String)arrayList.get(2);
	System.out.println(s);
	System.out.println(s1);
	System.out.println(s2);
	System.out.println("-----------");
	for(int i=0;i<arrayList.size();i++)
	{
		System.out.println(arrayList.get(i));
	}
	System.out.println("-----------");
	/*arrayList.clear();
	System.out.println(arrayList.size());*/
	arrayList.remove(0);
	System.out.println(arrayList.get(0));
}  
}
