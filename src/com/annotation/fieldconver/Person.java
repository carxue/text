package com.annotation.fieldconver;

import java.util.Date;

public class Person extends SupHuman{
	@FieldConvert(filed="name")
	private String userName;
	@FieldConvert(filed="psword")
	private String passWord;
	@FieldConvert(filed="age")
	private Integer userAge;
	@FieldConvert(filed="time")
	private Date createItme;
	private Boolean sex;
	private Double salary;

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Integer getUserAge() {
		return userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	public Date getCreateItme() {
		return createItme;
	}

	public void setCreateItme(Date createItme) {
		this.createItme = createItme;
	}
}
