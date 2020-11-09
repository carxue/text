package com.annotation.fieldconver;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.annotation.fieldconver.FieldConvert.Ignore;

/**
 * 将一个对象的值转换到另一个对象的对应属性上
 * @author xuekui
 * @param <T>
 */
public class ObjectConvertUtils<T,K> {
	
	private static Log logger = LogFactory.getLog(ObjectConvertUtils.class.getName());
	
	public static <T,K> void convertObjectAttrs(K source,T target) {
		Class<?> toClass = target.getClass();
		Field[] toFileds = null;
		for (; toClass != Object.class; toClass = toClass.getSuperclass()) {
			toFileds = toClass.getDeclaredFields();
			loopSettingFields(target, source, toFileds);
		}
	}

	private static <T,K> void loopSettingFields(T t, K k, Field[] toFileds) {
		for (Field field : toFileds) {
			FieldConvert fieldAnno = field.getAnnotation(FieldConvert.class);
			if (fieldAnno != null && fieldAnno.ignore() == Ignore.N) {
				field.setAccessible(true);
				String filedName = fieldAnno.filed();
				settingToFileds(field, filedName, t, k);
			}
		}
	}

	private static <T,K> void settingToFileds(Field field, String filedName,T t, K k) {
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
					}else if(type.equals(BigDecimal.class)){
						field.set(t, new BigDecimal(val.toString()));
					}
				}
				break;
			} catch (IllegalArgumentException e){
				logger.error("参数非法异常:"+e.getMessage());
			}catch (Exception e){
				logger.error("数据赋值异常:"+e.getMessage());
			}
		}
	}
	
	public static void main(String[] args) {
		User user = new User();
		user.setAge(22);
		user.setTime(new Date());
		user.setMyCountry("中国");//父类属性
		List<Object> list = Arrays.asList("sdfds",new Integer(1));
		user.setElist(list);
		//没有属性值的对象
		Person person = new Person();
		//将user对象的属性值转换到person对象上
		ObjectConvertUtils.convertObjectAttrs(user,person);
		System.out.println(person);
	}
}
