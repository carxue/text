package com.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import com.reflection.test.Xue;

/**
 * @author ex_kjkfb_xuek
 *
 */
public class ReflectUtils {
	
	/**
	 * 为对象的某个属性或继承的属性赋值
	 * @param property 要赋值的属性
	 * @param obj 要赋的值
	 * @param t 要赋值的对象
	 * @return T 传入的对象
	 * @throws Exception
	 */
	public static <T> T reflectSetField(String property,Object obj,T t) throws Exception {
		Class<?> clazz = t.getClass();
		String firstLetter = property.substring(0, 1).toUpperCase();
		String setMethodName = "set" + firstLetter+ property.substring(1);
		Method method = null;
		try {
			method = clazz.getMethod(setMethodName,new Class[] { obj.getClass() });
		} catch (Exception e) {
			try{
				method = clazz.getSuperclass().getMethod(setMethodName,new Class[] { obj.getClass() });
			} catch (Exception e1) {
				System.out.println("ReflectUtils.reflectSetField-赋值与属性类型不匹配！");
			}
		}
		if(null!=method)
			method.invoke(t, new Object[] { obj });
		return t;
	}
	
	public static <T> T reflectSetField1(String property,Object obj,T t) throws Exception {
		Class<?> clazz = t.getClass();
		try{
			Field field = clazz.getDeclaredField(property);
			field.setAccessible(true);
			field.set(t, obj);
		}catch(Exception e){
			System.out.println("ReflectUtils.reflectSetField1-赋值与属性类型不匹配！");
		}
		return t;
	}
	
	/**
	 * 为对象的多个属性或继承的属性赋值
	 * @param property 要赋值的属性集合
	 * @param obj 要赋的值列表
	 * @param t 要赋值的对象
	 * @return
	 * @throws Exception
	 */
	public static <T> T reflectSetFields(String[] property,Object[] obj,T t) throws Exception {
		Class<?> clazz = t.getClass();
		for(int i=0;i<property.length;i++){
			String firstLetter = property[i].substring(0, 1).toUpperCase();
			String setMethodName = "set" + firstLetter+ property[i].substring(1);
			Method method = null;
			try {
				method = clazz.getMethod(setMethodName,new Class[] { obj[i].getClass() });
			} catch (Exception e) {
				try{
					method = clazz.getSuperclass().getMethod(setMethodName,new Class[] { obj[i].getClass() });
				} catch (Exception e1) {
					System.out.println("ReflectUtils.reflectSetFields-赋值与属性类型不匹配！");
				}
			}
			if(null !=method)
				method.invoke(t, new Object[] { obj[i] });
		}
		return t;
	}
	
	/**
	 * 获得对象或超类的属性值
	 * @param obj 对象
	 * @param fieldName 属性字段名
	 * @return
	 * @throws Exception
	 */
	public static <T> Object getFieldValue(Object obj,String fieldName) throws Exception{
		Class<?> clazz = obj.getClass();
		String firstLetter = fieldName.substring(0, 1).toUpperCase();
		String getMethodName = "get" + firstLetter+ fieldName.substring(1);// get方法
		Method method = null;
		try {
			method = clazz.getMethod(getMethodName,new Class[] {});
		} catch (Exception e) {
			try{
				method = clazz.getSuperclass().getMethod(getMethodName,new Class[] {});
			} catch (Exception e1) {
				System.out.println("ReflectUtils.getFieldValue-传入的字段不存在！");
			}
		}
		if(null !=method)
			return method.invoke(obj, new Object[] {});
		return null;
	}
	public static void main(String[] args) throws Exception{
		Xue xue = new Xue();
		Object num = (Object)123;
		reflectSetField("price",num,xue);
		System.out.println(xue.getPrice());
		
		reflectSetField("date",new Date(),xue);
		System.out.println(xue.getDate());
		
		reflectSetField1("say","sss",xue);
		System.out.println(xue.getSay());
	}
}
