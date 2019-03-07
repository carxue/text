package com.annotation.fieldconver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface FieldConvert {
	public enum Ignore{Y,N};
	String filed();//对应字段
	Ignore ignore() default Ignore.N;//可以设置值默认:N
	//String type();//不同类型的转换
}

