package com.Io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RamdomAcceseFil
{
	public static void main(String[] args) throws Exception
	{
		RandomAccessFile raf = new RandomAccessFile("2.txt", "rw");

		Person person = new Person(1, "xuekui", 1.83);
		person.write(raf);
		raf.seek(0);
		Person person2 = new Person();
		person2.read(raf);
		System.out.println(person2.getId());
		System.out.println(person2.getName());
		System.out.println(person2.getHiegh());

	}
}

class Person
{
	int id;
	String name;
	double hiegh;

	public Person()
	{

	}

	public Person(int id, String name, double hiegh)
	{
		this.id = id;
		this.name = name;
		this.hiegh = hiegh;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public double getHiegh()
	{
		return hiegh;
	}

	public void setHiegh(double hiegh)
	{
		this.hiegh = hiegh;
	}

	public void write(RandomAccessFile raf) throws IOException
	{
		raf.writeInt(this.id);
		raf.writeUTF(this.name);
		raf.writeDouble(this.hiegh);
	}

	public void read(RandomAccessFile raf) throws IOException
	{
		this.id = raf.readInt();
		this.name = raf.readUTF();
		this.hiegh = raf.readDouble();
	}
}
