package com.dark.path;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author idiot
 * @version 1.0
 * @date 2016年2月4日 下午10:29:34
 */
public class PathDemo {
	/**
	 * 相对路径
	 */
	public void relativePathDemo() {
		InputStream resourceAsStream1 = this.getClass().getResourceAsStream("/");  // ClassPath:
		InputStream resourceAsStream2 = this.getClass().getResourceAsStream("/log.log"); // ClassPath:log.log
		InputStream resourceAsStream3 = this.getClass().getResourceAsStream("log.log"); // ./log.log
		System.out.println(resourceAsStream1);
		System.out.println(resourceAsStream2);
		System.out.println(resourceAsStream3);
	}

	/**
	 * 绝对路径
	 */
	public void absolutelyPathDemo() {
		try {
			FileInputStream fileInputStream = new FileInputStream("D:/eclipse-jee-luna-R-win32-x86_64/workplace/JavaHighAndNewTechnologyDemo/src/log.log");
			System.out.println(fileInputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		PathDemo demo = new PathDemo();
		demo.absolutelyPathDemo();
		System.out.println("====================== Next Demo ======================");
		demo.relativePathDemo();
	}
}
