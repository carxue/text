package com.annotation.fieldconver;

import com.annotation.fieldconver.FieldConvert.Ignore;

public class SupHuman {
	@FieldConvert(filed="myCountry",ignore=Ignore.N)
	private String country;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
