package com.dark.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author idiot
 * @version 1.0
 * @date 2016��2��18�� ����12:13:52
 */
//@Retention��Ĭ��ֵ��RetentionPolicy.CLASS,���������ע���¼��class�ļ���,������java����ʱ��jvm�����ᱣ��ע�⡣
//RetentionPolicy.RUNTIME,���������ע���¼��class�ļ���,������java����ʱ��jvm�ᱣ��ע�⣬�������ͨ�������ȡ��ע�⡣
@Retention(RetentionPolicy.RUNTIME)  
//ElementType.TYPE Ӧ�����ࡢ�ӿڡ���ö������  ElementType.ANNOTATION_TYPE  Ӧ����ע����������   ElementType.CONSTRUCTOR Ӧ���ڹ��췽������
//ElementType.FIELD Ӧ�����ֶ�����(����ö�ٳ���)  ElementType.LOCAL_VARIABLE Ӧ���ھֲ���������   ElementType.METHOD Ӧ���ڷ�������
//ElementType.PACKAGE Ӧ���ڰ�����   ElementType.PARAMETER Ӧ���ڲ�������   ElementType.TYPE_PARAMETER
@Target({ElementType.TYPE})
//�������ε�Annotation�����м̳��ԣ����ĳ����ʹ���˱�@Inherited����Annotation���������ཫ�Զ����и�ע�⡣
@Inherited
//����ָ������ԪAnnotation���ε�Annotation�ཫ��javadoc������ȡ���ĵ���
// �������Annotation��ʱʹ����@Documented����, ������ʹ�ø�Annotation���εĳ���Ԫ�ص�API�ĵ��н��������Annotation˵��. 
@Documented
public @interface CustomAnnotation {
	/**
	 * ����ֻ����String,Annotation,enumeration,primitive type.
	 */
	String name() default "darkidiot";
	
	String sex() default "male";
	
	int age() default 0;
	
	String hobby() default "coding";
	
}
