package org.mou.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

/****
 * DateTime dateTime = new DateTime();
 * 
 * String s1 = dateTime.toString("yyyy/MM/dd hh:mm:ss.SSSa"); <br>
 * String s2 = dateTime.toString("yyyy-MM-dd HH:mm:ss"); <br>
 * String s3 = dateTime.toString("EEEE dd MMMM, yyyy HH:mm:ssa");<br>
 * String s4 = dateTime.toString("yyyy/MM/dd HH:mm ZZZZ");<br>
 * String s5 = dateTime.toString("yyyy/MM/dd HH:mm Z");
 * 
 * @author NBQ
 * 
 */
public class DateUtil {

	/**
	 * 得到当前日期
	 * 
	 * @return 当前对象的日期
	 * @exception
	 * @see
	 */
	public static String getCurdate() {
		return getCurdate("-");
	}

	/****
	 * 取日期中的年 2014-06-07-->2014
	 * 
	 * @param date
	 * @return
	 */
	public static String getYear(String date) {

		if (date == null || date.trim().length() == 0) {
			return getYear(getCurdate());
		}
		return date.substring(0, 4);
	}

	/****
	 * 取日期中的月 2014-06-07-->2014-06
	 * 
	 * @param date
	 * @return
	 */
	public static String getMonth(String date) {

		if (date == null || date.trim().length() == 0) {
			return getMonth(getCurdate());
		}
		return date.substring(0, 7);
	}

	/**
	 * 得到当前日期
	 * 
	 * @return 当前对象的日期
	 * @exception
	 * @see
	 */
	public static String getCurdate(String delimiter) {
		GregorianCalendar gc = new GregorianCalendar();
		java.util.Date date = new java.util.Date();
		gc.setTime(date);

		int year = gc.get(Calendar.YEAR); // 年
		int month = gc.get(Calendar.MONTH) + 1; // 月
		int day = gc.get(Calendar.DATE); // 日

		return toSimpleString(year, month, day, delimiter);
	}

