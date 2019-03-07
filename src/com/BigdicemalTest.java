package com;

import java.math.BigDecimal;

public class BigdicemalTest {
	public static void main(String[] args) {
		
		System.out.println(mul(mul(500,0.001d).toString(),1));
		System.out.println((3.3d-2.4d)/0.1d);
		System.out.println(div(1.140d, 1000.0d,4).doubleValue());
		
	}
 public  BigDecimal add(Object o1,Object o2){
	 BigDecimal b1 = new BigDecimal(o1.toString());
	 BigDecimal b2 = new BigDecimal(o2.toString());
	 return b1.add(b2);
 }
 public static BigDecimal sub(Object o1,Object o2){
	 BigDecimal b1 = new BigDecimal(o1.toString());
	 BigDecimal b2 = new BigDecimal(o2.toString());
	 return b1.subtract(b2);
 }
 public static BigDecimal mul(Object o1,Object o2){
	 BigDecimal b1 = new BigDecimal(o1.toString());
	 BigDecimal b2 = new BigDecimal(o2.toString());
	 return b1.multiply(b2);
 }
 public static BigDecimal div(Object o1,Object o2,int scale){
	 BigDecimal b1 = new BigDecimal(o1.toString());
	 BigDecimal b2 = new BigDecimal(o2.toString());
	 return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP);
 }
}
