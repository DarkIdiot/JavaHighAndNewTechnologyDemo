package com.dark.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class TestDemo {
	/**
	 *	main method 
	 */
	public static void main(String[] args) {
		TestDemo.customAnnotationDemo();
		System.out.println("====================== NEXT DEMO ======================");
		TestDemo.setValueDemo();
	}
	
	private static void customAnnotationDemo() {
		Class<?> clazz = null;
		Student stu = null;
		try {
			clazz = Class.forName("com.dark.annotation.Student");
			stu = (Student) clazz.newInstance();
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			e.printStackTrace();
		}
		CustomAnnotation customeAnnotation = clazz.getAnnotation(CustomAnnotation.class);
		stu.setAge(customeAnnotation.age());
		stu.setSex(customeAnnotation.sex());
		stu.setName(customeAnnotation.name());
		stu.setHobby(customeAnnotation.hobby());
		System.out.println("Student " + stu);
	}
	private static void setValueDemo(){
		Class<?> clazz = null;
		Student stu = null;
		try {
			clazz = Class.forName("com.dark.annotation.Student");
			stu = (Student) clazz.newInstance();
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			e.printStackTrace();
		}
		;
		for (Method method : clazz.getMethods()) {
			if (method.getName().contains("set") && method.isAnnotationPresent(SetValue.class)) {
				boolean accessible = method.isAccessible();
				SetValue setValue = method.getAnnotation(SetValue.class);
				if (accessible) {
					method.setAccessible(true);
				}
				try {
					if (setValue.type().getName().equalsIgnoreCase("java.lang.String")) {
						method.invoke(stu, setValue.value());
					}else if(setValue.type().getName().equalsIgnoreCase("java.lang.Integer")){
						method.invoke(stu, Integer.parseInt(setValue.value()));
					}
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
				method.setAccessible(accessible);
			}
		}
		System.out.println(stu);
	}
}
