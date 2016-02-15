package com.dark.jdk7NewCharacteristic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 	JDK 1.7 ��������
 * 	java��������ִ�и���ϸ�µļ�飬java����������throws����׳����쳣��ʵ�����͡�
 * @author idiot
 * @version 1.0
 * @date 2016��2��4�� ����12:06:06
 */
public class EnhanceThrowsSegement {
	/**
	 * JDK1.7֮ǰ
	 * 	��catch������������{@link FileNotFoundException}�������쳣�Ķ��η�װ���������׳��쳣��ʱ��ͱ����Ƕ�Ӧ���쳣�����丸�ࡣ
	 *  
	 */
	public void oldDemo() throws Exception{
		try {
			FileInputStream fs = new FileInputStream("c://a.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}
	/**
	 *  JDK1.7֮��
	 *  ��catch�����������Exceptionȥ����{@link FileNotFoundException}���׳����������׳��쳣��ʱ������ǲ���ĵ��쳣��ʵ�����͡�
	 *  
	 */
	public void newDemo() throws FileNotFoundException {
		try {
			FileInputStream fs = new FileInputStream("c://a.txt");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	/**
	 * mian method
	 */
	public static void main(String[] args) {
		
	}
}
