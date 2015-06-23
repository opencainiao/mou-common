package org.mou.common;

import com.google.gson.Gson;

public class JsonUtil {

	private static Gson gson = new Gson();

	public static Gson getGson() {
		return JsonUtil.gson;
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
	 * 取格式化的对象的json字符串
	 * 
	 * @param o
	 * @return
	 */
	public static String getFormatJsonStr(Object o) {
		String rtnStr = toJsonStr(o);
		return rtnStr.replaceAll(",", "\n").replaceAll("},", "\n");
	}
}
