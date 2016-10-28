package com.dark.java7;

/**
 * JDK 1.7 新增特性
 * 	允许switch语句的控制表达式是java.lang.String类型的变量或者表达式。不能是StringBuffer或者StringBuilder
 * @author idiot
 * @version 1.0
 * @date 2016年2月3日 下午9:43:35
 */
public class SwitchDemo {
	public static void main(String[] args) {
		String session = "夏天";
		switch (session) {
		case "春天":
			System.out.println("春天");
			break;
		case "夏天":
			System.out.println("夏天");
			break;
		case "秋天":
			System.out.println("秋天");
			break;
		case "冬天":
			System.out.println("冬天");
			break;
		default:
			break;
		}
	}
}
