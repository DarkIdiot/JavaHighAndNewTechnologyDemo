package com.dark.reflection;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.ConstantCallSite;
import java.util.Properties;

/**
 * @author idiot
 * @version 1.0
 * @date 2016年2月17日 下午3:23:19
 */
public class ClassLoaderDemo {

	/**
	 * load class by constant String embed java Code.
	 */
	public static void LoadClassDemo() throws ClassNotFoundException {
		ClassLoader cl = ClassLoader.getSystemClassLoader(); 
		/* equals to up above method
		 * ClassLoader cl = ClassLoaderDemo.class.getClassLoader();*/
		Class<?> clazz = cl.loadClass("com.dark.reflection.Student");
		System.out.println(clazz);
	}
	/**
	 * load class by default.properties
	 */
	public static void LoadClassDemoByPropertiesFile(){
		ClassLoader cl = ClassLoaderDemo.class.getClassLoader();
		InputStream is = ClassLoader.getSystemResourceAsStream("./com/dark/reflection/default.properties");
		Properties prop = new Properties();
		try {
			prop.load(is);
			Class<?> clazz = cl.loadClass(prop.getProperty("ClassName"));
			System.out.println(clazz);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 	main method 
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		ClassLoaderDemo.LoadClassDemo();
		System.out.println("====================== Next Demo ======================");
		ClassLoaderDemo.LoadClassDemoByPropertiesFile();
	}
}
