package com.annotation.test;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.annotation.sign.SignUtils;
import com.annotation.sign.User1;

public class Test {
	public static void main(String[] args)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		System.out.println(new BaseMapping().callMethod("select_user", "薛奎"));
		String qOpenid = "123";
		String openid = "456";
		if (StringUtils.isBlank(qOpenid)
				|| (StringUtils.isNotBlank(qOpenid) && (qOpenid.startsWith("app_") || !openid.equals(qOpenid)))) {
			System.out.println("----");
		} else {
			System.out.println("-ssss--");
		}

		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		System.out.println(checkiSWeekend("20191109", format));
		System.out.println(checkiSWeekend("20191110", format));
		System.out.println(getFirstDayOfMonth(2019, 12, format) + ":" + getLastDayOfMonth(2019, 12, format));
		String monthOfDay = "20191110";
		int year = Integer.parseInt(monthOfDay.substring(0, 4));
		int month = Integer.parseInt(monthOfDay.substring(4, 6));
		int day = Integer.parseInt(monthOfDay.substring(6, 8));
		System.out.println(year+":"+month+":"+day);
		System.out.println(""+year+month);
	}

	private static boolean checkiSWeekend(String bDate, DateFormat format1) {
		Date bdate = null;
		try {
			bdate = format1.parse(bDate);
		} catch (ParseException e) {
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(bdate);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		} else {
			return false;
		}
	}

	public static String getFirstDayOfMonth(int year, int month, DateFormat sdf) {
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, year);
		// 设置月份
		cal.set(Calendar.MONTH, month - 1);
		// 获取某月最小天数
		int firstDay = cal.getMinimum(Calendar.DATE);
		// 设置日历中月份的最小天数
		cal.set(Calendar.DAY_OF_MONTH, firstDay);
		// 格式化日期
		return sdf.format(cal.getTime());
	}

	public static String getLastDayOfMonth(int year, int month, DateFormat sdf) {
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, year);
		// 设置月份
		cal.set(Calendar.MONTH, month - 1);
		// 获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DATE);
		// 设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		// 格式化日期
		return sdf.format(cal.getTime());
	}

	private static String handleYear(Date date) {
		DateFormat df = new SimpleDateFormat("yyyyMM");
		String today = df.format(date);
		int year = Integer.parseInt(today.substring(0, 4));
		int month = Integer.parseInt(today.substring(4, 6));
		if (month >= 8 && month <= 12) {
			return year + "";
		}
		return (year - 1) + "";
	}

	public static Date getDateByStr2(String dd) {

		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sd.parse(dd);
		} catch (ParseException e) {
			date = null;
			e.printStackTrace();
		}
		return date;
	}

	public static Date stepMonth(Date sourceDate, int month) {
		Calendar c = Calendar.getInstance();
		c.setTime(sourceDate);
		c.add(Calendar.MONTH, month);
		return c.getTime();
	}
}
