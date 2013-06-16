package com.ceit.tool.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
	/**默认的日期格式的集合*/
	private static List<SimpleDateFormat> DATE_FORMAT_LIST = new ArrayList<SimpleDateFormat>();
	/**初始化日期格式的集合*/
	static {
		DATE_FORMAT_LIST.add(new SimpleDateFormat("yyyy-MM-dd"));
		DATE_FORMAT_LIST.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}
	
	/**日历类的实例*/
	private static Calendar CALENDAR = Calendar.getInstance();
	
//	/**按秒来获取和添加日期*/
//	public static int SECOND = Calendar.SECOND;
//	
//	/**按分钟来获取和添加日期*/
//	public static int MINUTE = Calendar.MINUTE;
//	
//	/**按小时来获取和添加日期（24小时制）*/
//	public static int HOUR = Calendar.HOUR_OF_DAY;
	
	/**按日来获取和添加日期（在一周中）*/
	public static int DAY_OF_WEEK = Calendar.DAY_OF_WEEK;
	/**按日来获取和添加日期（在一月中）*/
	public static int DAY_OF_MONTH = Calendar.DAY_OF_MONTH;
	/**按日来获取和添加日期（在一年中）*/
	public static int DAY_OF_YEAR = Calendar.DAY_OF_YEAR;
	
	/**按周来获取和添加日期（在一月中）*/
	public static int WEEK_OF_MONTH = Calendar.WEEK_OF_MONTH;
	/**按周来获取和添加日期（在一年中）*/
	public static int WEEK_OF_YEAR = Calendar.WEEK_OF_YEAR;
	
	/**按月来获取和添加日期*/
	public static int MONTH = Calendar.MONTH;
	
	/**按季度来获取和添加日期*/
	public static int SEASON = 0;
	
	/**按年来获取和添加日期*/
	public static int YEAR = Calendar.YEAR;
	
	/**
	 * 把字符串转化成日期
	 * @param dateString
	 *        日期字符串
	 * @return
	 * @throws ParseException 
	 */
	private static Date transformStringToDate(String dateString) throws ParseException {
		for(SimpleDateFormat dateFormat : DATE_FORMAT_LIST) {
			try {
				return dateFormat.parse(dateString);
			} catch (ParseException e) {
			}
		}
		throw new ParseException("没有此日期格式，无法转化！", 0);
	}
	
	/**
	 * 把日期转化成默认日期集合中的一个格式的字符串
	 * @param date
	 *        日期
	 * @return
	 */
	private static String transformDateToString(Date date)  {
		return DATE_FORMAT_LIST.get(0).format(date);
	}
	
	/**
	  * 获得默认日期格式下的当前日期的字符串(yyyy-MM-dd HH:mm:ss)
	  */
	public static String getCurrentDateString() {
		return transformDateToString(new Date());
	}
	
	/**
	 * 获得默认日期格式下的给定日期的字符串(yyyy-MM-dd HH:mm:ss)
	 * @param date
	 *        日期
	 * @return
	 */
	public static String getDateString(Date date) {
		return transformDateToString(date);
	}
	
	/**
	 * 获得所选日历类型的值
	 * @param dateString
	 *        日期字符串，格式为2012-12-12 23:23:00
	 * @param field
	 *        日历类型
	 * @return
	 */
	public static int get(String dateString, int field) {
		int time = 0;
		try {
			Date date = transformStringToDate(dateString);
			
			//进行日期的加减
			CALENDAR.setTime(date);
			
			if(field == SEASON) {
				time = CALENDAR.get(MONTH);
				if(time % 3 != 0)
					time = time / 3 + 1;
				time = time / 3;
			} else {
				time = CALENDAR.get(field);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}
	
	/**
	 * 获得所选日历类型的值
	 * @param date
	 *        日期
	 * @param field
	 *        日历类型
	 * @return
	 */
	public static int get(Date date, int field) {
		return get(transformDateToString(date), field);
	}
	
	/**
	 * 获取给定的日期进行加减一段时间后的日期
	 * @param dateString
	 *        日期字符串，格式为2012-12-12 23:23:00
	 * @param amount
	 *        数值，负数代表减
	 * @param field
	 *        日历类型
	 * @return
	 */
	public static String add(String dateString, int amount, int field) {
		String newDateString = null;
		try {
			Date date = transformStringToDate(dateString);
			
			//进行日期的加减
			CALENDAR.setTime(date);
			if(field == SEASON) {
				CALENDAR.add(MONTH, amount * 3);
			} else {
				CALENDAR.add(field, amount);
			}
			
			newDateString = transformDateToString(CALENDAR.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newDateString;
	}
	
	/**
	 * 获取给定的日期进行加减一段时间后的日期
	 * @param dateString
	 *        日期字符串，格式为2012-12-12 23:23:00
	 * @param amount
	 *        数值，负数代表减
	 * @param field
	 *        日历类型
	 * @return
	 */
	public static String add(Date date, int amount, int field) {
		return add(transformDateToString(date), amount, field);
	}
	
	
	/**
	 * 获得两个日期的时间差
	 * @param bigDateString
	 *        较大的时间的字符串
	 * @param smallDateSting
	 *        较小的时间的字符串
	 * @param field
	 *        日历类型
	 * @return
	 */
	public static int getDifference(String bigDateString, String smallDateSting, int field) {
		int diff = 0;
		try {
			Date bigDate = transformStringToDate(bigDateString);
			Date smallDate = transformStringToDate(smallDateSting);
			
			//两日期相差的天数
			diff = (int) ((bigDate.getTime() - smallDate.getTime()) / (1000 * 60 * 60 * 24));
			
			switch (field) {
				case 7:
				case 6:
				case 5:
					break;
	
				case 4:
				case 3:
					diff /= 7;
					break;
					
				case 2:
					diff /= 30;
					break;
					
				case 0:
					diff /= 90;
					break;
					
				case 1:
					diff /= 365;
					break;
					
				default:
					break;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return diff;
	}
	
	/**
	 * 获得两个日期的时间差
	 * @param bigDateString
	 *        较大的时间的字符串
	 * @param smallDateSting
	 *        较小的时间的字符串
	 * @param field
	 *        日历类型
	 * @return
	 */
	public static int getDifference(Date bigDate, Date smallDate, int field) {
		return getDifference(transformDateToString(bigDate), transformDateToString(smallDate), field);
	}
	
	public static Date getDefaultDate(){
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		Date d=new Date();
		try {
			d=sf.parse("2009-08-08");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
}

