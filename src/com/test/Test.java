package com.test;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class Test {

	private static final int HASH_INCREMENT = 0x61c88647;

	public static void main(String[] args) throws ParseException {
		Date date = new Date(1517817065098l);
		DateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		System.out.println(df.format(date));
		Long v = 0l;
		int a=0;
		if(a!=v){
			System.out.println("111");
		}
		
	}
	
	public static void autoInteger(){
		AtomicInteger nextHashCode = new AtomicInteger();
	    System.out.println(HASH_INCREMENT);
	    for(int i=0;i<10;i++)
	    	System.out.println(nextHashCode.getAndAdd(HASH_INCREMENT));
	    	
	}
	
	public static void patternUrl(){
		 // 要验证的字符串
	    String str = "/verifyCode/uuid/123456/image/aaaa";
	    // 正则表达式规则
	    String regEx = "/verifyCode/uuid/.*/image/.*";
	    // 编译正则表达式
	    Pattern pattern = Pattern.compile(regEx);
	    // 忽略大小写的写法
	    // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(str);
	    // 查找字符串中是否有匹配正则表达式的字符/字符串
	    boolean rs = matcher.find();
	    System.out.println(rs);
	}
	public static String scienceE(String value){
		BigDecimal bd = new BigDecimal(value); 
		return bd.toPlainString();
	}
	public static String converScience1(String value,int i){
		if(StringUtils.isBlank(value)){
			return "0";
		}
		DecimalFormat format=new DecimalFormat();
		format.applyPattern("##,###,###,###.00");
		return format.format(new BigDecimal(Double.parseDouble(value)).setScale(i,BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	public static String deletePoint(String number){
		String ret = "0";
		if(StringUtils.isBlank(number))
			return ret;
		if(number.indexOf(".")!=-1){
			return number.substring(0, number.indexOf("."));
		}else
			return number;
	}
	
	public static String a() throws Exception{
		throw new Exception();
	}
}
