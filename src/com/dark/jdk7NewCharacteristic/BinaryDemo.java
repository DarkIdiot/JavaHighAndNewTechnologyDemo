package com.dark.jdk7NewCharacteristic;

/**
 * JDK 1.7 ��������
 * ʹ��0b����0Bֱ����������ƴ���
 * @author idiot
 * @version 1.0
 * @date 2016��2��3�� ����8:50:22
 */
public class BinaryDemo {
	public static void main(String[] args) {
		byte b = -0b1111111; // -127 ԭ��
		short s = 0B111111111111111; //32767 ԭ��
		int i = 0b11111111111111111111111111111111; // -1  ����
		long l = 0B10000000; //128 ����
		System.out.println(b);
		System.out.println(s);
		System.out.println(i);
		System.out.println(l);
	}
}
