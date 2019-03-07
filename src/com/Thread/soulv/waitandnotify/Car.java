package com.Thread.soulv.waitandnotify;

import java.util.regex.Pattern;

public class Car {

	public static void main(String[] args) throws InterruptedException {
		String[] splists = Pattern.compile("[_]").split("name_xuekui");
	    String ret = "";
	    for(int i=0;i<splists.length;i++){
	    	String a = splists[i];
	    	if(i==0){
	    		ret=a;
	    	}else{
	    		ret += a.substring(0,1).toUpperCase()+a.substring(1,a.length());
	    	}
	    	System.out.println(ret);
	    }
	}
}
