package com.test;

public class JvmTest {
	double doubleTest(double d1, double d2) {
		return d1 + d2;
	}
	
	void sspin(){
		short i;
		for(i=0;i<100;i++)
			;//loop body is empty
	}
	
	public static int align2grain(int i,int grain){
		int a = (i+grain-1),b=~(grain-1);
		System.out.println(a+" : "+b);
		System.out.println(a&b);
		return ((i+grain-1)&~(grain-1));
	}
	
	public static int upAdd(int n){
		if(n>1)
			return n+upAdd(n-1);
		else
			return 1;
	}
	
	public static int upMul(int n){
		if(n>1)
			return n*upMul(n-1);
		else
			return 1;
	}
	
	
	public static void main(String[] args) {
		System.out.println(align2grain(2,3));
		
		System.out.println("a 非的结果是："+(~9)+" : "+(8<<3));
		
		System.out.println("=========11=="+upAdd(11));
		System.out.println("==========="+upMul(5));
	}
}
