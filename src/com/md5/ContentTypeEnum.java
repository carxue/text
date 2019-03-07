package com.md5;

/**
 * content type 枚举类
 *
 * @author zengzw
 * @date 2015年1月27日
 */
public enum ContentTypeEnum {
	
	Formurlencoded("application/x-www-form-urlencoded"),
	
	Json("application/json");

	private final String value;
	private ContentTypeEnum(String value){
		this.value = value;
	}
	public String value(){
		return this.value;
	}
	

}
