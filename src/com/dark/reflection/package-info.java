/*
 * 把java类的各种成分映射成一个个的java对象。
 */
package com.dark.reflection;

/**
 * Note: 
 * {@link ClassLoad} 类加载器是负责加载类的对象。
 * ClassLoad类是一个抽象类。如果给定类的二进制名称，那么类加载器就会试图查找或生成构成类定义的数据。
 * 一般策略是将名称转换为某个文件名，然后从文件系统读取该名称的"类文件"。
 * 每个Class对象都包含一个对定义它的ClassLoader的引用。
 */
/**
 * Note:
 * java是静态语言，特点是安全。
 * 动态语言特点是灵活。多态，reflection...赋予了java语言动态的特性。
 */
