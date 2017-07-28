package com.dark.jvm;

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
	 * 因为在writer中，两句话顺序可能打乱 (JDK8很难重试)
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		InstructionReorderingDemo instructionReorderingDemo = new InstructionReorderingDemo();
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				instructionReorderingDemo.writer();
			}
		});
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				instructionReorderingDemo.reader();
			}
		});
		thread1.start();
		thread2.start();
		Thread.sleep(10);
	}
}
