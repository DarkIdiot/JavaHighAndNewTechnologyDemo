package com.dark.jdk7NewCharacteristic;

/**
 * JDK 1.7 ��������
 * 	ʹ��һ��catch�鲶��������͵��쳣��������������Ҫע��ĵط���
 * 	1.��׽����쳣����ʱ������쳣����֮����'��'(|)����;
 *  2.��׽��������쳣ʱ���쳣��������ʾ��final���Σ���˳����ܶԸ��쳣�������¸�ֵ;
 * @author idiot
 * @version 1.0
 * @date 2016��2��4�� ����11:40:45
 */
public class MultiExceptionDemo {
	public static void main(String[] args) {
		try {
			int a = Integer.parseInt(args[0]);
			int b = Integer.parseInt(args[1]);
			int c = a / b;
		} catch (NumberFormatException | IndexOutOfBoundsException | ArithmeticException e) {
			System.out.println("����������Խ�硢���ָ�ʽ�쳣�������쳣֮һ��");
			e.printStackTrace();
		}
				
	}
}
