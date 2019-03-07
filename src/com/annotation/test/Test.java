package com.annotation.test;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang.StringUtils;

public class Test {
  public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
	  System.out.println(new BaseMapping().callMethod("select_user", "薛奎"));
	  String qOpenid = "123";
	  String openid = "456";
	  if(StringUtils.isBlank(qOpenid)||(StringUtils.isNotBlank(qOpenid)&&(qOpenid.startsWith("app_")||!openid.equals(qOpenid)))){
		  System.out.println("----");
	  }else{
		  System.out.println("-ssss--");
	  }
	  
	  
	  
	  
  }
}
