package com.reflection;

import java.lang.reflect.Method;

public class TextPrivate {
	public static void main(String[] args) throws Exception {
		TextPrivate a=new TextPrivate();
		a.aa();
	}
	public void aa() throws Exception, NoSuchMethodException{
		Private p = new Private();
		Class<?> classType = p.getClass();
		Method getMethod = classType.getDeclaredMethod("sayHello",
				new Class[] { String.class });
		getMethod.setAccessible(true);
		String value = (String) getMethod.invoke(this, new Object[] { "xuekui" });
//		System.out.println(value);
	}
}
