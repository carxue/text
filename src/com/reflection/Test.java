package com.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class Test {
	public static void test(List<Bill> list, List<String> visibles) throws Exception, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// 创建相应的列数
		for (int i = 0; i < list.size(); i++) {
			Class<?> clazz = list.get(i).getClass();
			Field field = null;
			for (int j = 0; j < visibles.size(); j++) {// 创建列数
				field = clazz.getDeclaredField(visibles.get(j));
				field.setAccessible(true);
				System.out.println(field.get(list.get(i)));
			}
		}
	}
	
	public static void test1(List<Bill> list, List<String> visibles) throws Exception{
		//创建相应的列数
		for(int i=0;i<list.size();i++){
			Class<?> clazz=list.get(i).getClass();
			Object object=clazz.getConstructor(new Class[]{}).newInstance(new Object[]{});
			for(int j=0;j<visibles.size();j++){//创建列数
				
				String firstLetter=visibles.get(j).substring(0,1).toUpperCase();
				String getMethodName="get"+firstLetter+visibles.get(j).substring(1);//get方法
				Method method=clazz.getMethod(getMethodName, new Class[]{});
				method.setAccessible(true);
				String value=String.valueOf(method.invoke(list.get(i), new Object[]{}));
				System.out.println(value);
			}
		}
	}
	
	public static <T> List<T> test2(List<T> list, List<String> visibles) throws Exception{
		List<T> list1=new ArrayList<T>();
		//创建相应的列数
		for(int i=0;i<list.size();i++){
			Class<?> clazz=list.get(i).getClass();
			T t=(T) clazz.newInstance();
			for(int j=0;j<visibles.size();j++){//创建列数
				
				String firstLetter=visibles.get(j).substring(0,1).toUpperCase();
				String setMethodName="set"+firstLetter+visibles.get(j).substring(1);//get方法
				Method method=clazz.getMethod(setMethodName, new Class[]{String.class});
				String value=String.valueOf(method.invoke(t, new Object[]{"xuekui: "+j}));
				System.out.println(value);
			}
			list1.add(t);
		}
		return list1;
	}

	public static void main(String[] args) throws SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, Exception {
		Bill bill = new Bill();
		bill.setBill_no("111");
		bill.setBill_name("薛奎");
		bill.setBill_class("111");

		Bill bill1 = new Bill();
		bill1.setBill_name("小雪");
		bill1.setBill_class("111");

		Bill bill2 = new Bill();
		bill2.setBill_no("111");
		bill2.setBill_name("哈哈");
		bill2.setBill_class("111");

		List<Bill> billlist = new ArrayList<Bill>();
//		billlist.add(bill2);
//		billlist.add(bill1);
		billlist.add(bill);

		List<String> visibles = new ArrayList<String>();
		visibles.add("father");
		visibles.add("bill_name");

		List<Bill> list=test2(billlist, visibles);
		for(int i=0;i<list.size();i++){
			Bill bills=list.get(i);
//			System.out.println(bills.getBill_name());
			}
		
	}
}
