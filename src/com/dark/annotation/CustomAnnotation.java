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
 * @date 2016年2月18日 上午12:13:52
 */
//@Retention的默认值是RetentionPolicy.CLASS,编译器会把注解记录在class文件中,当运行java程序时，jvm并不会保留注解。
//RetentionPolicy.RUNTIME,编译器会把注解记录在class文件中,当运行java程序时，jvm会保留注解，程序可以通过反射获取该注解。
@Retention(RetentionPolicy.RUNTIME)  
//ElementType.TYPE 应用于类、接口、或枚举声明  ElementType.ANNOTATION_TYPE  应用于注释类型声明   ElementType.CONSTRUCTOR 应用于构造方法声明
//ElementType.FIELD 应用于字段声明(包括枚举常量)  ElementType.LOCAL_VARIABLE 应用于局部变量声明   ElementType.METHOD 应用于方法声明
//ElementType.PACKAGE 应用于包声明   ElementType.PARAMETER 应用于参数声明   ElementType.TYPE_PARAMETER
@Target({ElementType.TYPE})
//被它修饰的Annotation将具有继承性，如果某个类使用了被@Inherited修饰Annotation，则其子类将自动具有该注解。
@Inherited
//用于指定被该元Annotation修饰的Annotation类将被javadoc工具提取成文档。
// 如果定义Annotation类时使用了@Documented修饰, 则所有使用该Annotation修饰的程序元素的API文档中将或包含该Annotation说明. 
@Documented
public @interface CustomAnnotation {
	/**
	 * 类型只能是String,Annotation,enumeration,primitive type.
	 */
	String name() default "darkidiot";
	
	String sex() default "male";
	
	int age() default 0;
	
	String hobby() default "coding";
	
}
