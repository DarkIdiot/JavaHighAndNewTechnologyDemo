package com.dark.jvm;

/**
 * 栈上分配
 * ①:栈上分配的一个技术基础是进行逃逸分析。逃逸分析的目的是判断对象的作用域是否有可能逃逸出函数体。
 * ②:对于大量的零散小对象，栈上分配提供了一种很好的对象分配策略，栈上分配的速度快，并且可以有效地避免垃圾回收带来的负面的影响，但由于和堆空间相比，栈空间比较小，因此对于大对象无法也不适合在栈上进行分配。
 * @author darkidiot
 */
public class StackAllocationDemo {
	// User对象占用内存大小：16+4+8+padding/4=32
	static class User {
		private int id = 1;
		private String name = "sixtrees";
	}
	/** user的作用域是整个StackAllocationDemo Class，所以user对象是可以逃逸出函数体的。 */
	private static User user;

	public static void heapAllocation() {
		user = new User();
	}

	/** *************************** split line *************************** */
	public void stackAllocation() {
		/** user的作用域是stackAllocation函数体，所以user对象是不可能逃逸出函数体的。 */
		User user = new User();
	}

	
	/**
	 * -server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC  (开启)
	 * -server -Xmx10m -Xms10m -XX:-DoEscapeAnalysis -XX:+PrintGC  (关闭)
	 */
	public static void main(String[] args) {
		System.out.println("-----------start-----------");
		long beginTime = System.currentTimeMillis();
		for (int i = 0; i < 100000000; i++) {
			heapAllocation();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("-----------end----------- =>总共运行:" + (endTime - beginTime) + "ms");
		System.out.println("-----------start-----------");
		beginTime = System.currentTimeMillis();
		StackAllocationDemo pos = new StackAllocationDemo();
		for (int i = 0; i < 100000000; i++) {
			pos.stackAllocation();
		}
		endTime = System.currentTimeMillis();
		System.out.println("-----------end----------- =>总共运行:" + (endTime - beginTime) + "ms");
	}
}
