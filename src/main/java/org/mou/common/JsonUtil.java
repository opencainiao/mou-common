package org.mou.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

	private static Gson gson = new Gson();
	private static GsonBuilder gsonBuilder = new GsonBuilder();

	public static Gson getGson() {
		return JsonUtil.gson;
	}
	
	public static GsonBuilder getGsonBuilder(){
		return gsonBuilder;
	}

	/****
	 * 解析json字符串为类对象
	 * 
	 * @param json
	 * @param classOfT
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> classOfT) {
		return getGson().fromJson(json, classOfT);
	}

	/****
	 * 把对象转换成JSON格式
	 * 
	 * @param o
	 * @return
	 */
	public static String toJsonStr(Object o) {
		return getGson().toJson(o);
	}

	/****
	 * 获取格式化的json字符串表示
	 * 
	 * @param o
	 * @return
	 */
	public static String getPrettyJsonStr(Object o) {
		Gson prettyGson = getGsonBuilder().setPrettyPrinting().create();
		return prettyGson.toJson(o);
	}

	/****
	 * 获取包含null值的属性
	 * 
	 * @param o
	 * @return
	 */
	public static String getIncludeNullGsonStr(Object o) {
		Gson includeNullsGson = getGsonBuilder().serializeNulls().create();
		return includeNullsGson.toJson(o);
	}
}
