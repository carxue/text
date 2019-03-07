package com.reflection.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.util.ReflectUtils;
import com.util.Shop;

public class XueTest {

	@SuppressWarnings("unchecked")
	public static <T extends Object> List<T> test(String[] visibles,Class<?> clazz) throws Exception {
		Object[] obj = { "薛奎", 23, 12.2, "湖北", 12 ,"hello 薛奎"};
		Object[] obj1 = { "pretty snow", 23, 12.2, "Hunan", 12 ,"haha pretty"};
		List<Object[]> valList = new ArrayList<Object[]>();
		valList.add(obj);
		valList.add(obj1);
		
		List<T> newlist = new ArrayList<T>();
		for (int i = 0; i < valList.size(); i++) {
			Object retObj = clazz.getConstructor(new Class[]{}).newInstance(new Object[]{});
//			Class<?> clazz = retObj.getClass();//下面这种方法也可以
//			Class<?> clazz = t.getClass();
			for (int j = 0; j < visibles.length; j++) {
				String firstLetter = visibles[j].substring(0, 1).toUpperCase();
				String setMethodName = "set" + firstLetter+ visibles[j].substring(1);// get方法
				Method method = null;
				try {
					method = clazz.getMethod(setMethodName,new Class[] { valList.get(i)[j].getClass() });
				} catch (Exception e) {
					method = clazz.getSuperclass().getMethod(setMethodName,new Class[] { valList.get(i)[j].getClass() });
				}
				method.invoke(retObj, new Object[] { valList.get(i)[j] });
			}
			T retT=(T)retObj;
			newlist.add(retT);
		}
		return newlist;
	}
	
	/**
	 * 为对象的某个属性赋值，其他值任保留
	 * @param property 要赋值的属性
	 * @param obj 要赋的值
	 * @param t 要赋值的对象
	 * @return T 传入的对象
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T reflectOnePro(String property,Object obj,T t) throws Exception {
//		Object retObj = t.getClass().getConstructor(new Class[]{}).newInstance(new Object[]{});
		Class<?> clazz = t.getClass();
		String firstLetter = property.substring(0, 1).toUpperCase();
		String setMethodName = "set" + firstLetter+ property.substring(1);// get方法
		Method method = null;
		try {//myself
			method = clazz.getMethod(setMethodName,new Class[] { obj.getClass() });
		} catch (Exception e) {
			try{//myfather
				method = clazz.getSuperclass().getMethod(setMethodName,new Class[] { obj.getClass() });
			} catch (Exception e1) {
				System.out.println("one-赋值与属性类型不匹配");
			}
		}
		if(null!=method)
			method.invoke(t, new Object[] { obj });
		
		/*BeanUtils.setProperty(t, property, obj);*/
		
		return t;
	}
	
	/**
	 * 为对象的多个属性赋值
	 * @param property 要赋值的属性集合
	 * @param obj 要赋的值列表
	 * @param t 要赋值的对象
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T reflectMorePros(String[] property,Object[] obj,T t) throws Exception {
//		Object retObj = t.getClass().getConstructor(new Class[]{}).newInstance(new Object[]{});
		Class<?> clazz = t.getClass();
		for(int i=0;i<property.length;i++){
			String firstLetter = property[i].substring(0, 1).toUpperCase();
			String setMethodName = "set" + firstLetter+ property[i].substring(1);// get方法
			Method method = null;
			try {//myself
				method = clazz.getMethod(setMethodName,new Class[] { obj[i].getClass() });
			} catch (Exception e) {
				try{//myfather
					method = clazz.getSuperclass().getMethod(setMethodName,new Class[] { obj[i].getClass() });
				} catch (Exception e1) {
					System.out.println("more-赋值与属性类型不匹配");
				}
			}
			if(null!=method)
				method.invoke(t, new Object[] { obj[i] });
		}
		return t;
	}
	/**
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	public static <T> Object getField(Object obj,String fieldName) throws Exception{
		Class<?> clazz = obj.getClass();
		String firstLetter = fieldName.substring(0, 1).toUpperCase();
		String getMethodName = "get" + firstLetter+ fieldName.substring(1);// get方法
		Method method = null;
		try {//myself
			method = clazz.getMethod(getMethodName,new Class[] {});
		} catch (Exception e) {
			try{//myfather
				method = clazz.getSuperclass().getMethod(getMethodName,new Class[] {});
			} catch (Exception e1) {
				System.out.println("more-赋值与属性类型不匹配");
			}
		}
		if(null!=method)
			return method.invoke(obj, new Object[] {});
		return null;
	}
	
	public static  Field getField1(Object obj,String fieldName) throws Exception{
		Field field = null;
		for (Class<?> clazz=obj.getClass(); clazz != Object.class;clazz=clazz.getSuperclass()){
	    	try {
	    		field=clazz.getDeclaredField(fieldName);
	    		break;
	    	} catch (NoSuchFieldException e) {
	    		//这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
	       }
	    }
       return field;
	}

	public static Object getFieldValue(Object obj,String fieldName)
	{
		try {
			Field field = getField1(obj,fieldName);
			if(field!=null)
			{
				field.setAccessible(true);
				return field.get(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		Info info = new Info();
/*		reflectOnePro("name","薛奎",info);
		System.out.println(info.getName()+"\n");
		
		List<String> nameList = new ArrayList<String>();
		nameList.add("薛奎");nameList.add("小雪");
		reflectOnePro("list",nameList,info);
		System.out.println(info.getList().get(1));*/
		
		/*DateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS");
		System.out.println(df.format(new Date()));
		for(long i=0;i<1000000;i++){
			reflectOnePro("name","薛奎",info);
		}
		System.out.println(df.format(new Date()));
		for(long i=0;i<1000000;i++){
			ReflectUtils.reflectSetField("name","薛奎",info);
		}
		System.out.println(df.format(new Date()));*/
		
		Shop shop = new Shop();
		reflectOnePro("shop_id",123456,shop);
		System.out.println(shop.getShop_id());
		
	}
}