	/**
	 * 得到当前的时间，精确到毫秒，格式为：yyyy-MM-dd hh:mm:ss:SSS(23位字符串)
	 * 
	 * @return 为一个字符串
	 * @exception
	 * @see
	 */
	public static String getCurrentTimsmp() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		Date d = new Date();
		return sdf.format(d).toString();
	}

	/**
	 * 得到当前的时间，精确到秒，格式为：yyyy-MM-dd hh:mm:ss:SSS(23位字符串)
	 * 
	 * @return 为一个字符串
	 * @exception
	 * @see
	 */
	public static String getCurrentTimsmpS() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		return sdf.format(d).toString();
	}

	/****
	 * DateTime dt = new DateTime(); //年 int year = dt.getYear(); //月 int month
	 * = dt.getMonthOfYear(); //日 int day = dt.getDayOfMonth(); //星期 int week =
	 * dt.getDayOfWeek(); //点 int hour = dt.getHourOfDay(); //分 int min =
	 * dt.getMinuteOfHour(); //秒 int sec = dt.getSecondOfMinute(); //毫秒 int msec
	 * = dt.getMillisOfSecond();
	 * 
	 * 2014-06-25-09
	 */
	public static String getHourNowStr() {
		DateTime dt = new DateTime();
		// int hour = dt.getHourOfDay();
		// return hour;
		return dt.toString("yyyy-MM-dd-HH");
	}

	/**
	 * 判断当前对象是否在日期when之后
	 * 
	 * @param when
	 *            日期对象
	 * @return 如果在日期when之后,返回true;否则，返回false
	 * @exception
	 * @see
	 */
	public final boolean isAfter(String date1, String date2, String delimiter) {
		Map<String, Integer> date1Map = parseDateString(date1, delimiter);

		int year1 = date1Map.get("year");
		int month1 = date1Map.get("month");
		int day1 = date1Map.get("day");

		Map<String, Integer> date2Map = parseDateString(date2, delimiter);

		int year2 = date2Map.get("year");
		int month2 = date2Map.get("month");
		int day2 = date2Map.get("day");

		return year1 != year2 ? year1 > year2
				: month1 != month2 ? month1 > month2
						: (day1 != day2 ? day1 > day2 : false);
	}

	/**
	 * 判断当前对象是否在日期when之前
	 * 
	 * @param when
	 *            日期对象
	 * @return 如果在日期when之前,返回true;否则，返回false
	 * @exception
	 * @see
	 */
	public final boolean ifBefore(String date1, String date2, String delimiter) {

		Map<String, Integer> date1Map = parseDateString(date1, delimiter);

		int year1 = date1Map.get("year");
		int month1 = date1Map.get("month");
		int day1 = date1Map.get("day");

		Map<String, Integer> date2Map = parseDateString(date2, delimiter);

		int year2 = date2Map.get("year");
		int month2 = date2Map.get("month");
		int day2 = date2Map.get("day");

		return year1 != year2 ? year1 < year2
				: month1 != month2 ? month1 < month2
						: (day1 != day2 ? day1 < day2 : false);
	}

	/**
	 * 得到当前days天后的日期
	 * 
	 * @param int
	 * @return 当前对象days天后的日期对象
	 * @exception
	 * @see
	 */
	public final static String getDateAfter(String date, int days,
			String delimiter) {
		Map<String, Integer> map = parseDateString(date, delimiter);

		int year = map.get("year");
		int month = map.get("month");
		int day = map.get("day");

		GregorianCalendar gc = new GregorianCalendar(year, month - 1, day);
		java.util.Date udate = gc.getTime();
		long datetime = days * (long) (24 * 3600 * 1000);
		udate = new Date(udate.getTime() + datetime);
		gc.setTime(udate);
		year = gc.get(Calendar.YEAR);
		month = gc.get(Calendar.MONTH) + 1;
		day = gc.get(Calendar.DATE);

		return toSimpleString(year, month, day, delimiter);
	}

	/**
	 * 得到当前days天前的日期
	 * 
	 * @param days
	 *            天数
	 * @return 当前对象days天前日期对象
	 * @exception
	 * @see
	 */

	public static final String getDateBefore(String date, int days,
			String delimiter) {
		Map<String, Integer> map = parseDateString(date, delimiter);

		int year = map.get("year");
		int month = map.get("month");
		int day = map.get("day");

		// System.out.println(map);

		GregorianCalendar gc = new GregorianCalendar(year, month - 1, day);
		java.util.Date udate = gc.getTime();
		long datetime = days * (long) (24 * 3600 * 1000);
		udate = new Date(udate.getTime() - datetime);
		gc.setTime(udate);
		year = gc.get(Calendar.YEAR);
		month = gc.get(Calendar.MONTH) + 1;
		day = gc.get(Calendar.DATE);

		// System.out.println(year + "=" + month + "=" + day);
		return toSimpleString(year, month, day, delimiter);
	}

	/**
	 * 得到两个日期间的天数，日期不分先后
	 * 
	 * @param begin
	 *            开始日期对象
	 * @param end
	 *            到达日期对象
	 * @return 两个日期间的天数
	 * @exception
	 * @see
	 */
	public static final int getDaysBetween(String begin, String end) {
		int bgnyear = Integer.parseInt(begin.substring(0, 3));
		int bgnmonth = Integer.parseInt(begin.substring(4, 5));
		int bgnday = Integer.parseInt(begin.substring(6, 7));

		int endyear = Integer.parseInt(end.substring(0, 3));
		int endmonth = Integer.parseInt(end.substring(4, 5));
		int endday = Integer.parseInt(end.substring(6, 7));

		GregorianCalendar gc = new GregorianCalendar(bgnyear, bgnmonth, bgnday);
		Date date1 = gc.getTime();
		long time1 = date1.getTime();

		gc = new GregorianCalendar(endyear, endmonth, endday);
		Date data2 = gc.getTime();
		long time2 = data2.getTime();

		long millis = Math.abs(time1 - time2);

		return (int) (millis / (24 * 3600 * 1000));
	}

	/**
	 * 获取输入年的月份的天数
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return 月份的天数
	 * @exception
	 * @see
	 */

	public static final int getDaysMonth(int year, int month) {
		if ((year > 2050) || (year < 1850) || (month < 1) || (month > 12)) {
			throw new IllegalArgumentException();
		}
		if (month == 2) {
			if (isLeapYear(year)) {
				return 29;
			} else {
				return 28;
			}
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		}
		return 31;
	}

	/**
	 * 判断一个字符串是否为合乎系统规定的字符串
	 * 
	 * @param strDate
	 *            字符串
	 * @return 如果与系统规定相符，则返回为true;否则为false
	 * @exception
	 * @see
	 */

	public static final boolean isAllowDate(String strDate) {
		if ((strDate == null) || (strDate.length() == 0)) {
			return false;
		}
		int year = 0;
		int month = 0;
		int day = 0;
		strDate = strDate.trim();
		String s = null;
		StringTokenizer st = null;
		if ((strDate.indexOf('-') != -1) && (strDate.indexOf('/') == -1)) {
			st = new StringTokenizer(strDate, "-", false);
		} else if ((strDate.indexOf('/') != -1) && (strDate.indexOf('-') == -1)) {
			st = new StringTokenizer(strDate, "/", false);
		}
		if (st != null && st.countTokens() == 3) {
			int j = 0;
			try {
				while (st.hasMoreTokens()) {
					s = st.nextToken();
					if (j == 0) {
						year = Integer.parseInt(s);
					} else if (j == 1) {
						month = Integer.parseInt(s);
					} else if (j == 2) {
						day = Integer.parseInt(s);
					}
				}
				j++;
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			year = Integer.parseInt(strDate.substring(0, 3));
			month = Integer.parseInt(strDate.substring(4, 5));
			day = Integer.parseInt(strDate.substring(6, 7));
		}

		return validate(year, month, day);
	}

	/****
	 * 解析日期字符串，可接受的类型为"-","/",(null or "")
	 * 
	 * @param date
	 * @param delimiter
	 * @return
	 */
	public static final Map<String, Integer> parseDateString(String date,
			String delimiter) {
		int year = 0;
		int month = 0;
		int day = 0;

		if (delimiter == null || delimiter.trim().equals("")) {
			year = Integer.parseInt(date.substring(0, 3));
			month = Integer.parseInt(date.substring(4, 5));
			day = Integer.parseInt(date.substring(6, 7));
		} else {
			StringTokenizer st = new StringTokenizer(date, "-", false);
			String s = "";
			if (st != null && st.countTokens() == 3) {
				int j = 0;
				while (st.hasMoreTokens()) {
					s = st.nextToken();

					if (j == 0) {
						year = Integer.parseInt(s);
					} else if (j == 1) {
						month = Integer.parseInt(s);
					} else if (j == 2) {
						day = Integer.parseInt(s);
					}
					j++;
				}
			}
		}

		Map<String, Integer> rtnMap = new HashMap<String, Integer>();

		rtnMap.put("year", year);
		rtnMap.put("month", month);
		rtnMap.put("day", day);

		return rtnMap;
	}

	/**
	 * 校验日期的大小必须限制在1850-1-1到2050-12-31日之间。
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param date
	 *            日
	 * @return
	 * @exception
	 * @see
	 */
	public static boolean validate(int year, int month, int day) {
		if ((year > 2050) || (year < 1850) || (month > 12) || (month < 1)
				|| (day < 0) || (day > 32)) {
			return false;
		}
		if (month == 2) {
			if (isLeapYear(year)) {
				if (day > 29) {
					return false;
				}
			} else {
				if ((day < 0) || (day > 28)) {
					return false;
				}
			}
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			if ((day < 0) || (day > 31)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 判断是否为闰年
	 * 
	 * @param year
	 *            年
	 * @return 如果是闰年，则返回true;如果是平年，则返回false
	 * @exception
	 * @see
	 */

	public static final boolean isLeapYear(int year) {
		GregorianCalendar gc = new GregorianCalendar();
		return gc.isLeapYear(year);
	}

	/**
	 * 把把日期转换为delimiter的字符串
	 * 
	 * @return delimiter形式的字符串
	 */
	public final static String toSimpleString(int year, int month, int day,
			String delimiter) {

		if (delimiter == null) {
			delimiter = "";
		}

		delimiter = delimiter.trim();

		String strMonth = Integer.toString(month);
		if (strMonth.length() == 1) {
			strMonth = "0" + strMonth;
		}
		String strDay = Integer.toString(day);
		if (strDay.length() == 1) {
			strDay = "0" + strDay;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(Integer.toString(year));
		sb.append(delimiter);
		sb.append(strMonth);
		sb.append(delimiter);
		sb.append(strDay);
		return sb.toString();
	}

	/**
	 * 两个时间字符串的时间间隔(天)
	 * 
	 * @return 为一个字符串
	 * @exception
	 * @see
	 */
	public static int getIntervalDay(String time1, String time2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d1 = sdf.parse(time1);
			Date d2 = sdf.parse(time2);

			// System.out.println("d1--" + d1);
			// System.out.println("d2--" + d2);

			return getIntervalDay(d1, d2);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return -1;
	}

	/****
	 * 取两个日期之间的日间隔（天)
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	private static int getIntervalDay(Date d1, Date d2) {

		DateTime begin = null;
		DateTime end = null;

		if (d1.after(d2)) {
			begin = new DateTime(d2);
			end = new DateTime(d1);
		} else {
			begin = new DateTime(d1);
			end = new DateTime(d2);
		}

		// 计算区间天数
		Period p = new Period(begin, end, PeriodType.days());
		int days = p.getDays();

		return days;
	}

	/**
	 * 两个时间字符串的时间间隔(分钟)
	 * 
	 * @return 为一个字符串
	 * @exception
	 * @see
	 */
	public static int getIntervalTimeStampMinute(String time1, String time2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		try {
			Date d1 = sdf.parse(time1);
			Date d2 = sdf.parse(time2);

			return getIntervalMinute(d1, d2);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return -1;
	}

	/****
	 * 取两个日期之间的时间间隔（分钟)
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	private static int getIntervalMinute(Date d1, Date d2) {

		DateTime begin = null;
		DateTime end = null;

		if (d1.after(d2)) {
			begin = new DateTime(d2);
			end = new DateTime(d1);
		} else {
			begin = new DateTime(d1);
			end = new DateTime(d2);
		}

		Period p = new Period(begin, end, PeriodType.minutes());

		return p.getMinutes();
	}

	/****
	 * 取两个日期之间的时间间隔（秒)
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	private static int getIntervalSound(Date d1, Date d2) {

		DateTime begin = null;
		DateTime end = null;

		if (d1.after(d2)) {
			begin = new DateTime(d2);
			end = new DateTime(d1);
		} else {
			begin = new DateTime(d1);
			end = new DateTime(d2);
		}

		Period p = new Period(begin, end, PeriodType.seconds());

		return p.getSeconds();
	}

	/**
	 * 两个时间字符串的时间间隔(秒)
	 * 
	 * @return 为一个字符串
	 * @exception
	 * @see
	 */
	public static int getIntervalTimeStampSecond(String time1, String time2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		try {
			Date d1 = sdf.parse(time1);
			Date d2 = sdf.parse(time2);

			return getIntervalSound(d1, d2);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return -1;
	}

	/****
	 * 取两个日期之间的时间间隔（毫秒)
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	private static int getIntervalMillis(Date d1, Date d2) {

		DateTime begin = null;
		DateTime end = null;

		if (d1.after(d2)) {
			begin = new DateTime(d2);
			end = new DateTime(d1);
		} else {
			begin = new DateTime(d1);
			end = new DateTime(d2);
		}

		Period p = new Period(begin, end, PeriodType.millis());

		return p.getMillis();
	}

	/**
	 * 两个时间字符串的时间间隔(毫秒)
	 * 
	 * @return 为一个字符串
	 * @exception
	 * @see
	 */
	public static int getIntervalTimeStampMillis(String time1, String time2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		try {
			Date d1 = sdf.parse(time1);
			Date d2 = sdf.parse(time2);

			return getIntervalMillis(d1, d2);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return -1;
	}

	/****
	 * 取num月以后的月<br>
	 * getMonthBefore(2012-05-03,1)-->2012-06<br>
	 * getMonthBefore(2012-05,1)-->2012-06
	 * 
	 * @param date
	 * @param num
	 * @return
	 */
	public static String getMonthAfter(String date, int num) {
		DateTime dt = new DateTime();

		if (date != null && date.trim().length() > 0) {
			dt = new DateTime(date);
		}

		// 1个月前
		DateTime beforeMonth = dt.plusMonths(num);

		return beforeMonth.toString("yyyy-MM");
	}

	/****
	 * 取num月以前的月<br>
	 * getMonthBefore(2012-05-03,1)-->2012-04<br>
	 * getMonthBefore(2012-05,1)-->2012-04
	 * 
	 * @param date
	 * @param num
	 * @return
	 */
	public static String getMonthBefore(String date, int num) {
		DateTime dt = new DateTime();

		if (date != null && date.trim().length() > 0) {
			dt = new DateTime(date);
		}

		System.out.println(dt);

		// 1个月前
		DateTime beforeMonth = dt.minusMonths(num);

		return beforeMonth.toString("yyyy-MM");
	}

	/****
	 * 取字符串对应日期
	 * 
	 * @param time
	 * @param style
	 * @return
	 */
	public static Date getDateFromString(String time, String style) {
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		try {
			Date d1 = sdf.parse(time);
			return d1;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void printInterval() {
		String t1 = "2012-01-02 10:10:00:030";
		String t2 = "2012-01-02 12:20:01:010";

		System.out.println("----------");

		System.out.println(getIntervalTimeStampMillis(t1, t2));

		System.out.println(getIntervalTimeStampSecond(t1, t2));

		System.out.println(getIntervalTimeStampMinute(t1, t2));
	}

	public static void main(String[] args) {

		System.out.println(DateUtil.getCurrentTimsmp());
		System.out.println(DateUtil.getCurrentTimsmpS());
		
		System.out.println(getDateFromString(DateUtil.getCurrentTimsmp(),
				"yyyy-MM-dd HH:mm:ss"));
		
		System.out.println(getDateFromString(DateUtil.getCurrentTimsmp(),
				"yyyy-MM-dd HH:mm:ss:SSS"));
		
		printInterval();

		String t1 = "2012-01-02 10:10:00:000";
		String t2 = "2012-01-02 10:50:00:000";

		System.out.println("----------");
		System.out.println(getIntervalTimeStampMinute(t1, t2));

		t2 = "2012-01-02 10:50:00:000";
		t1 = "2012-01-0"
				+ "1 10:50:00:000";

		System.out.println("----------");
		System.out.println(getIntervalTimeStampMinute(t1, t2));

		t2 = "2012-08-03 ";
		t1 = "2012-05-01 10:50:00:000";

		System.out.println("----------");
		System.out.println(getIntervalDay(t1, t2));

		System.out.println(getHourNowStr());

		System.out.println("################getMonthBefore");
		System.out.println(getMonthBefore(null, 3));
		System.out.println(getMonthBefore("2012-05", 1));

		System.out.println("################getMonthAfter");
		System.out.println(getMonthAfter(null, 3));
		System.out.println(getMonthAfter("2012-05", 7));
		
		System.out.println("===========================");
		System.out.println("结束");
	}
}
