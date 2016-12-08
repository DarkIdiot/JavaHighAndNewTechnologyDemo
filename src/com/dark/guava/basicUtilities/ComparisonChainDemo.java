package com.dark.guava.basicUtilities;

import java.util.ArrayList;
import java.util.Collections;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

/**
 * @author Darkidiot
 * @version 1.0
 * @date 2016年11月8日
 */
public class ComparisonChainDemo {

	private static void print(Object obj){
		System.out.println(String.valueOf(obj));
	}
	
	public static void main(String[] args) {
		Person p1 = new Person("kim", 3);
		Person p2 = new Person("Tom", null);
		Person p3 = new Person("Sam", 5);
		Person p4 = new Person(null, 3);
		Person p5 = new Person(null, null);
		ArrayList<Person> persons = Lists.newArrayList(p1,p2,p3,p4,p5);
		Collections.sort(persons);
		print(persons);
	}
}

class Person implements Comparable<Person>{
	String name;
	Integer age;
	public Person(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
	@Override
	public int compareTo(Person that) {
		return ComparisonChain.start()
	            .compare(this.name, that.name,Ordering.natural().nullsFirst())
	            .compare(this.age, that.age,Ordering.natural().nullsLast())
	            .result();
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
}