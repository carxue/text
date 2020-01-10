package com.tools.base64;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Base64Test {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		encodedText();
		encodedText1();
	}
	
	public static void encodedText() throws UnsupportedEncodingException {
		final Decoder decoder = Base64.getDecoder();
		final Encoder encoder = Base64.getEncoder();
		final String text = "字串文字";
		final byte[] textByte = text.getBytes("UTF-8");
		//编码
		final String encodedText = encoder.encodeToString(textByte);
		System.out.println(encodedText);
		//解码
		System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
	}
	
	public static void encodedText1() throws UnsupportedEncodingException {
		final Decoder decoder = Base64.getDecoder();
		final Encoder encoder = Base64.getEncoder();
		final String text = "字串文字";
		final byte[] textByte = text.getBytes("UTF-8");
		//编码
		final String encodedText = encoder.encodeToString(textByte);
		System.out.println(encodedText);
		//解码
		System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
	}
	
}
