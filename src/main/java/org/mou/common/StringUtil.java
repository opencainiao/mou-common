package org.mou.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class StringUtil {

	/****
	 * 获取异常堆栈信息
	 * 
	 * @param e
	 * @return
	 */
	public static String getStackTrace(Exception e) {
		StringWriter writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer, true));
		try {
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String rst = writer.toString();
		return rst;
	}

	/****
	 * 获取异常堆栈信息
	 * 
	 * @param e
	 * @return
	 */
	public static String getStackTrace(Throwable e) {
		StringWriter writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer, true));
		try {
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String rst = writer.toString();
		// rst= rst.replace(System.getProperty("line.separator"),"</br>");
		return rst;
	}

	/****
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return (!(str != null && str.trim().length() > 0));
	}

	/****
	 * 判断字符串是否不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return (str != null && str.trim().length() > 0);
	}

	/***
	 * 数字不足位数左补字符
	 * 
	 * @param str
	 * 
	 * @param strLength
	 */
	public static String addCharL(Integer num, int strLength, String character) {
		return addCharL(String.valueOf(num), strLength, character);
	}

	/***
	 * 数字不足位数左补字符
	 * 
	 * @param str
	 * 
	 * @param strLength
	 */
	public static String addCharR(Integer num, int strLength, String character) {
		return addCharR(String.valueOf(num), strLength, character);
	}

	/***
	 * 数字不足位数左补字符
	 * 
	 * @param str
	 * 
	 * @param strLength
	 */
	public static String addCharL(String str, int strLength, String character) {

		int strLen = str.length();
		int numsToAdd = strLength - strLen;

		if (numsToAdd > 0) {
			StringBuffer sb = new StringBuffer();

			for (int i = 0; i < numsToAdd; ++i) {
				sb.append(character);
			}

			sb.append(str);

			return sb.toString();
		}

		return str;
	}

	/***
	 * 数字不足位数右补字符
	 * 
	 * @param str
	 * 
	 * @param strLength
	 */
	public static String addCharR(String str, int strLength, String character) {

		int strLen = str.length();
		int numsToAdd = strLength - strLen;

		if (numsToAdd > 0) {
			StringBuffer sb = new StringBuffer();

			sb.append(str);

			for (int i = 0; i < numsToAdd; ++i) {
				sb.append(character);
			}

			return sb.toString();
		}

		return str;
	}

	/****
	 * 判断part是不是str的一部分(忽略大小写)
	 * 
	 * @param str
	 * @param part
	 * @return
	 */
	public static boolean isContainIngoreCase(String str, String part) {
		if (str == null || part == null) {
			return false;
		}

		return (str.toLowerCase().indexOf(part.toLowerCase()) >= 0);
	}

	/****
	 * 判断part是不是str的一部分
	 * 
	 * @param str
	 * @param part
	 * @return
	 */
	public static boolean isContain(String str, String part) {
		if (str == null || part == null) {
			return false;
		}

		return (str.indexOf(part) >= 0);
	}

	/****
	 * HTML特殊字符串替换为编码 <br>
	 * 
	 * &#47; -- 左斜杠 /
	 * 
	 * &#92; -- 右斜杠 \
	 * 
	 * &#58; -- 冒号:
	 * 
	 * &#34; 和 &quot; -- 双引号 "
	 * 
	 * &apos; -- 单引号 '
	 * 
	 * &#38; -- 与 &
	 * 
	 * &#123; -- {
	 * 
	 * &#125; --}
	 * 
	 * &#124;--|
	 * 
	 * @param str
	 * @return
	 */
	public static String escapeHtmlToCode(String str) {

		// str = str.replaceAll("\\\\", "\\\\\\\\");
		str = str.replaceAll("\\\\", "&#92;");
		str = str.replaceAll("/", "&#47;");
		str = str.replaceAll(":", "&#58;");
		str = str.replaceAll("\\{", "&#123;");
		str = str.replaceAll("\\}", "&#125;");
		str = str.replaceAll("\\|", "&#124;");
		str = str.replaceAll("'", "&apos;");

		return str;
	}

	public static void main(String[] args) {
		System.out.println(addCharL(1, 5, "0"));
		System.out.println(addCharR(1, 3, "A"));
		System.out.println(addCharL("mm", 5, "0"));
		System.out.println(addCharR("n", 3, "A"));
	}
}
