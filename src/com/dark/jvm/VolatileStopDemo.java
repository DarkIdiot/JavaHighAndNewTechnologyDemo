package com.dark.jvm;

/**
 * volatile boolean 终止线程
 * 
 * @author darkidiot
 */
public class VolatileStopDemo extends Thread {
	/**
	 * 可能低版本的JDK存在此缺陷(JDK8测试不存在这类情况)
	 * 需要指定volatile关键字,线程工作内存已经载入isStop=false变量的值，
	 * 由其他线程修改isStop=true的值写入主存，但是其他已载入该值得线程(工作内存)是不可见的，所以线程不会终止。
	 */
	public volatile boolean isStop = false;
	
	@Override
	public void run() {
		int i = 0;
		while (!isStop) {
			i++;
			System.out.println("thread is running now. and the Number is " + i);
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("stop Thread now.");
	}

	/**
	 * -server
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		VolatileStopDemo runnable = new VolatileStopDemo();
		runnable.start();
		Thread.sleep(1000);
		runnable.isStop = true;
		Thread.sleep(1000);
	}
}
