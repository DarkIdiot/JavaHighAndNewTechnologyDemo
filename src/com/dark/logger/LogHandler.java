package com.dark.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * 日志的存储，转发。
 * 
 * @author idiot
 * @version 1.0
 * @date 2016年2月4日 下午9:11:49
 */
public class LogHandler {
	private Logger log = Logger.getLogger(this.getClass().getName());

	public LogHandler() {
		// 设置日志输出的handler
		/**
		 * 常见的日志输出有3种handler {@link FileHandler}、{@link java.util.logging.ConsoleHandler}、{@link java.util.logging.SocketHandler}
		 */
		try {
			FileHandler fileHandler = new FileHandler("log.log", true);
			//设置输出格式
			/**
			 * 常见的格式有2中Formatter {@link SimpleFormatter}、{@link XMLFormatter}
			 */
			fileHandler.setFormatter(new SimpleFormatter());
			log.addHandler(fileHandler);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void LoggerHandlerDemo() {
		// 当前日志的级别默认为INFO
		log.severe("Logger sever ");
		log.warning("Logger warning ");
		log.info("Logger info ");
		log.config("Logger config ");
		log.fine("Logger fine ");
		log.finer("Logger finer ");
		log.finest("Logger finest");

	}

	public static void main(String[] args) {
		LogHandler lh = new LogHandler();
		lh.LoggerHandlerDemo();
	}
}
