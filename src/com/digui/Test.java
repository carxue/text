package com.digui;

import java.math.BigDecimal;
import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		int[] a = { 1, 23, 2, 6, 8, 3, 39, 100, 7, 19, 30 };
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = 0; j < a.length - i - 1; j++) {
				int temp = a[j + 1];
				if (a[j] > a[j + 1]) {
					a[j + 1] = a[j];
					a[j] = temp;
				}
			}
		}
		for (int m = 0; m < a.length; m++)
			System.out.print(a[m] + "  ");

		System.out.println("----------------------------");

		int[] b = { 1, 2, 3, 6, 7, 8, 19, 23, 30, 39, 100,122};
		System.out.println(Test.binarysearch(b,1222));
		
		System.out.println("--------------------------------------");
		Arrays.sort(a);
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+"  ");
		}

		System.out.println("1111 "+new BigDecimal(1).divide(new BigDecimal(100)).doubleValue());
		
	}

	public static int binarysearch(int[] a, int b) {
		int start = 0;
		int end = a.length - 1;
		int mid;
		while (end >= start) {
			mid = (start + end) / 2;
			if (a[mid] == b)
				return mid;
			if (a[mid] > b) {
				end = mid-1;
				mid = (start + end) / 2;
			}
			if (a[mid] < b) {
				start = mid+1;
				mid = (start + end) / 2;
			}
		}
		return -1;

	}

}
