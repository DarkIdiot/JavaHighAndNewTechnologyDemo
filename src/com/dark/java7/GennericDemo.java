package com.dark.java7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JDK 1.7 新增特性
 * 	菱形语法：
 * 	1.7之后允许在构造器之后不需要带完整的泛型信息，只要给出一对(<>)即可。java可以推断尖括号里面应该是什么泛型信息。
 * 	菱形语法实质上对原有的泛型并没有什么改变，只是更好的简化了泛型编程。
 * @author idiot
 * @version 1.0
 * @date 2016年2月3日 下午9:54:15
 */
public class GennericDemo {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		Map<String, Object> map = new HashMap<String, Object>();
		//  等价于
		List<String> listOptimize = new ArrayList<>();
		Map<String, Object> mapOptimize = new HashMap<>();
	}
}
