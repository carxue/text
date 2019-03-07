package com.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ParentMethod<T> {
	public static <T> List<T> getFatherMethod(T ob) throws Exception {
		List<T> list=new ArrayList<T>();
		
		for(int i=0;i<3;i++){
		T newobj=(T) ob.getClass().newInstance();
		Class<?> fatherclass = newobj.getClass().getSuperclass();
		Method fatherMethod = fatherclass.getDeclaredMethod("setFather",new Class[] { String.class});
		fatherMethod.invoke(newobj,new Object[] {"father"});
		 list.add(newobj);
		}
		return list;
	}

	public static void main(String[] args) throws Exception {
//		System.out.println(((Bill)getFatherMethod(new Bill())).getFather());
		
		List<Bill> list= ParentMethod.getFatherMethod(new Bill());
		for(int i=0;i<list.size();i++)
			System.out.println(list.get(i).getFather());
		
	}
}
