package com.dark.java8;

import java.util.Objects;

/**
 * @author Darkidiot
 * @version 1.0
 * @date 2016年11月7日
 */
public class ObjectsDemo {
	
	public static void forNullDemo(){
		
		System.out.println(Objects.isNull(null));
		System.out.println(Objects.nonNull(null));
		
		System.out.println(Objects.requireNonNull("String1"));
		String str = Objects.requireNonNull("String2","this value can't be null."); // get the "NullPointerException", with the message. 
		System.out.println(str);
		String str2 = Objects.requireNonNull("String3", ()->"this value can't be null"); // get the "NullPointerException", with the message.
		System.out.println(str2);
	}
	
	public static void toStringDemo(){
		ObjectsDemo o = new ObjectsDemo();
		System.out.println(Objects.toString(o));
		o = null;
		System.out.println(Objects.toString(o, "object is null."));
	}
	
	public static void equalsDemo(){
		ObjectsDemo o1 = new ObjectsDemo();
		ObjectsDemo o2 = new ObjectsDemo();
		System.out.println(Objects.equals(o1, o2));
		System.out.println(Objects.deepEquals(o1, o2));
		System.out.println(Objects.deepEquals("123", "123"));
	}
	
	/**
	 *	main method 
	 */
	public static void main(String[] args) {
//		forNullDemo();
//		toStringDemo();
		equalsDemo();
	}

	@Override
	public String toString() {
		return "call the toString method.";
	}
	
	
}
