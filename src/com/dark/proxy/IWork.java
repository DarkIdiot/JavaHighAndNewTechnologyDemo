package com.dark.proxy;

/**
 * @author idiot
 * @version 1.0
 * @date 2016��1��27�� ����8:48:14
 */
public interface IWork {
	public void doWork(String name) throws Exception;
}

class Work implements IWork{

	@Override
	public void doWork(String name) throws Exception{
		System.out.println(name+" is working hard....");
//		throw new Exception("boycott");
	}
}