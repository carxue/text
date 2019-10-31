package com.annotation.sign;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.annotation.fieldconver.ObjectConvertUtils;

public class SignUtils {

	private static Log logger = LogFactory.getLog(ObjectConvertUtils.class.getName());

	public static <T> Map<String, Object> getNeedSignSortAttrMap(T t) {
		Class<?> toClass = t.getClass();
		Map<String, Object> map = new TreeMap<>(new Comparator<String>() {
			public int compare(String obj1, String obj2) {
				return obj1.compareTo(obj2);
			}
		});
		for (; toClass != Object.class; toClass = toClass.getSuperclass()) {
			Field[] toFileds = toClass.getDeclaredFields();
			getObjectAnnoAttrs(t, toFileds, map);
		}
		return map;
	}

	private static <T> void getObjectAnnoAttrs(T t, Field[] fields, Map<String, Object> map) {
		for (Field field : fields) {
			SignAttr fieldAnno = field.getAnnotation(SignAttr.class);
			if (fieldAnno != null) {
				field.setAccessible(true);
				try {
					Object FieldVal = field.get(t);
//					if (FieldVal != null) {
						map.put(field.getName(), FieldVal);
//					}
				} catch (Exception e) {
					logger.error("获取签名属性异常:" + e.getMessage());
				}
			}
		}
	}

	/**
	 * @function 将需要签名的属性按照key=value&key1=value1方式拼接
	 * @param t 需要签名的对象属性使用@SignAttr标注
	 * @param dfPattern 日期转换为指定格式,为NULL则使用默认字符串
	 * @return
	 */
	public static <T> String needSignAnnotationStr(T t, DateFormat dfPattern) {
		Map<String, Object> map = getNeedSignSortAttrMap(t);
		Set<Map.Entry<String, Object>> set = map.entrySet();
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Object> entry : set) {
			Object val = entry.getValue();
			if (dfPattern != null) {
				if (val instanceof Date) {
					val = dfPattern.format(val);
				}
			}
			sb.append(entry.getKey()).append("=").append(val).append("&");
		}
		return sb.substring(0, sb.length() - 1);
	}
	
	public static <T> String mapSortStr(Map<String, Object> map, DateFormat dfPattern) {
		 List<Map.Entry<String, Object>> infos = new ArrayList<Map.Entry<String, Object>>(map.entrySet());
		// 重写集合的排序方法：按字母顺序
		Collections.sort(infos, new Comparator<Map.Entry<String, Object>>() {
			@Override
			public int compare(final Entry<String, Object> o1, final Entry<String, Object> o2) {
				return (o1.getKey().toString().compareTo(o2.getKey()));
			}
		});
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Object> entry : infos) {
			Object val = entry.getValue();
			if (dfPattern != null) {
				if (val instanceof Date) {
					val = dfPattern.format(val);
				}
			}
			sb.append(entry.getKey()).append("=").append(val).append("&");
		}
		return sb.substring(0, sb.length() - 1);
	}

	private static <T> void getObjectAttrs(T t, Field[] fields, Map<String, Object> map) {
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				map.put(field.getName(), field.get(t));
			} catch (Exception e) {
				logger.error("获取签名属性异常:" + e.getMessage());
			}
		}
	}
}
