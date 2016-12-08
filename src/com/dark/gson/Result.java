package com.dark.gson;

/**
 * @author Darkidiot
 * @version 1.0
 * @date 2016年12月8日
 */
public class Result<T> {
	private int code; 
	private String message;
	private T data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
