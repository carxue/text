package com.annotation.fieldconver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User<E> extends SupUser{
	private String name;
	private String psword;
	private Integer age;
	private Date time;
	private List<E> elist = new ArrayList<E>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPsword() {
		return psword;
	}

	public void setPsword(String psword) {
		this.psword = psword;
	}

	public List<E> getElist() {
		return elist;
	}

	public void setElist(List<E> elist) {
		this.elist = elist;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", psword='" + psword + '\'' +
				", age=" + age +
				", time=" + time +
				", elist=" + elist +
				'}';
	}
}
