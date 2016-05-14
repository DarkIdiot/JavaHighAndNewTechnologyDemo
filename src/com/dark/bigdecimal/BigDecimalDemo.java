package com.dark.bigdecimal;

import java.math.BigDecimal;

/**
 * REASON：
 * float/double不能停供完全精确的计算结果。
 * 这个原理其实很简单，float/int都是32bit（也就是一共有2^32个精确值），而int的范围是-2^31 ~ 2^31-1，而Float的最大值是3.4028235e+38，远大于2^31 -1。
 * 而且，int只负责个数有限的整数，而浮点却要用来表示个数无穷的小数，显然力不从心。
 * 
 * 浮点精确值可以简单视作一个以0为中心的正态分布，绝对值越小（越接近0的地方），相邻两个精确值月密集。比如，最近的两个值可能只相差0.00000...几十个0...01，而最远的两个精确值，却差了2.028241E31。浮点的表示采用IEEE 754，大家可以参考一下。
 * 
 * 另外浮点数特别不适合用于货币计算。因为浮点型不可能精确表示0.1或者任何10的负数次幂的值。同样，如果计算的数字极大，或者精度要求很高，也不应该用浮点进行计算。
 */
public class BigDecimalDemo {
	public static void main(String[] args) {
		doubleDemo();
		errorDemo();
		BigDecimalDemo();
	}

	public static void doubleDemo() {
		System.out.println(1.03 - 0.42); // 0.6100000000000001
		System.out.println(1.00 - 9 * 0.10); // 0.09999999999999998
	}

	// 每个单价0.10元，0.20元，0.30元，0.40元……，每种比前一种多0.10元。每样买一个，1元钱能买几个，找零多少
	public static void errorDemo() {
		double funds = 1.00;
		int itemsBought = 0;
		for (double price = .10; funds >= price; price += .10) {
			funds -= price;
			itemsBought++;
		}
		System.out.println(itemsBought + " items bought.");
		System.out.println("Change: $" + funds);
	}
	/**
	 * java.math.BigDecimal与浮点不同，它可以提供精度任意（当然在硬件限制范围内）的计算结果，但是，只能进行四则运算或者基于四则运算的其他简单运算。
	 *  需要注意的是： 
	 *  1   虽然提供了double型构造函数或方法，但是仍然应使用String以提高精度
	 * 	2 BigDecimal与String，Integer等类似，为不可变对象（Immutable），计算结果需要重新赋值给变量，下面的代码，没有任何效果。
	 * 	3   对于有些可能影响精度的计算（比如除法除不尽）可能需要提供计算结果的精确度及取舍依据。当然@since 1.5，可以不再提供，但是如果无法得出精确值或者除不尽，仍会ArithmeticException。
	 * 
	 */	
	public static void BigDecimalDemo() {
		BigDecimal funds = new BigDecimal("1.00");
		int itemsBought = 0;
		for (BigDecimal price = new BigDecimal(".10"); funds.compareTo(price) >= 0; price = price
				.add(new BigDecimal(".10"))) {
			funds = funds.subtract(price);
			itemsBought++;
		}
		System.out.println(itemsBought + " items bought.");
		System.out.println("Change: $" + funds);
	}
}
