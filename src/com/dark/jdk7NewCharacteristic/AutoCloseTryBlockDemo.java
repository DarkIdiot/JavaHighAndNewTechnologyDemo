package com.dark.jdk7NewCharacteristic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * JDK 1.7 ��������
 * 	�Զ��ر���Դ��try:
 * 	 ��try�ؼ���֮�����һ��Բ���ţ�Բ���ſ�����������ʼ��һ��������Դ���˴�����Դָ������Щ�����ڳ������ʱ������ʾ�رյ���Դ��
 * 	 (�������ݿ����ӣ��������ӣ��������������...),try����ڸ�������ʱ�Զ��ر���Щ��Դ��
 * 	ATTENTION: Ϊ�˱�֤try�����������Ĺر���Դ����Щ��Դʵ�������ʵ��{@link AutoCloseable}��{@link java.io.Closeable}�ӿڣ���ʵ��close������
 * 				{@link java.io.Closeable} �� {@link AutoCloseable}�����࣬{@link AutoCloseable}��close�����׳�Exception�����ʵ��������׳��κ��쳣;{@link java.io.Closeable}��close�����׳�IOException��ʵ����ֻ���׳�IOException��
 * 				�Զ��ر���Դ��try����൱�ڰ�������ʽ��finally��(���finally�����ڹر���Դ),������try�����Լ�û��catch��Ҳû��finally�顣
 *				�Զ��ر���Դ��try������Դ����catch���һ��finally�顣 				
 * @author idiot
 * @version 1.0
 * @date 2016��2��3�� ����10:35:58
 */
public class AutoCloseTryBlockDemo {
	public static void main(String[] args) {
		// ��ʽд��
		FileInputStream fs = null;
		try {
			 fs = new FileInputStream("D://test.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			if (fs != null) {
				try {
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//��ʽд��
		try(
			// �������������ı�����������������try-catch-finally�顣
			FileInputStream fsNew = new FileInputStream("D://test.txt"); 
			)
		{
			//DO SOMETHING
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
