package com.dark.gson;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;

/**
 * @author Darkidiot
 * @version 1.0
 * @date 2016年12月8日
 */
public class GsonUtil {
	public static <T> Result<T> fromJsonObject(Reader reader, Class<T> clazz) {
		Gson gson = new Gson();
		Type type = new MyParameterizedType(Result.class, new Type[] { clazz }); // Result<T>
		return gson.fromJson(reader, type);
	}

	public static <T> Result<List<T>> fromJsonArray(Reader reader, Class<T> clazz) {
		Gson gson = new Gson();
		Type listType = new MyParameterizedType(List.class, new Type[] { clazz }); // 生成Result<List<T>>
																					// 中的
																					// List<T>
		Type type = new MyParameterizedType(Result.class, new Type[] { listType }); // 根据List<T>生成完整的Result<List<T>>
		return gson.fromJson(reader, type);
	}

	public static <T> Result<List<T>> fromJsonArrayCommand(Reader reader, Class<T> clazz) {
		Gson gson = new Gson();
		Type type = TypeBuilder.newInstance(Result.class)
				.beginSubType(List.class)
				.addTypeParam(clazz)
				.endSubType()
				.build();
		return gson.fromJson(reader, type);
	}

	public static <T> Result<T> fromJsonObjectCommand(Reader reader, Class<T> clazz) {
		Gson gson = new Gson();
		Type type = TypeBuilder.newInstance(Result.class)
				.addTypeParam(clazz)
				.build();
		return gson.fromJson(reader, type);
	}

}
