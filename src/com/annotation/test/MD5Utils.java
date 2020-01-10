package com.annotation.test;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * md5 工具类
 * 
 * @author xianhai
 * @date 2018年3月30日
 */
public abstract class MD5Utils {
	
	
	private static final Logger logger = LoggerFactory.getLogger(MD5Utils.class);
	/**
	 * 对特定字符串进行md5加密，可以添加盐值
	 * @param source 要加密的字符串
	 * @param salt 添加的盐值
	 * @return
	 */
	public static String encrypt(String source,String salt){
		//检查加密源是否存在
		if(source == null){
			return null;
		}
		//添加盐值
		if(salt != null){
			source += salt;
		}
		try {
			MessageDigest md = MessageDigest.getInstance(ENCRYPT_TYPE);
			md.reset();
			md.update(source.getBytes("UTF-8"));
			byte[] byteArray = md.digest();
			StringBuffer rs = new StringBuffer();
			for (int i = 0; i < byteArray.length; i++) {
				if (Integer.toHexString(0xFF & byteArray[i]).length() == 1){
					 rs.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
				}
				else{
					rs.append(Integer.toHexString(0xFF & byteArray[i]));
				}
			}
			return rs.toString();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return source;
		}
	}

	/**
	 * 对特定字符串进行md5加密
	 * @param source 要加密的字符串
	 * @return 结果
	 */
	public static String encrypt(String source){
		return encrypt(source, null);
	}
	
	/**
	 * 密码加密，模拟spring security 加密方式
	 * @param source
	 * @param salt
	 * @return
	 */
	public static String securityPassword(String source,Object salt){
       return encrypt(mergePasswordAndSalt(source, salt, false));
	}
	
	/**
	 * 合并要加密的密码和盐值
	 * @param password
	 * @param salt
	 * @param strict
	 * @return
	 */
	private static String mergePasswordAndSalt(String password, Object salt, boolean strict) {
        if (password == null) {
            password = "";
        }
        if (strict && (salt != null)) {
            if ((salt.toString().lastIndexOf("{") != -1) || (salt.toString().lastIndexOf("}") != -1)) {
                throw new IllegalArgumentException("Cannot use { or } in salt.toString()");
            }
        }

        if ((salt == null) || "".equals(salt)) {
            return password;
        } else {
            return password + "{" + salt.toString() + "}";
        }
    }
	
	private static final String ENCRYPT_TYPE = "MD5";

	
}
