package org.mou.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	/****
	 * 判断一个字符串是否全是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (StringUtil.isEmpty(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/****
	 * 判断一个字符串是否是小数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str) {

		if (StringUtil.isEmpty(str)) {
			return false;
		}

		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^\\d+\\.\\d+$");

		java.util.regex.Matcher m2 = pattern.matcher(str);
		if (m2.matches()) {
			return true;
		}

		return false;
	}

	/****
	 * 判断是否是数字或是小数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumericOrDouble(String str) {
		if (isNumeric(str) || isDouble(str)) {
			return true;
		}

		return false;
	}

	public static void main(String[] args) {
		System.out.println(addCharL(1, 5, "0"));
		System.out.println(addCharR(1, 3, "A"));
		System.out.println(addCharL("mm", 5, "0"));
		System.out.println(addCharR("n", 3, "A"));

		System.out.println("is_double------------");
		System.out.println(isDouble("123"));
		System.out.println(isDouble("123.1"));
		System.out.println(isDouble("123.10"));
		System.out.println(isDouble("123.1.0"));
		System.out.println("is_numeric------------");
		System.out.println(isNumeric("123"));
		System.out.println(isNumeric("123.1"));
		System.out.println(isNumeric("123.10"));
		System.out.println(isNumeric("123.1.0"));
		System.out.println("is_numeric_or_double------------");
		System.out.println(isNumericOrDouble("123"));
		System.out.println(isNumericOrDouble("123.1"));
		System.out.println(isNumericOrDouble("123.10"));
		System.out.println(isNumericOrDouble("123.1.0"));
	}
}
