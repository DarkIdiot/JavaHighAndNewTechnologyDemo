package com.dark.guava.basicUtilities;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkPositionIndex;
import static com.google.common.base.Preconditions.checkPositionIndexes;
import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.List;

/**
 * 前置条件(建议静态导入)
 * 
 * @author Darkidiot
 * @version 1.0
 * @date 2016年11月7日
 */
public class PreconditionsDemo {

	/**
	 * 确保涉及的一个或多个参数来调用方法表达式的真相。
	 */
	private static void checkArgumentDemo() {
		try {
			// 校验表达式是否正确，并使用占位符输出错误信息
			checkArgument(1 > 2, "%s is wrong", "1 > 2");
		} catch (IllegalArgumentException e) {
			print(e.getMessage()); // 1 > 2 is wrong
		}
	}

	/**
	 * 确保索引指定一个数组，列表或尺寸大小的字符串有效的元素。
	 * 校验元素的索引值是否有效，index大于等于0小于size，在无效时显示错误描述信息。
	 */
	private static void checkElementIndexDemo() {
		try {
			checkElementIndex(10, list.size());
			// 在临界值size处产生异常
		} catch (IndexOutOfBoundsException e) {
			print(e.getMessage()); // index (10) must be less than size (10)
		}
		
		try {
			checkElementIndex(10, list.size(),"bad element index.");
			// 在临界值size处产生异常
		} catch (IndexOutOfBoundsException e) {
			print(e.getMessage()); // index (10) must be less than size (10)
		}
	}

	/**
	 * 确保对象引用作为参数传递给调用方法不为空。
	 */
	private static void checkNotNullDemo() {
		try {
			// 校验对象是否为空，并使用占位符输出错误信息
			checkNotNull(testObject(), "%s is null", "testObject()");
		} catch (NullPointerException e) {
			print(e.getMessage()); // testObject() is null
		}
	}

	/**
	 * 确保索引指定一个数组，列表或尺寸大小的字符串的有效位置。
	 * 校验元素的索引值是否有效，index大于等于0小于<b>等于</b>size，在无效时显示错误描述信息。
	 */
	private static void checkPositionIndexDemo() {
		try {
			// 校验元素索引是否有效，使用checkPositionIndex校验
			checkPositionIndex(10, list.size());
			// 在临界size处不产生异常
			print("checkPositionIndex does not throw IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			print(e.getMessage()); // checkPositionIndex does not throw
									// IndexOutOfBoundsException
		}
		
		try {
			// 校验元素索引是否有效，使用checkPositionIndex校验
			checkPositionIndex(11, list.size(),"bad element index.");
			// 在临界size处不产生异常
			print("checkPositionIndex does not throw IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			print(e.getMessage()); // checkPositionIndex does not throw
									// IndexOutOfBoundsException
		}
	}

	/**
	 * 确保开始和结束指定数组，列表或字符串大小有效的位置，并按照顺序。
	 */
	private static void checkPositionIndexesDemo() {
		// checkPositionIndexes
		try {
			// 校验是否是有效的索引区间
			checkPositionIndexes(3, 11, list.size());
		} catch (IndexOutOfBoundsException e) {
			print(e.getMessage()); // end index (11) must not be greater than
									// size (10)
		}
	}

	/**
	 * 确保涉及调用实例的状态，但不涉及任何参数来调用方法表达式的真相。
	 */
	private static void checkStateDemo() {
		// checkState
		try {
			// 校验表达式是否正确，并使用占位符输出错误信息，使用方法作为表达式
			checkState(testMethod(), "%s is wrong", "testMethod()");
		} catch (IllegalStateException e) {
			print(e.getMessage()); // testMethod() is wrong
		}
	}

	// 打印输出方法
	private static void print(Object obj) {
		System.out.println(String.valueOf(obj));
	}

	// 测试方法
	private static boolean testMethod() {
		return 1 > 2;
	}

	// 测试对象
	private static Object testObject() {
		return null;
	}

	// 初始化测试用list
	static List<Integer> list = new ArrayList<Integer>();
	static{
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
	}
	
	public static void main(String[] args) {
		checkArgumentDemo();
		print("==================== Next Demo ====================");
		checkElementIndexDemo();
		print("==================== Next Demo ====================");
		checkNotNullDemo();
		print("==================== Next Demo ====================");
		checkPositionIndexDemo();
		print("==================== Next Demo ====================");
		checkPositionIndexesDemo();
		print("==================== Next Demo ====================");
		checkStateDemo();
	}
}
