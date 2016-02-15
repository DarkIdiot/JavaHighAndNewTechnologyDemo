package com.dark.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author idiot
 * @version 1.0
 * @date 2016��2��4�� ����10:10:52
 */
public class LogFactoryDemo {
}

/**
 * ��̬������ʵ��
 */
class LoggerFactory{
	private static Logger logger = Logger.getLogger(LoggerFactory.class.getName());
	static{
		FileHandler fh;
		try {
			fh = new FileHandler();
			logger.addHandler(fh);
			fh.setFormatter(new SimpleFormatter());
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}
	public void log(Level level,String msg){
		logger.log(level, msg);
	}
	public void logp(Level level, String sourceClass, String sourceMethod, String msg){
		logger.logp(level, sourceClass, sourceMethod, msg);
	}
}
/**
 * ����ģʽʵ��
 */
class LoggerFactorySingleton{
	private static LoggerFactorySingleton factorySingleton = new LoggerFactorySingleton();
	private Logger logger = Logger.getLogger(LoggerFactorySingleton.class.getName());
	private LoggerFactorySingleton() {
		try {
			FileHandler fh = new FileHandler();
			logger.addHandler(fh);
			fh.setFormatter(new SimpleFormatter());
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}
	public static LoggerFactorySingleton getInstance(){
		return factorySingleton;
	}
	public void log(Level level,String msg){
		logger.log(level, msg);
	}
	public void logp(Level level, String sourceClass, String sourceMethod, String msg){
		logger.logp(level, sourceClass, sourceMethod, msg);
	}
}