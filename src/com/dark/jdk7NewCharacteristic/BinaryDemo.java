package com.dark.jdk7NewCharacteristic;

/**
 * JDK 1.7 新增特性
 * 使用0b或者0B直接输入二进制代码
 * @author idiot
 * @version 1.0
 * @date 2016年2月3日 下午8:50:22
 */
public class BinaryDemo {
	public static void main(String[] args) {
		byte b = -0b1111111; // -127 原码
		short s = 0B111111111111111; //32767 原码
		int i = 0b11111111111111111111111111111111; // -1  补码
		long l = 0B10000000; //128 补码
		System.out.println(b);
		System.out.println(s);
		System.out.println(i);
		System.out.println(l);
	}
}
