package com.Generic1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Hash1 {
	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();
		set.add("xue");
		set.add("kui");
		for (Iterator<String> it = set.iterator(); it.hasNext();) {
			String value = it.next();
			System.out.println(value);
		}
		System.out.println("--------------------------------------");

		Set<Person> set1 = new HashSet<Person>();
		Person p1 = new Person("xuekui", 22, "Hongkong");
		Person p2 = new Person("xuekui", 22, "Hongkong");
		set1.add(p1);
		set1.add(p2);
		System.out.println(p1.equals(p2));
		/*for (Iterator<Person> it = set1.iterator(); it.hasNext();) {
			Person person = it.next();
			String name = person.getName();
			int age = person.getAge();
			String address = person.getAddress();
			System.out.println(name+"|"+age+"|"+address);
		}*/
	}
}

class Person {
	private String name;
	private int age;
	private String address;

	public Person(String name, int age, String address) {
		this.name = name;
		this.age = age;
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}