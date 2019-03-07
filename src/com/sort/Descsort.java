package com.sort;

public class Descsort
{
	public static void main(String[] args)
	{
       String[] numbers={"2","3","5","7","3.3","3.33","45","22","33","22"};
       Descsort sort=new Descsort();
       sort.desc_sort(numbers);
       for(int i=0;i<numbers.length;i++)
    	   System.out.print(numbers[i]+"   ");
       System.out.println("\n-----------------------------------------------------------");
       
       sort.tree();
       
	}
	
	//倒序冒泡排列
	public void desc_sort(String[] number){
		for(int i=0;i<number.length-1;i++){
			for(int j=0;j<number.length-i-1;j++){
				double a=Double.parseDouble(number[j]);
				double b=Double.parseDouble(number[j+1]);
				if(a<b){
					String temp=number[j+1];
					number[j+1]=number[j];
					number[j]=temp;
				}
			}
		}
		System.out.println("最大值为："+number[0]);
	}
	
	//树形结构输出
	public void tree(){
		for(int i=0;i<10;i++){
			int first=(2<<i)/2;
			for(int j=0;j<first;j++){
				System.out.print(first+j+"  ");
			}
			System.out.println("\n");
		}
	}
}
