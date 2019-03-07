package com.annotation;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;

@DocumentAnnotation(name="薛奎")
public class Test {
	public static void main(String[] args) throws Exception {
		SuppressWarningsTest supp = new SuppressWarningsTest();
		Class<SuppressWarningsTest> suppclass = SuppressWarningsTest.class;
		Method tmethod = suppclass.getMethod("tests", new Class[] {});
		System.out.println(tmethod.getAnnotations().length);
		if (tmethod.isAnnotationPresent(MyAnnotation.class)) {
			tmethod.invoke(supp, new Object[] {});
			MyAnnotation ann = tmethod.getAnnotation(MyAnnotation.class);
			System.out.println(ann.country() + " ：" + ann.address());
		}
//		System.out.println(Pattern.matches("[1-9][0-9]{7}", null));
		
		Class strInt =new ArrayList<Integer>().getClass();
		Class strStr =new ArrayList<String>().getClass();
		System.out.println(strInt==strStr);
		

		
		String str="登录验证码为{code}，5分钟内有效，请尽快输入验证（乐言评论）".replace("{code}", "123456");
		System.out.println(str);
		
		
		String wds = "<p>水电费fghfgh水电费</p>ghfghffghfgh<img>水电fghfg费水电费</img>";
		
		
		System.out.println(wds.replaceAll("</?[^>]+>", ""));
		
		String aaa="o0AP6wrJh__7hYp9UzGiN1lLQLGk_20180913190413";
		String[] as= aaa.split("_");
		for(String a:as )
			System.out.println(a);
		System.out.println(System.currentTimeMillis()+"_"+System.currentTimeMillis()/1000);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println((new Date()).getTime()/1000);
		System.out.println(new Long(1541597178).longValue()*1000);
		System.out.println(df.format(new Date(1541597178l*1000)));
		
		System.out.println("1243213412_bakup".endsWith("_bakup"));
		//java 栈列表
		Stack<Integer> stack = new Stack<>();
		stack.push(1);stack.push(2);stack.push(3);
		while(!stack.isEmpty())
			System.out.println(stack.pop());
		
	}
}
