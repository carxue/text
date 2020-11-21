package com.jdk8.list;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ListTest {
	/*Stream支持顺序和并行聚合操作的元素序列*/
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
	    Stream<Integer> stream = numbers.stream();
	    stream.filter((x) -> {return x % 2 == 0; }).map((x) -> {return x * x;}).forEach(System.out::println);
		streamMapToInt(null);
	}

	/*Lists是Guava中的一个工具类*/
	private static void filterListNull(Object obj){
		/*①创建Stream②转换Stream，每次转换原有Stream对象不改变，返回一个新的Stream对象,可以有多次转换
		* ③对Stream进行聚合（Reduce）操作，获取想要的结果*/
		List<Integer> numbers = Arrays.asList(1, 6, 7, 4, null, 6, 7, 8, null, 10);
		long notNullCount = numbers.stream().filter(element->element!=null).count();
		System.out.println("stream创建,filter把所有numbers变量的Stream转换成另外一个过滤掉null以后的Stream,count聚合"+notNullCount);
	}

	private static void streamMapToInt(Object obj){
		/*map对于Stream中包含的元素使用给定的转换函数进行转换操作新生成的Stream只包含转换生成的元*/
		List<Integer> numbers = Arrays.asList(1, 6, 7, 4, null, 6, 7, 8, null, 10);
		numbers.stream().filter(a->a!=null).mapToInt(num->num*2).forEach(value -> {System.out.print(value+"   ");});
		System.out.println("=================skip=======================");
		/*skip对数据进行截取*/
		numbers.stream().filter(a->a!=null).mapToInt(num->num*2).skip(2).forEach(value -> {System.out.print(value+"   ");});
		System.out.println("==================distinct======================");
		/*distinct对重复原生进行过滤其实是调用对象的equall方法*/
		numbers.stream().filter(a->a!=null).distinct().mapToInt(num->num*2).skip(2).forEach(value -> {System.out.print(value+"   ");});
		System.out.println("==================limit======================");
		/*limit过滤需要展示的原生个数*/
		numbers.stream().filter(a->a!=null).distinct().mapToInt(num->num*2).skip(2).limit(3).forEach(value -> {System.out.print(value+"   ");});
		System.out.println("==================peek===sum===================");
		/*peek生成新Stream每个元素被消费的时候都会执行给定的消费函数sum计算所有元素的总和*/
		int sum = numbers.stream().filter(a->a!=null).distinct().mapToInt(num->num*2).skip(2).limit(3).peek(System.out::println).sum();
		System.out.println("计算总和："+sum);
	}

}
