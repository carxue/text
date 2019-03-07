package com.proxy.asm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import com.sun.xml.internal.ws.org.objectweb.asm.ClassWriter;
import com.sun.xml.internal.ws.org.objectweb.asm.MethodVisitor;
import com.sun.xml.internal.ws.org.objectweb.asm.Opcodes;
import com.sun.xml.internal.ws.org.objectweb.asm.Type;

public class MyGenerator {
	public static void main(String[] args) throws Exception {
		System.out.println();
		ClassWriter classWriter = new ClassWriter(0);
		classWriter.visit(Opcodes.V1_6,//java版本
				Opcodes.ACC_PUBLIC,//类修饰符
				"Programmer", null,//programmer类额权限定名
				"java/lang/Object", null);
		
		//创建构造函数
		MethodVisitor mv = classWriter.visitMethod(
				Opcodes.ACC_PUBLIC, 
				"<init>", "()V", null,null);
		mv.visitCode();
		mv.visitVarInsn(Opcodes.ALOAD, 0);//AALOAD:表示数组元素
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
				"java/lang/Object", "<init>", "()V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(1, 1);
		mv.visitEnd();
		
		//定义code方法
		MethodVisitor mVisitor = classWriter.visitMethod(
				Opcodes.ACC_PUBLIC, 
				"code", "()V", null,null);
		mVisitor.visitCode();
		//Type.getObjectType("java/io/PrintStream").getDescriptor()
		mVisitor.visitFieldInsn(Opcodes.GETSTATIC,
				"java/lang/System",
				"out", "Ljava/io/PrintStream;" );
		mVisitor.visitLdcInsn("I'm a Programmer,Just Coding.....");
		mVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, 
				"java/io/PrintStream", "println",
				"(Ljava/lang/String;)V");
		mVisitor.visitInsn(Opcodes.RETURN);
		mVisitor.visitMaxs(2, 2);
		mVisitor.visitEnd();
		classWriter.visitEnd();
		
		//使用classWriter类以及完成
		//将classWriter转换成字节数组写到文件里面去
		byte[] classData = classWriter.toByteArray();
		File file = new File("E://Programmer.class");
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(classData);
		fos.close();
 				
		
		
		String descriptor =Type.getObjectType(Type.getInternalName(PrintStream.class)).getDescriptor();
		System.out.println(descriptor);
		System.out.println(Type.getObjectType("java/io/PrintStream").getDescriptor());
		System.out.println(Type.getDescriptor(Object.class));
		java.lang.reflect.Method m = String.class.getMethod("substring", int.class);  
        System.out.println(Type.getMethodDescriptor(m));   
		
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
	}
}
