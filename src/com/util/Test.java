package com.util;

import java.text.ParseException;
import java.util.Random;

public class Test {
	public static void main(String[] args) throws ParseException {
		System.out.println(System.currentTimeMillis());
		
	}
	
	public static String getOracleInConditionOther(String[] arrInValue) {
        if (arrInValue == null)
            return null;
        StringBuilder sResult = new StringBuilder();
        if(arrInValue.length==1)
        	return "'"+arrInValue[0].replaceAll(",", "','")+"'";
        else{
        	for (int i = 0; i < arrInValue.length; i++) {
                sResult.append("'").append(arrInValue[i]).append("',");
            }
        	String ret = sResult.toString();
        	return ret.substring(0,ret.length()-1);
        }
    }
	
	private static String getFixLenthString(int strLength) {  
	      
	    Random rm = new Random();  
	      
	    // 获得随机数  
	    double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);  
	  
	    // 将获得的获得随机数转化为字符串  
	    String fixLenthString = String.valueOf(pross);  
	  
	    // 返回固定的长度的随机数  
	    return fixLenthString.substring(1, strLength + 1);  
	}  
}
class SortA implements Comparable{

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}