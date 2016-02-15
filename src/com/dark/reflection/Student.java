package com.dark.reflection;


/**
 * @author idiot
 * @version 1.0
 * @date 2016年2月4日 下午10:46:20
 */
public class Student {
	private String name;

	public Student(String name) {
		super();
		this.name = name;
	}

	public Student() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public static void main(String args[]) throws InterruptedException {
		VolatileStopThread t = new VolatileStopThread();
		t.start();
		System.out.println(System.currentTimeMillis()/1000);
		Thread.sleep(1000);
		t.stopMe();
		System.out.println(System.currentTimeMillis()/1000);
		Thread.sleep(1000);
		System.out.println(System.currentTimeMillis()/1000);
	}
}

 class VolatileStopThread extends Thread {
	private volatile boolean stop = false;

	public void stopMe() {
		stop = true;
	}

	public void run() {
		int i = 0;
		while (!stop) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
		}
		System.out.println(i);
		System.out.println("Stop thread");
	}

	
}
