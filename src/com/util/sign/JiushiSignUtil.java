package com.util.sign;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JiushiSignUtil<T> {

	private static Log logger = LogFactory.getLog(JiushiSignUtil.class.getName());
	
	private static final String NO_SIGN = "sign";

	public static <T> String paramSign(T t,String appkey) {
		Map<String, Object> map = new TreeMap<String, Object>(new Comparator<String>() {
			public int compare(String obj1, String obj2) {
				return obj1.compareTo(obj2);// 升序排列
			}
		});
		//获取对象名称和值
		Class<?> toClass = t.getClass();
		Field[] toFileds = null;
		for (; toClass != Object.class; toClass = toClass.getSuperclass()) {
			toFileds = toClass.getDeclaredFields();
			getKeyValue(t, toFileds, map);
		}

		return Md5Str(map,appkey);
	}

	public static <T> void getKeyValue(T t, Field[] toFileds, Map<String, Object> map) {
		for (Field field : toFileds) {
			field.setAccessible(true);
			String name = field.getName();
			try {
				map.put(name, field.get(t));
			} catch (Exception e) {
				logger.error(name + ":该字段获取中失败！");
			}
		}
	}
	
	public static <T> String Md5Str(Map<String, Object> map,String appkey) {
		map.remove(NO_SIGN);
        Set<String> keySet = map.keySet();
        Iterator<String> iter = keySet.iterator();
        StringBuilder sb = new StringBuilder();
        while (iter.hasNext()) {
        	String name = iter.next();
        	Object value = map.get(name);
        	if(value!=null){
        		sb.append(name).append("=").append(value).append("&");
        	}
        }
        sb.append("appkey=").append(appkey);
        logger.info("------------加密前的字符串为:"+sb);
        return JiushiMd5.toMd5(sb.toString()).toUpperCase();
	}
	
	
	
	/**
	 * 参数构建表单样式
	 * @param t
	 * @return
	 */
	public static <T> String buildForm(T t) {
		Map<String, Object> map = new HashMap<>();
		//获取对象名称和值
		Class<?> toClass = t.getClass();
		Field[] toFileds = null;
		for (; toClass != Object.class; toClass = toClass.getSuperclass()) {
			toFileds = toClass.getDeclaredFields();
			getKeyValue(t, toFileds, map);
		}
        Set<String> keySet = map.keySet();
        Iterator<String> iter = keySet.iterator();
        StringBuilder sb = new StringBuilder();
        while (iter.hasNext()) {
        	String name = iter.next();
        	Object value = map.get(name);
        	if(value!=null){
        		sb.append(name).append("=").append(value).append("&");
        	}
        }
        logger.info("------------字符串排序为:"+sb);
        String retStr = sb.toString();
        return retStr.substring(0,retStr.length()-1);
	}
}
