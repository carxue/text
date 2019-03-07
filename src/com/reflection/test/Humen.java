package com.reflection.test;

import java.util.Date;

public class Humen {
	private String say;

	private String language;
	
	private Date date;
	
	
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSay() {
		return say;
	}

	public void setSay(String say) {
		this.say = say;
	}

	@Override
	public String toString() {
		return "Humen [say=" + say + "]";
	}
	
}
