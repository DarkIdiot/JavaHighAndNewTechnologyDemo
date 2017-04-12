package com.dark.guava.basicUtilities;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * @author Darkidiot
 * @version 1.0
 * @date 2016骞�11鏈�8鏃�
 */
public class ObjectDemo {
	static ObjectDemo demo = new ObjectDemo();
	
	private static void toStringHelperDemo(){
		print(MoreObjects.toStringHelper(demo).add("extraProperties", "otherInfomation").toString());
	}
	
	private static void firstNonNullDemo(){
		String obj = null;
		String firstObj = "first object";
		String secondObj = "second object";
		obj = MoreObjects.firstNonNull(firstObj, secondObj);
		print("firstNonNull get the first param when first param is not null. And the value is \""+obj+"\"");
		firstObj = null;
		obj = MoreObjects.firstNonNull(firstObj, secondObj);
		print("firstNonNull get the secondObj when first param is null. And the value is \""+obj+"\"");
		secondObj = null;
		try{
			MoreObjects.firstNonNull(firstObj, secondObj);
		}catch (NullPointerException e) {
			print("get the " + e + " in here.");
		}
	}
	
	private static void hashCodeDemo(){
		int hashCode = Objects.hashCode(demo); //浼氬浼犲叆鐨勫瓧娈靛簭鍒楄绠楀嚭鍚堢悊鐨勩�侀『搴忔晱鎰熺殑鏁ｅ垪鍊笺��
		print("the hashcode is "+ hashCode);
	}
	
	private static void print(Object obj){
		System.out.println(String.valueOf(obj));
	}
	
	
	@Override
	public String toString() {
		return "call the toString() method.";
	}

	public static void main(String[] args) {
		toStringHelperDemo();
		print("==================== Next Demo ====================");
		firstNonNullDemo();
		print("==================== Next Demo ====================");
		hashCodeDemo();
	}
}
