package com.dark.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * @author idiot
 * @version 1.0
 * @date 2016��2��17�� ����7:56:19
 */
public class BaseReflectionDemo {
	/*
	 *	getDeclaredXXX(...) ��ȡ�������ɴ������ȷ����XXX(����private,protected,default)
	 *	getDeclaredXXXs() ��ȡ���������е�XXX(����private,protected,default)
	 *	getXXX(...)	��ȡ�ɴ������ȷ����XXX(������public)
	 *	getXXXs() ��ȡ���е�XXX(������public)
	 */

	/**
	 * get the Student instance with no parameter constructor.
	 */
	public static void getClassInstanceByDefault() {
		try {
			Class<?> clazz = Class.forName("com.dark.reflection.Student");
			Student stu = (Student) clazz.newInstance();
			stu.setName("default");
			System.out.println(stu);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * get the Student instance with specify constructor.
	 */
	public static void getClassInstanceBySpecifyConstructor() {
		try {
			Class<?> clazz = Class.forName("com.dark.reflection.Student");
			Constructor<?> constructor = clazz.getDeclaredConstructor(String.class);
			if (!constructor.isAccessible()) {
				constructor.setAccessible(true);
			}
			Student stu = (Student) constructor.newInstance("Tom");
			System.out.println(stu);
		} catch (ClassNotFoundException | NoSuchMethodException
				| SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	/**
	 * obtain the Field of Student.
	 */
	public static void obtainFieldOfStudent(){
		try {
			Class<?> clazz = Class.forName("com.dark.reflection.Student");
			Student stu = (Student) clazz.newInstance();
			for (Field field : clazz.getDeclaredFields()) {
				boolean isAccessible = field.isAccessible(); 
				if (!isAccessible) {
					field.setAccessible(true);
				}
				//��ȡfieldֵ��ʱ����Ҫ�ж��Ƿ�ɼ���
				System.out.println(field.getName()+" : "+field.get(stu));
				
				field.set(stu, field.get(stu)+"$proxy123");
				
				System.out.println("Modified Field -> "+field.getName()+" : "+field.get(stu));
				field.setAccessible(isAccessible); //��ԭ����Ȩ�ޡ�
			}
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | SecurityException
				| IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	/**
	 * obtain the Method of Student.
	 */
	public static void  obtainMethodOfStudent(){
		try {
			Class<?> clazz = Class.forName("com.dark.reflection.Student");
			Student stu = (Student) clazz.newInstance();

			Method method = clazz.getDeclaredMethod("relaxing",long.class);
			boolean isAccessible = method.isAccessible();
			if (!isAccessible) {
				method.setAccessible(true);
			}
			method.invoke(stu, 1000L);
			method.setAccessible(isAccessible);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	/**
	 * main method
	 */
	public static void main(String[] args) {
		BaseReflectionDemo.getClassInstanceByDefault();
		System.out.println("==================== Next Demo ====================");
		BaseReflectionDemo.getClassInstanceBySpecifyConstructor();
		System.out.println("==================== Next Demo ====================");
		BaseReflectionDemo.obtainFieldOfStudent();
		System.out.println("==================== Next Demo ====================");
		BaseReflectionDemo.obtainMethodOfStudent();
	}
}