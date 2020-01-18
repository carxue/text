package com.annotation.test;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

public class Test {
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, InterruptedException, ParseException {
		System.out.println(new BaseMapping().callMethod("select_user", "薛奎"));
		String qOpenid = "123";
		String openid = "456";
		if (StringUtils.isBlank(qOpenid)
				|| (StringUtils.isNotBlank(qOpenid) && (qOpenid.startsWith("app_") || !openid.equals(qOpenid)))) {
			System.out.println("----");
		} else {
			System.out.println("-ssss--");
		}

		List mylist = new ArrayList(); // 生成数据集，用来保存随即生成数，并用于判断
		Random rd = new Random();
		Map map = new HashMap();

		while (mylist.size() < 10) {
			int num = rd.nextInt(11);
			if (!mylist.contains(num)) {
				mylist.add(num); // 往集合里面添加数据。
			}
		}

		String region = "sync_entire_network_resources:com.kankan.module.search.util.CacheUtils";
		String key = generatorVersionKey(region, "enr_last_sync_movie_sub_time");
		System.out.println(key);

		System.out.println(new Long(1851));
	}

	public static Date getNextMonthFirstDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		return calendar.getTime();
	}

	public static Date getMonthFirstDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 0);
		return calendar.getTime();
	}

	public static boolean isTrimEmpty(String str) {
		if (str == null || str.trim().length() == 0) {
			return true;
		}
		return false;
	}

	private static String generatorVersionKey(String region, String key) {
		return md5Key(region + "::" + key);
	}

	private static <K, T> String md5Key(String key, Object... salts) {
		// 计算盐值
		String _salt = "";
		if (salts != null) {
			for (Object s : salts) {
				_salt += s;
			}
		}
		return MD5Utils.encrypt(key, _salt);
	}

	public static Date dayAdd(Date date, int days) {
		Calendar calendar = Calendar.getInstance();// new一个Calendar类,把Date放进去
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);// 实现日期加一操作,也就是明天
		return calendar.getTime();
	}

	public static String removeDomain(String path, String domain) {
		if (path.contains(domain)) {
			return path.replace(domain, "");
		}
		return path;
	}

	public static String addDomain(String path, String domain) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(path)) {
			if (path.contains(",")) {
				String[] imgs = path.split(",");
				for (String img : imgs) {
					sb.append(domain).append(img).append(",");
				}
				return sb.substring(0, sb.length() - 1);
			} else {
				return domain + path;
			}
		}
		return path;
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
