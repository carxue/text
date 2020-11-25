package com.jdk8.list;

import com.annotation.fieldconver.Person;
import com.annotation.fieldconver.User;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.*;

public class LambdaTest {

    public static void main(String[] args) {
        innerInterHasImpl(null);
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

    private static void innerInterHasImpl(Object obj) {
        /**[方法引用注意点]：被引用的方法的参数和返回值必须和要实现的抽象方法的参数和返回值一致**/
        //引用out对象的打印输出方法作为Consumer接口accept方法的具体实现
        Consumer<String> consumer = System.out::println;
        consumer.accept("薛奎");
      /*  //供给型接口Supplier，返回指定字符串
        Supplier<String> supplier = LambdaTest.testSup();
        //获取字符串
        supplier.get();*/

        //lambda表达式常用方式
        BiPredicate<String, String> bp1 = (x, y) -> x.equals(y);
        //方法引用：类::实例方法（方法传入参数是两个参数，且第一个参数作为方法调用对象，第二个参数作为调用的方法的参数）
        BiPredicate<String, String> bp2 = String::equals;
        System.out.println(bp1.test("aaa", "aaa"));
        System.out.println(bp2.test("aaa", "bbb"));

        /**[构造器引用]：通过函数式接口实例化类时可进行构造器引用**/
        /**引用到的是与函数式接口中的方法参数个数及类型相同的构造器**/
        Supplier<Person> supplier1 = () -> new Person();
        //构造器引用:通过类型推断，引用无参构造器
        Supplier<Person> supplier2 = Person::new;
        Person person1 = supplier1.get();
        Person person2 = supplier2.get();
        System.out.println(person1 + "  :  " + person2);

        BiFunction<Boolean, String, Passenger> supplier3 = (x, y) -> new Passenger(x, y);
        //构造器引用:通过类型推断，引用无参构造器
        BiFunction<Boolean, String, Passenger> supplier4 = Passenger::new;
        Passenger passenger1 = supplier3.apply(true, "xuekui");
        Passenger passenger2 = supplier4.apply(true, "薛奎");
        System.out.println(passenger1 + "  :  " + passenger2);


        //[数组引用]lambda表达式常用方式
        Function<Integer, String[]> fun1 = (x) -> new String[x];
        String[] strs1 = fun1.apply(10);
        //数组引用
        Function<Integer, String[]> fun2 = String[]::new;
        String[] strs2 = fun2.apply(10);

    }

    private static String testSup() {
        return "Supplier->xuekui";
    }


}
