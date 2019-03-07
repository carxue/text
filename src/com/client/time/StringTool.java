package com.client.time;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 字符串工具类
 * 
 * @author 曾迎颖
 * @date 2016年5月10日 下午3:57:19
 */

public class StringTool {
	
	/**
	 * 将null转为""
	 * 
	 * @param str
	 * @return
	 */
	public static String nullToEmpty(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj.toString();
		}
	}

	/**
	 * 将null转为""
	 * 
	 * @param str
	 * @return
	 */
	public static String nullToEmpty(String str) {
		if (str == null) {
			return "";
		} else {
			return str;
		}
	}

	/**
	 * 将""转为null
	 * @param str
	 * @return
	 */
	public static String emptyToNull(String str) {
		if (str != null && str.trim().equals("")) {
			return null;
		} else {
			return str;
		}
	}

	/**
	 * 将null转为""，并移除首尾多余空格
	 * 
	 * @param str
	 * @return
	 */
	public static String nullToTrimEmpty(String str) {
		if (str == null) {
			return "";
		} else {
			return str.trim();
		}
	}

	/**
	 * 小写转大写
	 * @param str
	 * @return
	 */
	public static String toUpperCase(String str) {
		if (str == null) {
			return null;
		}else {
			return str.trim().toUpperCase();
		}
	}

	/**
	 * 判断字符串是否为空（null、""、"null"）
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.equals("") || str.equalsIgnoreCase("null");
	}

	/**
	 * 判断对象是否为空（null、""、"null"）
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		if ("".equals(obj)) {
			return true;
		}
		if ("".equals(obj.toString().trim())) {
			return true;
		}
		if ("null".equalsIgnoreCase(obj.toString().trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否为空（null、""、"null"），判断之前先移除首尾空格
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isTrimEmpty(String str) {
		return str == null || str.trim().equals("") || str.trim().equalsIgnoreCase("null");
	}

	/**
	 * 判断字符串是否为null
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		return str == null;
	}

	/**
	 * 获取字符串字节数
	 * 
	 * @param str
	 * @return
	 */
	public static int getByteLength(String str) {
		int length = 0;
		if (str == null || str.equals("")) {
			return length;
		}
		for (int i = 0; i < str.length(); i++) {
			int ascii = Character.codePointAt(str, i);
			if (ascii >= 0 && ascii <= 255) {
				length++;
			} else {
				length += 2;
			}
		}
		return length;
	}

	/**
	 * 连接字符串转为 list
	 */
	public static List<String> strToList(String str, String split) {
		List<String> retList = new ArrayList<String>();
		String[] arr = str.split(split);
		for (String code : arr) {
			if (isTrimEmpty(code)) {
				retList.add(code);
			}
		}
		return retList;
	}

    /**
     * 将以逗号分隔的ids解析成Long类型id列表，默认不去重复
     * 
     * @param ids
     * @return
     */
    public static List<Long> idsToLongList(String ids) {
        return idsToLongList(ids, false);
    }

    /**
     * 将以逗号分隔的ids解析成Long类型id列表，默认去重复
     * 
     * @param ids
     * @return
     */
    public static List<Long> idsToLongListDistinct(String ids) {
        return idsToLongList(ids, true);
    }

    /**
     * 将以逗号分隔的ids解析成Long类型id列表
     * 
     * @param ids
     * @param distinct 是否去重复
     * @return
     */
    private static List<Long> idsToLongList(String ids, boolean distinct) {
        if (isTrimEmpty(ids)) {
            return new ArrayList<>();
        }

        List<Long> result = new ArrayList<>();

        String[] split = ids.trim().split("[,，]");
        for (String s : split) {
            if (isTrimEmpty(s)) {
                continue;
            }

            try {
                long l = Long.parseLong(s);
                if (!distinct || !result.contains(l)) {
                    result.add(l);
                }
            } catch (Exception e) {
            }
        }

        return result;
    }
    
    public static String generateVersion() {
    	Long timeStamp = System.currentTimeMillis();
    	return timeStamp+getRandomString(5);
    }
    
    public static String getRandomString(int length){
        //定义一个字符串（A-Z，a-z，0-9）即62位；
        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        Random random=new Random();  
        StringBuffer sb=new StringBuffer();
        for(int i=0; i<length; ++i){
          int number=random.nextInt(62);
          sb.append(str.charAt(number));
        }
        return sb.toString();
  }

}
