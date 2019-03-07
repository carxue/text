package com.proxy;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationHandler;

public class DynamicSubject implements InvocationHandler { 
   private Object sub; //被代理的任意实例
   public DynamicSubject() {} 
   public DynamicSubject(Object obj) {    
       sub = obj; //我要代理谁，就传谁
   }
   //调用被代理的方法
 public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//       System.out.println("before calling " + method);
	   ProxyUtils.generateClassFile(sub.getClass(), "ProxySubject");
       method.invoke(sub,args);
//       System.out.println("after calling " + method);
       return null; 
   }
}
