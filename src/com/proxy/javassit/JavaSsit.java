package com.proxy.javassit;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

public class JavaSsit {
	public static void main(String[] args) throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.makeClass("com.proxy.javassit.Programmer");
		CtMethod method = CtNewMethod.make("public void code(){}", cc);
		method.insertBefore("System.out.println(\"I'm a Programmer,Just Coding.....\");");
		cc.addMethod(method);
		cc.writeFile("d://");
	}
}
