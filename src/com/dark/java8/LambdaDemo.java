package com.dark.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

/**
 * a function (or a subroutine) defined, and possibly called, without being bound to an identifier.
 * @see LambdaIntroduce
 * @author Darkidiot
 * @version 1.0
 * @date 2016年10月24日
 */
public class LambdaDemo {
	
	/**
	 * lambda表达式完美代替匿名类的使用
	 */
	public static void basicLambdaDemo() {
		List<String> names = Lists.newArrayList("hyj", "efg", "abc");
		Collections.sort(names, (o1, o2) -> o1.compareTo(o2));
		System.out.println(names);
		/** equals */
		names= Lists.newArrayList("hyj", "efg", "abc");
		Collections.sort(names, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		System.out.println(names);
	}
	
	/**
	 * lambda表达式和Stream的配合使用
	 */
	public static void lambddaAndSteamDemo(){
		List<String> names = new ArrayList<>();
		names.add("TaoBao");
		names.add("ZhiFuBao");
		List<String> lowercaseNames = names.stream().map((String name) -> {return name.toLowerCase();}).collect(Collectors.toList());
		System.out.println(lowercaseNames);
		/** equals */
		List<String> namesA = new ArrayList<>();
		namesA.add("TaoBao");
		namesA.add("ZhiFuBao");
		List<String> lowercaseNamesA = new ArrayList<>();
		for (String name : namesA) {
		  lowercaseNamesA.add(name.toLowerCase());
		}
		System.out.println(lowercaseNamesA);
		/** equals */
		List<String> namesB = new ArrayList<>();
		namesB.add("TaoBao");
		namesB.add("ZhiFuBao");
		List<String> lowercaseNamesB = FluentIterable.from(names).transform(new Function<String, String>() {
		  @Override
		  public String apply(String name) {
		    return name.toLowerCase();
		  }
		}).toList();
		System.out.println(lowercaseNamesB);
	}
	/**
	 * lambda表达式访问外部变量有一个非常重要的限制：变量不可变(只是引用不可变，而不是真正的不可变)
	 */
	public static void lambdaOutterVariables(){
		String[] array = {"a", "b", "c"};
		for(Integer i : Lists.newArrayList(1,2,3)){
		  Stream.of(array).map(item -> Strings.padEnd(item, i, '@')).forEach(System.out::println);
		}
		/** get error */
		String[] arrayA = {"a", "b", "c"};
		for(int i = 1; i<4; i++){
//		  Stream.of(arrayA).map(item -> Strings.padEnd(item, i, '@')).forEach(System.out::println);
		  // will get the error of "Local variable i defined in an enclosing scope must be final or effectively final".
		}
	}
	
	/**
	 * lambda眼中的this(相对于内部类，lambda表达式的语义就十分简单：它不会从超类（supertype）中继承任何变量名，也不会引入一个新的作用域。lambda表达式基于词法作用域，
	 * 也就是说lambda表达式函数体里面的变量和它外部环境的变量具有相同的语义（也包括lambda表达式的形式参数）。此外，'this'关键字及其引用在lambda表达式内部和外部也拥有相同的语义。)
	 */
	public static class Hello {
		Runnable r1 = () -> { System.out.println(this); };
		Runnable r2 = () -> { System.out.println(toString()); };

		public String toString() {
			return "Hello, world";
		}
	}

	public static void lambdaThisDemo(){
		new Hello().r1.run();
		new Hello().r2.run();
	}
	/**
	 * 方法引用
	 */
	static class Person {
		private String name;
		private int age;

		public int getAge() {
			return age;
		}

		public String getName() {
			return name;
		}

		public Person(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Person [name=" + name + ", age=" + age + "]";
		}
	}

	
	public static void lambdaMethodReferencesDemo(){
		List<Person> persons = Lists.newArrayList(new Person("Tom"),new Person("Kim"),new Person("Jetty"));
		Person[] people = persons.toArray(new Person[persons.size()]);
		Comparator<Person> byName = Comparator.comparing(p -> p.getName());
		Arrays.sort(people, byName);
		System.out.println(Arrays.toString(people));
		
		Consumer<Integer> b1 = System::exit;
		b1.accept(0);  //退出程序
		
		Function<String, String> upperfier = String::toUpperCase;
		System.out.println(upperfier.apply("lowerCase")); 	//转换大小
	}
	/**
	 * main method
	 */
	public static void main(String[] args) {
		basicLambdaDemo();
		System.out.println("====================== Next Demo ======================");
		lambddaAndSteamDemo();
		System.out.println("====================== Next Demo ======================");
		lambdaOutterVariables();
		System.out.println("====================== Next Demo ======================");
		lambdaThisDemo();
		System.out.println("====================== Next Demo ======================");
		lambdaMethodReferencesDemo();
	}
}
