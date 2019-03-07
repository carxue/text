package com.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//指定该注解的保存地点：在.class文件中并且运行时VM可以访问
//如果不是rumtime则通过反射机制无法访问到MyAnnotation
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String address();
    String country();
}
