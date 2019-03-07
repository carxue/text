package com.annotation;

import java.lang.reflect.Method;

@AnnotationTest(enums = EnumTest.HELLO)
public class SuppressWarningsTest {
	@AnnotationTest(value="薛奎",enums = EnumTest.HELLO)
	@MyAnnotation(address="湖北襄阳",country="中国")
	public void tests() {
       System.out.println("注解反射调用测试");
	}
}
