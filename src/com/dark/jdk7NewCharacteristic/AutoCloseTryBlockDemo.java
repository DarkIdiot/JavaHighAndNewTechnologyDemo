package com.dark.jdk7NewCharacteristic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * JDK 1.7 新增特性
 * 	自动关闭资源的try:
 * 	 在try关键字之后紧跟一对圆括号，圆括号可以声明、初始化一个或多个资源。此处的资源指的是那些必须在程序结束时必须显示关闭的资源。
 * 	 (比如数据库连接，网络连接，磁盘输入输出流...),try语句在该语句结束时自动关闭这些资源。
 * 	ATTENTION: 为了保证try语句可以正常的关闭资源，这些资源实现类必须实现{@link AutoCloseable}或{@link java.io.Closeable}接口，并实现close方法。
 * 				{@link java.io.Closeable} 是 {@link AutoCloseable}的子类，{@link AutoCloseable}的close方法抛出Exception，因此实现类可以抛出任何异常;{@link java.io.Closeable}的close方法抛出IOException，实现类只能抛出IOException。
 * 				自动关闭资源的try语句相当于包含了隐式的finally块(这个finally块用于关闭资源),因此这个try语句可以既没有catch，也没有finally块。
 *				自动关闭资源的try语句后可以带多个catch块和一个finally块。 				
 * @author idiot
 * @version 1.0
 * @date 2016年2月3日 下午10:35:58
 */
public class AutoCloseTryBlockDemo {
	public static void main(String[] args) {
		// 老式写法
		FileInputStream fs = null;
		try {
			 fs = new FileInputStream("D://test.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			if (fs != null) {
				try {
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//新式写法
		try(
			// 在这里面声明的变量的作用域是整个try-catch-finally块。
			FileInputStream fsNew = new FileInputStream("D://test.txt"); 
			)
		{
			//DO SOMETHING
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
