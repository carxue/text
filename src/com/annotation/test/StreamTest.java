package com.annotation.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;

public class StreamTest {
	public static void main(String[] args) throws Exception {
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
//		// 获取对应的平方数
		List<Integer> squaresList = numbers.parallelStream().map( i -> i*i).distinct().collect(Collectors.toList());
		squaresList.forEach(System.out::println);
		
		List<String> strings = Arrays.asList("abc", "的粉色闪电", "bc", "efg", "abcd","", "jkl",null);
		// 获取空字符串的数量
		long count = strings.parallelStream().filter(string -> StringUtils.isBlank(string)).count();
		List<String> stringb = strings.parallelStream().filter(param -> StringUtils.isNotBlank(param)).sorted().collect(Collectors.toList());
		strings.forEach(System.out::println);
		System.out.println("----------------------------");
		stringb.forEach(System.out::println);
		System.out.println("----------limit------------------");
		strings.parallelStream().limit(2).forEach(System.out::println);
		System.out.println("----------Collectors------------------");
		String mergedString = strings.stream().filter(string -> StringUtils.isNotBlank(string)).collect(Collectors.joining(", "));
		System.out.println("合并字符串: " + mergedString);
		

		Student s1 = new Student("aa", 10);
		Student s2 = new Student("bb", 20);
		Student s3 = new Student("cc", 10);
		Student s4 = new Student("dd", 8);
		Student s5 = new Student("ee", 15);
//		Student s6 = new Student("aa", 15);//key重复会报错
		List<Student> slist = Arrays.asList(s1, s2, s3, s4, s5);
		// map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
		List<Integer> ageList = slist.stream().map(Student::getAge).collect(Collectors.toList());
		ageList.forEach(System.out::println);
		//flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
		List<String> strlist = Arrays.asList("a,b,c", "1,2,3");
		Stream<String> stream = strlist.stream().flatMap(element->{
			String[] chararr = element.split(",");
			Stream<String> stream1=Arrays.stream(chararr);
			return stream1;
		});
		stream.forEach(System.out::println);
		System.out.println("==============list convert to map====================");
		Map<String,Integer> stuMap = slist.stream().collect(Collectors.toMap(Student::getName,Student::getAge));
		stuMap.forEach((k,v)->{
			System.out.println(k+":"+v);
		});
		System.out.println("-------------------------");
		Set<Integer> ageSet = slist.stream().map(Student::getAge).filter(age->age>10).sorted().skip(1).collect(Collectors.toSet());
		ageSet.forEach(System.out::println);
		System.out.println("------------sort by object-------------");
		List<Student> stuList =  slist.stream().sorted((x,y)->{
			if(x.getName().equals(y.getName())) {
				return x.getAge()-y.getAge();
			}else {
				return x.getName().compareTo(y.getName());
			}
		 }).collect(Collectors.toList());
		stuList.forEach(System.out::println);
		System.out.println("-----------peek setvalue--------------");
		slist.stream().peek(a->{
			a.setAge(100);
		}).forEach(System.out::println);
		
		System.out.println("-------------------------");
		//统计总人数
		Long count2 = slist.stream().collect(Collectors.counting());
		System.out.println("总人数:"+count2);
		int maxAge = slist.stream().map(Student::getAge).collect(Collectors.maxBy(Integer::compare)).get();
		System.out.println("最大年纪:"+maxAge);
		int sumAge = slist.stream().collect(Collectors.summingInt(Student::getAge));
		System.out.println("所有人的年纪:"+sumAge);
		double avgAge = slist.stream().collect(Collectors.averagingDouble(Student::getAge));
		System.out.println("所有人的平均年纪:"+avgAge);

		// 带上以上所有方法
//		DoubleSummaryStatistics statistics = list.stream().collect(Collectors.summarizingDouble(Student::getAge));
//		System.out.println("count:" + statistics.getCount() + ",max:" + statistics.getMax() + ",sum:" + statistics.getSum() + ",average:" + statistics.getAverage());

		BufferedReader reader = new BufferedReader(new FileReader("F:\\Test_path\\test.txt"));
		Stream<String> lineStream = reader.lines();
		lineStream.forEach(System.out::println);
		reader.close();
		
		System.out.println("===============stream end===================");
		//流的终止操作
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		boolean allMatch = list.stream().allMatch(e -> e > 10); //false
		boolean noneMatch = list.stream().noneMatch(e -> e > 10); //true
		boolean anyMatch = list.stream().anyMatch(e -> e > 4);  //true
		 
		Integer findFirst = list.stream().findFirst().get(); //1
		Integer findAny = list.stream().findAny().get(); //1
		 
		long count1 = list.stream().count(); //5
		Integer max = list.stream().max(Integer::compareTo).get(); //5
		System.out.println(findFirst+":::"+findFirst+":::"+count1+":::"+max);	
		
		
//		Predicate 
		

	}

	public static void testForEach() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		// 直接打印
		list.forEach(System.out::println);
		list.forEach(Integer::floatValue);

		// 取值分别操作
		list.stream().forEach(i -> {
			System.out.println(i * 3);
		});

		// 可改变对象,只在本次调用中有效, 并不会改变原有的list
		list.stream().map((i) -> i * 3).forEach(System.out::println);

		// 不可改变元有对象
		list.forEach(i -> i = i * 3);
		list.forEach(System.out::println);

		Integer integer = list.stream().map((i) -> i = i * 3).reduce((sum, count) -> sum += count).get();
		System.out.println(integer);
	}

}
