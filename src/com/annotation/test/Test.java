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
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Map<String,Object> map = new HashMap<>();
		map.put("id", "94");
		String signStr1 = SignUtils.mapSortStr(map,df);
		System.out.println(SignUtils.encrypt(signStr1, "thirdedusalt", true));
		
	}
	
	
	private static String handleYear(Date date) {
		DateFormat df = new SimpleDateFormat("yyyyMM");
		String today = df.format(date);
		int year = Integer.parseInt(today.substring(0,4));
		int month = Integer.parseInt(today.substring(4,6));
		if(month>=8&&month<=12) {
			return year+"";
		}
		return (year-1)+"";
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
