package com.annotation.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BaseMapping {
	private static Map<String, Method> methodMapping = null;

	static {
		methodMapping = new HashMap<String, Method>();
		Class[] clazz = new Class[] { Operate.class };
		for (int i = 0; i < clazz.length; i++) {
			initMethodMapping(clazz[i]);
		}
	}

	/**
	 * 添加有MethodAnnotation注解的方法
	 * 
	 * @param clazz
	 */
	public static void initMethodMapping(Class clazz) {
		Method[] method = clazz.getMethods();
		for (int i = 0; i < method.length; i++) {
			MethodAnnotation ann = method[i]
					.getAnnotation(MethodAnnotation.class);
			if (null == ann)
				continue;
			String methodName = ann.mappingmethod();
			if (methodMapping.containsKey(ann))
				continue;
			methodMapping.put(methodName, method[i]);
		}
	}

	/**
	 * 调用方法
	 * @param key
	 * @param param
	 * @return
	 */
	public String callMethod(String key, String param)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		Method method = methodMapping.get(key);
		if (null != method) {/*System.out.print(this+"\n"+method+"\n");*/
			return (String) method.invoke(this, new Object[] { param });
		}
		return "不存在该方法";
	}
}
