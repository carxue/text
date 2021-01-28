package com.annotation.test;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

        String region = "com.kankan.service.infant.system.web.InfantLoginController.getLoginCaptcha";
        String key = generatorVersionKey(region, "Rn.Lc.87237.1595744649");
        System.out.println("-----------------------------------------");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date buyDate = df.parse("2020-11-01");

        System.out.println(getMonthSpace("2020-09-30","2020-12-31"));
        System.out.println("=================:::"+Test.generateCharacter(8));
        System.out.println(System.currentTimeMillis());


        HashMap map1 = new HashMap();
        map1.put("xue", "0201");
        map1.put("xab", "01");
        map1.put("name", "0304");
        map1.put("a", "0101");
        Object[] key1 = map1.keySet().toArray();
        Arrays.sort(key1);

        for (int i = 0; i < key1.length; i++) {
            System.out.println(map.get(key1[i]));
        }




    }

    private static final String[] charList = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "v", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public static String generateCharacter(int len) {
        int listLength = charList.length;
        if (len > listLength) {
            len = listLength;
        }
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            sb.append(charList[random.nextInt(listLength)]);
        }
        return sb.toString();
    }

    public static int getDiffDays(String startDate, String endDate) {

        long diff = 0;

        SimpleDateFormat ft = null;

        if (startDate.indexOf("/") > 0 && endDate.indexOf("/") > 0) {

            ft = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        }

        if (startDate.indexOf("-") > 0 && endDate.indexOf("-") > 0) {

            ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        }

        try {

            Date sDate = ft.parse(startDate + " 00:00:00");

            Date eDate = ft.parse(endDate + " 00:00:00");

            diff = eDate.getTime() - sDate.getTime();

            diff = diff / 86400000;// 1000*60*60*24;

        } catch (ParseException e) {

            e.printStackTrace();

        }

        return (int) diff;


    }


    public static int getMonthSpace(String date1, String date2)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int result = 0;
        Calendar cal1 = new GregorianCalendar();
        cal1.setTime(sdf.parse(date1));
        GregorianCalendar cal2 = new GregorianCalendar();
        cal2.setTime(sdf.parse(date2));
        result = (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH);
        return result == 0 ? 0 : Math.abs(result);
    }

    public static Long WeeksFigureOuter(Date buy, Date today) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        long from = buy.getTime();
        long to = today.getTime();
        return (to - from) / (1000 * 3600 * 24 * 7);
    }

    public static Date getYearFirstDay(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    public static Date getFirstDayOnQuarterly(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        //根据月份获取所在季度
        int quarter = new Double(Math.ceil(month / 3.0D)).intValue();
        //所在季度的第一个月
        int startMonth = quarter * 3 - 2;
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        a.set(Calendar.MONTH, startMonth - 1);
        a.set(Calendar.DATE, 1);
        Date time = a.getTime();
        return time;
    }

    public static Date getFirstDayDateOfMonth(Date date) {

        final Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        final int last = cal.getActualMinimum(Calendar.DAY_OF_MONTH);

        cal.set(Calendar.DAY_OF_MONTH, last);

        return cal.getTime();

    }

    public static Date getWeekfirstday(String day) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(day));

        int weekday = cal.get(Calendar.DAY_OF_WEEK);//当前日期是周几
        if (weekday == 1) {//如果是周日,减一
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);//周一
        int dy = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dy);

        return cal.getTime();
    }


    public static int getFirstWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public static Date addByDate(Date date, int type, int value) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(type, now.get(type) + value);
        return now.getTime();
    }

    public static Date getPreMonthFirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
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
