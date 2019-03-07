package com.digui;
import java.util.Arrays;

public class Binary
{
	public void search(int[] a,int b)
	{
//		Arrays.sort(a);
//		for(int i=0;i<a.length;i++)
//			System.out.print("   "+a[i]);
		int low=0;
		int high=a.length-1;
		int middle;
		while(low<=high)
		{
			middle=(low+high)/2;
			if(a[middle]==b)
			{
				System.out.println("\n"+middle);
				return;
			}
			if(a[middle]>b)
			{
				high=middle-1;
				middle=(low+high)/2;
				
			}
			if(a[middle]<b)
			{
				low=middle+1;
				middle=(low+high)/2;
			}
		}
		System.out.println("\n");
	}

	public static void main(String[] args)
	{
		Binary binary = new Binary();
		int[] a = { 1, 2, 5, 4, 11, 7,7,11, 45, 22 };
		int[] c = { 1, 2, 3, 6, 7, 8, 19, 23, 30, 39, 100};
		int b=11;
		binary.search(c,b);
		/*
		 * int count=0;
		 *count++;
		 *System.out.println(count)
		*/
	}
}
