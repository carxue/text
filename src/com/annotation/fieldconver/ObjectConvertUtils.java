package com.annotation.fieldconver;

import java.lang.reflect.Field;
import java.util.Date;

import com.annotation.fieldconver.FieldConvert.Ignore;
import com.google.gson.Gson;

/**
 * 将一个对象的值转换到另一个对象的对应属性上
 * @author xuekui
 * @param <T>
 */
public class ObjectConvertUtils<T,K> {
	
	public static <T,K> void getSettingFields(T t, K k) {
		Class<?> toClass = t.getClass();
		Field[] toFileds = null;
		for (; toClass != Object.class; toClass = toClass.getSuperclass()) {
			toFileds = toClass.getDeclaredFields();
			loopSettingFields(t, k, toFileds);
		}
	}

	public static <T,K> void loopSettingFields(T t, K k, Field[] toFileds) {
		for (Field field : toFileds) {
			FieldConvert fieldAnno = field.getAnnotation(FieldConvert.class);
			if (fieldAnno != null && fieldAnno.ignore() == Ignore.N) {
				field.setAccessible(true);
				String filedName = fieldAnno.filed();
				settingToFileds(field, filedName, t, k);
			}
		}
	}

	public static <T,K> void settingToFileds(Field field, String filedName,T t, K k) {
		Class<?> fromClass = k.getClass();
		for (; fromClass != Object.class; fromClass = fromClass.getSuperclass()) {
			try {
				Field fromField = fromClass.getDeclaredField(filedName);
				fromField.setAccessible(true);
				Class<?> type = field.getType();
				String val = fromField.get(k).toString();
				if(type==fromField.getType()){
					field.set(t, fromField.get(k));
				}else {
					if(type.equals(String.class)){
						field.set(t, val);
					}else if(type.equals(Integer.class)){
						field.set(t, Integer.parseInt(val));
					}else if(type.equals(Double.class)){
						field.set(t, Double.parseDouble(val));
					}else if(type.equals(Long.class)){
						field.set(t, Long.parseLong(val));
					}else if(type.equals(Float.class)){
						field.set(t, Float.parseFloat(val));
					}else if(type.equals(Short.class)){
						field.set(t, Short.parseShort(val));
					}else if(type.equals(Character.class)){
						field.set(t, val.toString().charAt(0));
					}else if(type.equals(Byte.class)){
						field.set(t, Byte.parseByte(val));
					}else if(type.equals(Boolean.class)){
						field.set(t, Boolean.parseBoolean(val));
					}
				}
				break;
			} catch (IllegalArgumentException e) {System.out.println(e.getMessage());}
			 catch (Exception e) {System.out.println(e.getMessage());}
		}
	}
	
	public static void main(String[] args) {
		User user = new User();
		user.setName("薛奎");
		user.setPsword("111111");
		user.setAge(9100);
		user.setTime(new Date());
		user.setSex(Boolean.TRUE);
		user.setMyCountry("中国");
		
		Person person = new Person();
		ObjectConvertUtils.getSettingFields(person,user);
		Gson gson = new Gson();
		System.out.println(gson.toJson(person));
		
		
		for(int i=0;i<args.length;i++){
			System.out.println(args[i]);
			}
		
//		System.out.println(System.getProperty("mian.args"));
//		System.out.println(System.getProperty("sun.boot.class.path"));
//		System.out.println(System.getProperty("java.ext.dirs"));
//		System.out.println(System.getProperty("java.class.path"));
		
	}
}
