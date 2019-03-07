package com.reflection.test;

import java.util.Map;

public class Info extends Xue {
	private String address;
	private Integer lucynum;
	private Map<String,Object> map;

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getLucynum() {
		return lucynum;
	}

	public void setLucynum(Integer lucynum) {
		this.lucynum = lucynum;
	}

}
