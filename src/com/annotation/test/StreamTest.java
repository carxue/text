package com.annotation.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

public class StreamTest {
	public static void main(String[] args) {
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
		

		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		 
		boolean allMatch = list.stream().allMatch(e -> e > 10); //false
		boolean noneMatch = list.stream().noneMatch(e -> e > 10); //true
		boolean anyMatch = list.stream().anyMatch(e -> e > 4);  //true
		 
		Integer findFirst = list.stream().findFirst().get(); //1
		Integer findAny = list.stream().findAny().get(); //1
		 
		long count1 = list.stream().count(); //5
		Integer max = list.stream().max(Integer::compareTo).get(); //5
		System.out.println(findFirst+":::"+findFirst+":::"+count1+":::"+max);
		
		

		Student s1 = new Student("aa", 10);
		Student s2 = new Student("bb", 20);
		Student s3 = new Student("cc", 10);
		Student s4 = new Student("dd", 8);
		Student s5 = new Student("ee", 15);
		List<Student> slist = Arrays.asList(s1, s2, s3, s4, s5);

		List<Integer> ageList = slist.stream().map(Student::getAge).collect(Collectors.toList());
		ageList.forEach(System.out::println);
		Map<String,Integer> stuMap = slist.stream().collect(Collectors.toMap(Student::getName,Student::getAge));
		stuMap.forEach((k,v)->{
			System.out.println(k+":"+v);
		});
		System.out.println("-------------------------");
		Set<Integer> ageSet = slist.stream().map(Student::getAge).filter(age->age>10).sorted().collect(Collectors.toSet());
		ageSet.forEach(System.out::println);

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
