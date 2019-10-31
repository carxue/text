package com.annotation.sign;

import java.util.Date;

public class User1 extends SupUser1{
	@SignAttr
	private String name;
	@SignAttr
	private String psword;
	private Integer age;
	@SignAttr
	private Date time;

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

}
