package com.annotation;

public @interface AnnotationTest {
	//默认值为hello,如果没有第一默认值，则在其他地方使用的时候
	//会报错，AnnotationTest("world") world会覆盖hello
	//由于属性名为value所以赋值时可以省略掉value=，其他的都不可以
	String value() default "hello";
	EnumTest enums();
}
enum EnumTest{
	HELLO,WORLD,WELCOM
}
