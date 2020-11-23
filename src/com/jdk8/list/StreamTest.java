package com.jdk8.list;

import com.annotation.fieldconver.Person;
import com.annotation.fieldconver.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
	/*Stream支持顺序和并行聚合操作的元素序列*/
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
	    Stream<Integer> stream = numbers.stream();
	    stream.filter((x) -> {return x % 2 == 0; }).map((x) -> {return x * x;}).forEach(System.out::println);
		streamMap(null);
	}

	/*Lists是Guava中的一个工具类*/
	private static void filterListNull(Object obj){
		/*①创建Stream②转换Stream，每次转换原有Stream对象不改变，返回一个新的Stream对象,可以有多次转换
		* ③对Stream进行聚合（Reduce）操作，获取想要的结果*/
		List<Integer> numbers = Arrays.asList(1, 6, 7, 4, null, 6, 7, 8, null, 10);
		long notNullCount = numbers.stream().filter(element->element!=null).count();
		System.out.println("stream创建,filter把所有numbers变量的Stream转换成另外一个过滤掉null以后的Stream,count聚合"+notNullCount);
	}

	/*转换Map将User对象列表转换为对象列表Person*/
	private static void streamMap(Object obj){
		/*map对于Stream中包含的元素使用给定的转换函数进行转换操作新生成的Stream只包含转换生成的元*/
		User user1 = new User();user1.setName("carxue");user1.setAge(15);
		User user2 = new User();user2.setName("小雪");user2.setAge(20);
		User user3 = new User();user3.setName("小wang");user3.setAge(30);
		List<User> numbers = Arrays.asList(user1,user2,user3);
		List<String> names=numbers.stream().map(user->user.getName()).peek(System.out::println).collect(Collectors.toList());
		List<Person> persons=numbers.stream().map(user-> {
                    Person person = new Person();
                    person.setUserName(user.getName());
                    person.setUserAge(user.getAge());
                    return person;
				}
		).peek(System.out::println).collect(Collectors.toList());
	}


	/*转换MapTo*/
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

	/*汇聚collect*/
	private static void streamReduceCollect(Object obj){
		/*Reduce可变汇聚collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner)*/
		/*都是lambda形式的函数；第一个函数生成一个新的ArrayList实例;*/
		/*第二个函数接受两个参数，第一个是前面生成的ArrayList对象，二个是stream中包含的元素*/
		/*第三个函数也是接受两个参数，这两个都是ArrayList类型的函数体就是把第二个ArrayList全部加入到第一个中*/
		List<Integer> numbers = Arrays.asList(1, 6, 7, 4, null, 6, 7, 8, null, 10);
		List<Integer> numberb=numbers.stream().filter(a->a!=null).collect(() -> new ArrayList<Integer>(),(preList, item) -> preList.add(item),(list1, list2) -> list1.addAll(list2));


		/* 另一个重载的方法collect(Collector<? super T, A, R> collector);*/
		/*这是jdk1.8提供的工具类Collectors.toCollection()*/
		List<Integer> numbera=numbers.stream().filter(a->a!=null).collect(Collectors.toList());
		numbera.forEach(a->{System.out.print(a+" ");});
		System.out.println("=================skip=======================");
		numberb.forEach(a->{System.out.print(a+" ");});
	}

	/*其他汇聚reduce*/
	private static void streamReduceReduce(Object obj){
		/*第一个参数是上次函数执行的返回值;第二个参数是stream中的元素，这个函数把这两个值相加;得到的和会被赋值给下次执行这个函数的第一个参数*/
		/*第一次执行的时候第一个参数的值是Stream的第一个元素，第二个参数是Stream的第二个元素*/
		List<Integer> numbers = Arrays.asList(1, 4, null, 2);
		int sum=numbers.stream().filter(a->a!=null).reduce((add,item)->add+item).get();
		System.out.println("ints sum is:" +sum);
		/*它允许用户提供一个循环计算的初始值，如果Stream为空，就直接返回该值*/
		System.out.println("ints sum1 is:" + numbers.stream().filter(a->a!=null).reduce(0, (sum1, item) -> sum1 + item));
	}

	/*其他汇聚reduce*/
	private static void streamReduceCount(Object obj){
		/*第一个参数是上次函数执行的返回值;第二个参数是stream中的元素，这个函数把这两个值相加;得到的和会被赋值给下次执行这个函数的第一个参数*/
		/*第一次执行的时候第一个参数的值是Stream的第一个元素，第二个参数是Stream的第二个元素*/
		List<Integer> numbers = Arrays.asList(1, 4, null, 2);
		long count=numbers.stream().filter(a->a!=null).count();
		System.out.println("count:" +count);

		/*是不是Stream中的所有元素都满足给定的匹配条件*/
		Boolean flag = numbers.stream().allMatch(a->a!=null);
		System.out.println("allMatch:" +flag);

		/*Stream中是否存在任何一个元素满足匹配条件*/
		Boolean flag1 = numbers.stream().anyMatch(a->a!=null);
		System.out.println("anyMatch:" +flag1);

		/*回Stream中的第一个元素，如果Stream为空，返回空Optional*/
		Optional optional = numbers.stream().findFirst();
		System.out.println("findFirst:" +optional.get());

		/*是不是Stream中的所有元素都不满足给定的匹配条件*/
		Boolean flag2 = numbers.stream().filter(a->a!=null).noneMatch(a->a>100);
		System.out.println("noneMatch:" +flag2);

		/*max和min使用给定的比较器（Operator），返回Stream中的最大|最小值*/
		Optional mix = numbers.stream().filter(a->a!=null).min(((o1, o2) -> o1.compareTo(o2)));
		System.out.println("mix:" +mix.get());
		numbers.stream().filter(a->a!=null).max(((o1, o2) -> o1.compareTo(o2))).ifPresent(System.out::println);

	}


}
