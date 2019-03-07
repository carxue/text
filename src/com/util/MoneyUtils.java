package com.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ex_kjkfb_xuek
 *
 */
public class MoneyUtils {
	public static void main(String[] args) {
         System.out.println(moneyFormate("923453543345",3));
         System.out.println(211692.43+845.33);
         System.out.println(pointLenFormat((211692.43+845.33),2));
         System.out.println(pointLenFormat(234.1,3));
	}
	
	/**
	 * @param moneys 金钱字符串 0000000.00 
	 * @param size 空格数的长度默认为3
	 * @return 转换后的金额字符串 000 000.00
	 */
	public static String moneyFormate(String moneys,Integer size) {
		if(null==size)
			size=3;
		String[] splits = moneys.split("-");
		String money=null;
		String type="";
		if(splits.length==2){
			type = "-";
			money = splits[1];
		}else
			money = splits[0];
			
		StringBuffer ret = new StringBuffer("");
		int point = money.indexOf(".");
		int lengths = 0;
		if (point > 0)
			lengths = money.substring(0, point).length();
		else
			lengths = money.length();
		int chu = lengths / size;
		int module = lengths % size;
		if (chu > 0 && module > 0)
			ret.append(money.substring(0, module) + ",");// 前几位
		else if (chu == 0 && module > 0) {
			ret.append(money.substring(0, module));// 前几位
		} else if (chu == 0 && module == 0) {
			return "0";
		}
		if (point > 0) {// 小数格式
			for (int i = 0; i < chu; i++) {
				if (i < chu - 1)
					ret.append(money.substring(module + i * size, module + (i + 1)* size)+ ",");
				else
					ret.append(money.substring(module + i * size, module + (i + 1)* size));
			}
			ret.append(money.substring(point, money.length()));// 小数点后面几位
		} else {// 整数格式化
			for (int i = 0; i < chu; i++) {
				if (i < chu - 1)
					ret.append(money.substring(module + i * size, module + (i + 1)* size)+ ",");
				else
					ret.append(money.substring(module + i * size, module + (i + 1)* size));
			}
		}
		return type+ret.toString();
	}
	
	
	/**
	 * 将double类型的数据，精确到小数点后length位；
	 * 但是小数点长度不一定为length如果是0会自动省略一些
	 * @param number 要格式的double类型参数
	 * @param length 精确的位数
	 * @return Double
	 */
	public static Double pointLenFormat(Double number,Integer length){
		Double a = Math.pow(10, length);
		Long ret = Math.round(number*a);
        return ret/a;
	}
}
