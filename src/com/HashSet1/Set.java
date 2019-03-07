package com.HashSet1;

import java.util.HashSet;

public class Set
{
 public static void main(String[] args)
{
	HashSet set=new HashSet();
	Student s=new Student("xuekui");
	Student s1=new Student("xuekui");
	set.add(s);
	set.add(s1);
	System.out.println(set);
	
}
}
class Student
{
	String name;
	public Student(String name)
	{
		this.name=name;
	}
	public int hashCode()
	{
		return this.name.hashCode();
	}
	public boolean equals(Object obj)
	{
		if(this==obj)
		{
			return true;
		}
		if(null!=obj&& obj instanceof Student)
		{
			Student stu=(Student)obj;
			if(this.name.equals(stu.name))
			{
			   return true;
			}
		}
		return false;
		
	}
}