package com.dark.jdk7NewCharacteristic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 	JDK 1.7 新增特性
 * 	java编译器会执行更加细致的检查，java编译器会检查throws语句抛出的异常的实际类型。
 * @author idiot
 * @version 1.0
 * @date 2016年2月4日 下午12:06:06
 */
public class EnhanceThrowsSegement {
	/**
	 * JDK1.7之前
	 * 	在catch代码块里面对于{@link FileNotFoundException}进行了异常的二次封装。在声明抛出异常的时候就必须是对应的异常或者其父类。
	 *  
	 */
	public void oldDemo() throws Exception{
		try {
			FileInputStream fs = new FileInputStream("c://a.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}
	/**
	 *  JDK1.7之后
	 *  在catch代码块里面用Exception去接收{@link FileNotFoundException}并抛出。在声明抛出异常的时候可以是捕获的的异常的实际类型。
	 *  
	 */
	public void newDemo() throws FileNotFoundException {
		try {
			FileInputStream fs = new FileInputStream("c://a.txt");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	/**
	 * mian method
	 */
	public static void main(String[] args) {
		
	}
}
