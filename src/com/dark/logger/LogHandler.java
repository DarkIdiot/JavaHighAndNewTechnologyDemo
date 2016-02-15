package com.dark.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * ��־�Ĵ洢��ת����
 * 
 * @author idiot
 * @version 1.0
 * @date 2016��2��4�� ����9:11:49
 */
public class LogHandler {
	private Logger log = Logger.getLogger(this.getClass().getName());

	public LogHandler() {
		// ������־�����handler
		/**
		 * ��������־�����3��handler {@link FileHandler}��{@link java.util.logging.ConsoleHandler}��{@link java.util.logging.SocketHandler}
		 */
		try {
			FileHandler fileHandler = new FileHandler("log.log", true);
			//���������ʽ
			/**
			 * �����ĸ�ʽ��2��Formatter {@link SimpleFormatter}��{@link XMLFormatter}
			 */
			fileHandler.setFormatter(new SimpleFormatter());
			log.addHandler(fileHandler);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void LoggerHandlerDemo() {
		// ��ǰ��־�ļ���Ĭ��ΪINFO
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
