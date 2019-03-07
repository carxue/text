package com.Io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableClone
{
	public static void main(String[] args) throws Exception, ClassNotFoundException
	{
		TeacherTest t1=new TeacherTest();
		t1.setAge(40);
		t1.setName("Teacher Xue");
		
        StudentTest s1=new StudentTest();
        s1.setAge(20);
        s1.setName("xuekui");
        
        StudentTest s2=new StudentTest();
        s2=(StudentTest)s1.deepCopy();
        
        System.out.println(s2.getAge());
        System.out.println(s2.getName());
        
        System.out.println(s2.getTeacher().getAge());
        System.out.println(s2.getTeacher().getName());
        
	}
}

class TeacherTest implements Serializable
{
	private int age;
	private String name;

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

}

class StudentTest implements Serializable

{
	private int age;
	private String name;
	private TeacherTest teacher;

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public TeacherTest getTeacher()
	{
		return teacher;
	}

	public void setTeacher(TeacherTest teacher)
	{
		this.teacher = teacher;
	}

	public Object deepCopy() throws Exception, ClassNotFoundException
	{
       ByteArrayOutputStream bos=new ByteArrayOutputStream();
       ObjectOutputStream oos=new ObjectOutputStream(bos);
       oos.writeObject(this);
       ByteArrayInputStream bis=new ByteArrayInputStream(bos.toByteArray());
       ObjectInputStream ois=new ObjectInputStream(bis);
       return  ois.readObject();
	}
}
