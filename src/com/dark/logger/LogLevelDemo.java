package com.dark.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JDK自带日志的级别DEMO
 * @author idiot
 * @version 1.0
 * @date 2016年2月4日 下午8:51:08
 */
public class LogLevelDemo {
	private Logger log = Logger.getLogger(this.getClass().getName());
	/*
	 * logger的默认级别是INFO，比INFO更低的日志将不会显示。
	 * Logger的默认级别定义是在jre/lib/logging.properties文件里面:java.util.logging.ConsoleHandler.level = INFO; 
	 */
	public void LoggerLevelDemo(){
		//当前日志的级别默认为INFO
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
