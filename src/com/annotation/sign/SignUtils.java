package com.annotation.sign;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.annotation.fieldconver.ObjectConvertUtils;

public class SignUtils {

	private static Log logger = LogFactory.getLog(ObjectConvertUtils.class.getName());

	private static final String ENCRYPT_TYPE = "MD5";

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
					logger.error("获取签名属性异常:{}"+ e.getMessage());
				}
			}
		}
	}

	/**
	 * @function 将需要签名的属性按照key=value&key1=value1方式拼接
	 * @param t         需要签名的对象属性使用@SignAttr标注
	 * @param dfPattern 日期转换为指定格式,为NULL则使用默认字符串
	 * @return
	 */
	public static <T> String entrySignAnnotationStr(T t, DateFormat dfPattern) {
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

	/**
	 * @function 将map中的键值对排序后按照key=value&key1=value1方式拼接
	 * @param map       需要签名的map对象
	 * @param dfPattern 日期转换为指定格式,为NULL则使用默认字符串
	 * @return
	 */
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

	/**
	 * 获取对象所有的属性
	 * 
	 * @param t
	 * @param fields
	 * @param map
	 */
	private static <T> void getObjectAttrs(T t, Field[] fields, Map<String, Object> map) {
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				map.put(field.getName(), field.get(t));
			} catch (Exception e) {
				logger.error("获取签名属性异常:{}"+e.getMessage());
			}
		}
	}

	/**
	 * 对特定字符串进行md5加密，可以添加盐值
	 * 
	 * @param source 要加密的字符串
	 * @param salt   添加的盐值
	 * @param upper  是否转换为大写
	 * @return
	 */
	public static String encrypt(String source, String salt, Boolean upper) {
		// 检查加密源是否存在
		if (source == null) {
			return null;
		}
		// 添加盐值
		if (salt != null) {
			source += salt;
		}
		try {
			MessageDigest md = MessageDigest.getInstance(ENCRYPT_TYPE);
			md.reset();
			md.update(source.getBytes("UTF-8"));
			byte[] byteArray = md.digest();
			StringBuffer rs = new StringBuffer();
			for (int i = 0; i < byteArray.length; i++) {
				if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
					rs.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
				} else {
					rs.append(Integer.toHexString(0xFF & byteArray[i]));
				}
			}
			if (upper != null && upper) {
				return rs.toString().toUpperCase();
			} else {
				return rs.toString();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return source;
		}
	}
}
