package com.md5;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicInteger;

public class Md5 {
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(Md5.getMd5("cosco123456"));
		AtomicInteger ato = new AtomicInteger(0);
		System.out.println(ato.incrementAndGet()+":"+ato.get()+":"+ato.incrementAndGet());

	}

	public static String getMd5(String plainText)
			throws UnsupportedEncodingException {
		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes("GBK"));
			byte[] b = md.digest();
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return buf.toString();
	}
	public static String md5(){
		
		
		return null;
	}
}
