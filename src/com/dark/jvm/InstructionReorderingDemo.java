package com.dark.jvm;

import java.util.concurrent.CountDownLatch;

/**
 * 指令重排-破坏线程间的有序性
 * 
 * @author darkidiot
 */
public class InstructionReorderingDemo {
	int a = 0;
	boolean flag = false;

	public void writer() {
		a = 1;
		flag = true;
	}

	public void reader() {
		Integer i = null;
		if (flag) {
			i = a + 1;
		}
		System.out.println(i + "");
	}
	/**
	 * 线程A首先执行writer()方法,
	 * 线程B线程接着执行reader()方法,
	 * 线程B在int i=a+1 是不一定能看到a已经被赋值为1,
	 * 因为在writer中，两句话顺序可能打乱
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(2);
		InstructionReorderingDemo instructionReorderingDemo = new InstructionReorderingDemo();
		new Thread(new Runnable() {
			@Override
			public void run() {
				instructionReorderingDemo.writer();
				countDownLatch.countDown();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				instructionReorderingDemo.reader();
				countDownLatch.countDown();
			}
		}).start();
		countDownLatch.await();
	}
}