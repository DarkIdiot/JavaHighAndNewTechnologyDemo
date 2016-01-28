package com.dark.proxy;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author idiot
 * @version 1.0
 * @date 2016��1��27�� ����9:02:54
 */
public class StaticProxyDemo {
	public static void main(String[] args) {
		IWork work = new Work();
		WorkProxy proxy = new WorkProxy(work);
		proxy.doWork("Tom");
	}
}
/**
 * ������
 */
class WorkProxy{
	/**
	 * ��Ϲ�ϵ���������������
	 */
	private IWork work;
	private Logger log = Logger.getLogger(this.getClass().getName());
	public WorkProxy(IWork work) {
		super();
		this.work = work;
	}
	public void doWork(String name){
		try {
			log.log(Level.INFO,name + " is beginning to work...");
			work.doWork(name);
			log.log(Level.INFO,name + " had worked a few minutes before... ");
		} catch (Exception e) {
			e.printStackTrace();
			log.log(Level.SEVERE, name + " has encountered to some problems...");
		}finally{
			log.log(Level.INFO, name + " is relaxing...");
		}
	}
}