package com.Io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Serializable1
{

	public static void main(String[] args) throws Exception
	{
		PersonText person1 = new PersonText(1, "xuekui", 1.83);
		PersonText person2 = new PersonText(1, "lilianzhi", 1.73);
		PersonText person3 = new PersonText(1, "xuetiqi", 1.83);
		//输出到本机文件夹中
		FileOutputStream fos = new FileOutputStream("serializable.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		oos.writeObject(person1);
		oos.writeObject(person2);
		oos.writeObject(person3);

		oos.close();
		// 反序列化
		FileInputStream fis = new FileInputStream("Serializable.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		PersonText person4 = null;
		for (int i = 0; i < 3; i++)
		{
			person4 = (PersonText) ois.readObject();
			System.out.println(person4.id + "," + person4.name + ","
					+ person4.heigh);
		}
		ois.close();

	}

}

class PersonText implements Serializable
{
	int id;
	String name;
	double heigh;

	public PersonText(int id, String name, double heigh)
	{
		this.id = id;
		this.name = name;
		this.heigh = heigh;
	}
}
