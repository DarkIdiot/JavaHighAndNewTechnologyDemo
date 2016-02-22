package com.dark.annotation;

@CustomAnnotation(name="Tom",age=20,hobby="basketball")
public class Student {
	private String name;
	private int age;
	private String sex;
	private String hobby;
	public int getAge() {
		return age;
	}
	@SetValue(type=java.lang.Integer.class,value="20")
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	@SetValue(type=java.lang.String.class,value="male")
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getHobby() {
		return hobby;
	}
	@SetValue(type=java.lang.String.class,value="football")
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getName() {
		return name;
	}
	@SetValue(type=java.lang.String.class,value="DarkIdiot")
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", sex=" + sex
				+ ", hobby=" + hobby + "]";
	}
}
