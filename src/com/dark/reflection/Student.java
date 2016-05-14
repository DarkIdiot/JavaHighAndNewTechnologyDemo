package com.dark.reflection;


/**
 * Reflect Test Class
 * @author idiot
 * @version 1.0
 * @date 2016年2月4日 下午10:46:20
 */
public class Student {
	private String name;
	private String sex;
	
	private Student(String name) {
		super();
		this.name = name;
		this.sex = "male";
	}

	public Student() {
		super();
		this.name = "default";
		this.sex = "male";
	}

	public Student(String name, String sex) {
		super();
		this.name = name;
		this.sex = sex;
	}
	
	private void relaxing(long time) throws InterruptedException{
		System.out.println("I'm relaxing ...");
		Thread.sleep(time);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", sex=" + sex + "]";
	}

}
