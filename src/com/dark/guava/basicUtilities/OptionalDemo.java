package com.dark.guava.basicUtilities;

import com.google.common.base.Optional;

/**
 * 使用和避免null
 * 
 * @author Darkidiot
 * @version 1.0
 * @date 2016年10月23日
 */
public class OptionalDemo {
	/**
	 * 鉴于这些原因，很多Guava工具类对Null值都采用快速失败操作，除非工具类本身提供了针对Null值的因变措施。此外，Guava还提供了很多工具类，让你更方便地用特定值替换Null值。
	 */
	public static void OptionalDemoNotNUll() {
		Optional<String> optional = Optional.of("Optional");
		boolean present = optional.isPresent();
		System.out.println("optional is present:" + present);
		String value = optional.get();
		System.out.println("optional's value is:"+value);
		String defaultValue = optional.or("Default value");
		System.out.println("optional's value is:"+defaultValue);
		
	}
	
	public static void OptionalDemoIsNUll() {
//		Optional<String> optional = Optional.of(null);
		Optional<String> optional = Optional.absent();
		boolean present = optional.isPresent();
		System.out.println("optional is present:" + present);
//		String value = optional.get();
//		System.out.println("optional's value is:"+value);
		String defaultValue = optional.or("Default value");
		System.out.println("optional's value is:"+defaultValue);
		
	}
	
	public static void main(String[] args) {
//		OptionalDemoNotNUll();
		OptionalDemoIsNUll();
//		Optional<Integer> possible = Optional.of(7);
//		Optional<Object> absent = Optional.absent();
//		Optional<String> nullableRef = Optional.<String>fromNullable("iny");
//		System.out.println(nullableRef.isPresent());
//		System.out.println(nullableRef.get());
//		System.out.println(absent.isPresent());
//		System.out.println(absent.get());
//		possible.isPresent(); // returns true
//		System.out.println(possible.isPresent());
//		System.out.println(possible.get());
	}
}
