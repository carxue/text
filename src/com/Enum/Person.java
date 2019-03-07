package com.Enum;

public enum Person {

	Xuekui("薛奎", "123qwe", 22, 12.9), Prettysnow("snow", "234", 22, 12.9), JackChen(
			"成龙", "768", 22, 12.9);

	private String name;
	private String passwrod;
	private Integer age;
	private Double salary;

	Person(String name, String passwrod, Integer age, Double salary) {
		this.name = name;
		this.passwrod = passwrod;
		this.age = age;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswrod() {
		return passwrod;
	}

	public void setPasswrod(String passwrod) {
		this.passwrod = passwrod;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public static void main(String[] args) {
		for(Person person : Person.values())
			System.out.println(person.passwrod);
	}
}
