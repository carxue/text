package com.annotation.sign;

public class SupUser1 {
	private Boolean sex;
	@SignAttr
	private String myCountry;
	
	public String getMyCountry() {
		return myCountry;
	}

	public void setMyCountry(String myCountry) {
		this.myCountry = myCountry;
	}

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	
}
