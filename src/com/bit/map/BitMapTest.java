package com.bit.map;

public class BitMapTest {
	public static void main(String[] args) {
	    //创建一个具有10000000位的bitset　初始所有位的值为false  
        java.util.BitSet bitSet = new java.util.BitSet(10000000);
        
        //将指定位的值设为true  
        bitSet.set(9999, true);  
        //输出指定位的值  
        System.out.println(bitSet.get(9999));  
        System.out.println(bitSet.get(9998));  
        System.out.println((1<<10) >> 6);
	}
}
