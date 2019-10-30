package com.annotation.test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.annotation.fieldconver.ObjectConvertUtils;

public class SignAttrUtils {
	
	private static Log logger = LogFactory.getLog(ObjectConvertUtils.class.getName());
	
	public static <T> Map<String, Object> getNeedSignAttrMap(T t) {
		Class<?> toClass = t.getClass();
		Map<String, Object> map = new HashMap<>();
		Field[] toFileds = null;
		for (; toClass != Object.class; toClass = toClass.getSuperclass()) {
			toFileds = toClass.getDeclaredFields();
			getObjectAttrs(t, toFileds, map);
		}
		return map;
	}

	public static <T> void getObjectAttrs(T t, Field[] fields, Map<String, Object> map) {
		for (Field field : fields) {
			SignAttr fieldAnno = field.getAnnotation(SignAttr.class);
			if (fieldAnno != null) {
				field.setAccessible(true);
				Object FieldVal;
				try {
					FieldVal = field.get(t);
					if (FieldVal != null) {
						map.put(fieldAnno.fieldName(), FieldVal);
					}
				} catch (Exception e) {
					logger.error("获取签名属性异常:"+e.getMessage());
				}
			}
		}
	}
}
