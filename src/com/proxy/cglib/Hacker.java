package com.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class Hacker implements MethodInterceptor{

	@Override
	public Object intercept(Object obj, Method method, Object[] arg2,
			MethodProxy proxy) throws Throwable {
		proxy.invokeSuper(obj,arg2 );
		return null;
	}

}
