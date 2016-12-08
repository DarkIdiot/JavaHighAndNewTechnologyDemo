package com.dark.guava.basicUtilities;

import java.util.Set;

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
		if (present) {
			String value = optional.get();
			print("optional's value is:"+value);
		}
	}
	
	/**
	 * Create
	 */
	public static void createOptional() {
		Optional<String> optional = Optional.fromNullable(null); //创建指定引用的Optional实例，若引用为null则表示缺失
//		Optional<String> optional = Optional.absent();	//创建引用缺失的Optional实例
//		Optional<String> optional = Optional.of(null);  //创建指定引用的Optional实例，若引用为null则快速失败,get the "java.lang.NullPointerException"
		boolean present = optional.isPresent();
		System.out.println("optional is present:" + present);
		String defaultValue = optional.or("Default value");
		System.out.println("optional's value is:"+ defaultValue);
		String value = optional.get();
		System.out.println("optional's value is:"+value);
		
	}
	/**
	 * normal operation 
	 */
	public static void normalOperation(){
		Optional<String> optional = Optional.fromNullable("Optional");
		boolean present = optional.isPresent();
		System.out.println("optional is present:" + present);
		String value = optional.get();
		System.out.println("optional's value is:"+value);
		String defaultValue = optional.or("Default value");
		System.out.println("optional's value is:"+defaultValue);
		String absentValue = optional.orNull();
		System.out.println("optional's value is:"+absentValue);
		Set<String> set = optional.asSet();
		System.out.println("Set of optional's value is:"+set);
		// ============ null ============ 
		Optional<String> optionalB = Optional.fromNullable(null);
		boolean presentB = optionalB.isPresent();
		System.out.println("optional is present:" + presentB);
		String defaultValueB = optionalB.or("Default value");
		System.out.println("optional's value is:"+defaultValueB);
		String absentValueB = optionalB.orNull();
		System.out.println("optional's value is:"+absentValueB);
		Set<String> setB = optionalB.asSet();
		System.out.println("Set of optional's value is:"+setB);
		String valueB = optionalB.get();
		System.out.println("optional's value is:"+valueB); // get the "java.lang.IllegalStateException"
	}
	
	// 打印输出方法
	private static void print(Object obj) {
		System.out.println(String.valueOf(obj));
	}
	
	public static void main(String[] args) {
		OptionalDemoNotNUll();
		createOptional();
		normalOperation();
	}
}
