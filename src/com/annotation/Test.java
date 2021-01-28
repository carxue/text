package com.annotation;

import com.annotation.fieldconver.UserType;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

@DocumentAnnotation(name = "薛奎")
public class Test {
    public static void main(String[] args) throws Exception {
        SuppressWarningsTest supp = new SuppressWarningsTest();
        Class<SuppressWarningsTest> suppclass = SuppressWarningsTest.class;
        Method tmethod = suppclass.getMethod("tests", new Class[]{});
        System.out.println(tmethod.getAnnotations().length);
        if (tmethod.isAnnotationPresent(MyAnnotation.class)) {
            tmethod.invoke(supp, new Object[]{});
            MyAnnotation ann = tmethod.getAnnotation(MyAnnotation.class);
            System.out.println(ann.country() + " ：" + ann.address());
        }
//		System.out.println(Pattern.matches("[1-9][0-9]{7}", null));

        Class strInt = new ArrayList<Integer>().getClass();
        Class strStr = new ArrayList<String>().getClass();
        System.out.println(strInt == strStr);


        String str = "登录验证码为{code}，5分钟内有效，请尽快输入验证（乐言评论）".replace("{code}", "123456");
        System.out.println(str);


        String wds = "<p>水电费fghfgh水电费</p>ghfghffghfgh<img>水电fghfg费水电费</img>";


        System.out.println(wds.replaceAll("</?[^>]+>", ""));

        String aaa = "o0AP6wrJh__7hYp9UzGiN1lLQLGk_20180913190413";
        String[] as = aaa.split("_");
        for (String a : as)
            System.out.println(a);
        System.out.println(System.currentTimeMillis() + "_" + System.currentTimeMillis() / 1000);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println((new Date()).getTime() / 1000);
        System.out.println(new Long(1541597178).longValue() * 1000);
        System.out.println(df.format(new Date(1541597178l * 1000)));

        System.out.println("1243213412_bakup".endsWith("_bakup"));
        //java 栈列表
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        while (!stack.isEmpty())
            System.out.println(stack.pop());

        System.out.println("============" + UserType.SMS.getText());
        Integer a = new Integer(130);
        Integer b = new Integer(131);
        System.out.println(a.compareTo(b));

        BigDecimal mainTotal = BigDecimal.ZERO;
        mainTotal.add(new BigDecimal(100));
        mainTotal.add(new BigDecimal(2));
        System.out.println(mainTotal.intValue());

        DecimalFormat df1 = new DecimalFormat("0000");
        String id = df1.format(1);
        System.out.println(id);
        BigDecimal ret = new BigDecimal("2");
        BigDecimal buy = new BigDecimal("3");
/*
		BigDecimal rate = ret.divide(buy,2, BigDecimal.ROUND_HALF_UP);
*/
        BigDecimal aa = new BigDecimal("60").multiply(new BigDecimal("2")).divide(new BigDecimal("3"), 2, BigDecimal.ROUND_HALF_UP);
        System.out.println(aa);

        Integer da = new Integer(1233443);
        Integer daa = new Integer(123344);
        System.out.println(da > daa);
        Set<Boolean> set = new HashSet<>();
        set.add(true);
        set.add(new Boolean(true));
        /*set.add(false);*/
        System.out.println(set.size());

        BigDecimal a1=new BigDecimal("10");
        BigDecimal b1=new BigDecimal("3");
        BigDecimal c1=a1.divide(b1).setScale(2, RoundingMode.DOWN);
        System.out.println("----------------"+c1);


    }

    private static void a(UserType type) {
        type = UserType.UMS;
    }
}
