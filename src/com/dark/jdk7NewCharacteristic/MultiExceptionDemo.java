package com.dark.jdk7NewCharacteristic;

/**
 * JDK 1.7 新增特性
 * 	使用一个catch块捕获多种类型的异常是有如下两个需要注意的地方。
 * 	1.捕捉多个异常类型时，多个异常类型之间用'或'(|)连接;
 *  2.捕捉多个类型异常时，异常变量有隐示的final修饰，因此程序不能对该异常变量重新赋值;
 * @author idiot
 * @version 1.0
 * @date 2016年2月4日 上午11:40:45
 */
public class MultiExceptionDemo {
	public static void main(String[] args) {
		try {
			int a = Integer.parseInt(args[0]);
			int b = Integer.parseInt(args[1]);
			int c = a / b;
		} catch (NumberFormatException | IndexOutOfBoundsException | ArithmeticException e) {
			System.out.println("程序发生数组越界、数字格式异常、算数异常之一。");
			e.printStackTrace();
		}
				
	}
}
