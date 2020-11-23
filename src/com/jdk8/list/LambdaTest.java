package com.jdk8.list;

import com.annotation.fieldconver.User;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaTest {

    public static void main(String[] args) {
        innerInter(null);
    }

    private static void toComparable(Object obj) {
        /*实现只有一个抽象方法的接口时会自行匹配到该方法,该接口只有一个方法*/
        Comparable<Integer> comparable = (x) -> Integer.compare(x, 100);
        System.out.println(comparable.compareTo(101));
    }

    private static void innerInter(Object obj) {
        //消费型接口Consumer，输入一个参数，对其进行打印输出
        Consumer<String> consumer = (x) -> System.out.println(x);
        //打印字符串
        consumer.accept("hehe");

        //供给型接口Supplier，返回指定字符串
        Supplier<String> supplier = () -> "Hello world!";
        //获取字符串
        supplier.get();

        //函数型接口Function，输入字符串，返回字符串长度
        Function<String, Integer> function = (x) -> x.length();
        //获取字符串长度
        function.apply("Hello world!");

        //断言型接口Predicate，输入数字，判断是否大于0
        Predicate<Integer> predicate = (x) -> x > 0;
        //获取判断结果
        predicate.test(10);
    }


}
