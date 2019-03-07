package com.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 生成图片验证码
 * @author xuekui
 *
 */
public class ImageCodeUtil {
	public static final char[] CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	public static Random random = new Random();

	/**
	 * 生成随机数
	 * @param len
	 * @return
	 */
	public static String getRandomString(int len) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < len; i++) {
			buffer.append(CHARS[random.nextInt(CHARS.length)]);
		}
		return buffer.toString();
	}

	/**
	 * 获取随机颜色
	 * @return
	 */
	public static Color getRandomColor() {
		return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}

	/**
	 * 返回某颜色的反色
	 * @param c
	 * @return
	 */
	public static Color getReverseColor(Color c) {
		return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
	}
	
	/**
	 * 返回验证码图片流
	 * @param response
	 * @param len
	 * @throws IOException
	 */
	@SuppressWarnings("restriction")
	public static void createImageVerifyCode(String verifyCode,int width,int height,String path) throws IOException{
//		int width = 80;
//		int height = 45;
		
		Color color = getRandomColor(); // 用于背景色
		Color reverse = getReverseColor(color);// 用于前景色

		BufferedImage bi = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();// 获取绘图对象
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));// 设置字体
		g.setColor(color);// 设置颜色
		g.fillRect(0, 0, width, height);// 绘制背景
		g.setColor(reverse);// 设置颜色
		g.drawString(verifyCode, 18, 20);// 绘制随机字符
		for (int i = 0, n = random.nextInt(100); i < n; i++) {
             g.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);//随机噪音点
		}
		OutputStream sos = new FileOutputStream(path);
		ImageIO.write(bi,  "JPEG ", sos);
	}
	public static void main(String[] args) throws IOException {
		createImageVerifyCode("1234",92,38,"E:/aa.jpeg");
	}
}
