package com.dark.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JDK�Դ���־�ļ���DEMO
 * @author idiot
 * @version 1.0
 * @date 2016��2��4�� ����8:51:08
 */
public class LogLevelDemo {
	private Logger log = Logger.getLogger(this.getClass().getName());
	/*
	 * logger��Ĭ�ϼ�����INFO����INFO���͵���־��������ʾ��
	 * Logger��Ĭ�ϼ���������jre/lib/logging.properties�ļ�����:java.util.logging.ConsoleHandler.level = INFO; 
	 */
	public void LoggerLevelDemo(){
		//��ǰ��־�ļ���Ĭ��ΪINFO
		log.severe("Logger sever ");
		log.warning("Logger warning ");
		log.info("Logger info ");
		log.config("Logger config ");
		log.fine("Logger fine ");
		log.finer("Logger finer ");
		log.finest("Logger finest");
	}
	public void LoggerLevelModifiedDemo(){
		log.setLevel(Level.WARNING);
		log.severe("Logger sever ");
		log.warning("Logger warning ");
		log.info("Logger info ");
		log.config("Logger config ");
		log.fine("Logger fine ");
		log.finer("Logger finer ");
		log.finest("Logger finest");
	}
	
	public static void main(String[] args) {
		LogLevelDemo ld = new LogLevelDemo();
		ld.LoggerLevelDemo();
		System.out.println("==================== Next Demo ====================");
		ld.LoggerLevelModifiedDemo();
	}
}
