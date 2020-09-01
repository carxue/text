package com.annotation.test;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageUtils {
	private Graphics2D g = null;

	/**
	 * 导入本地图片到缓冲区
	 */
	public static BufferedImage toBufferedImage(Image image) {
		if (image instanceof BufferedImage) {
			return (BufferedImage) image;
		}
		// This code ensures that all the pixels in the image are loaded
		image = new ImageIcon(image).getImage();
		BufferedImage bimage = null;
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			int transparency = Transparency.OPAQUE;
			GraphicsDevice gs = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gs.getDefaultConfiguration();
			bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
		} catch (HeadlessException e) {
			// The system does not have a screen
		}
		if (bimage == null) {
			// Create a buffered image using the default color model
			int type = BufferedImage.TYPE_INT_RGB;
			bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
		}
		// Copy image to buffered image
		Graphics g = bimage.createGraphics();
		// Paint the image onto the buffered image
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return bimage;
	}

	public BufferedImage modifyImagetogeter(BufferedImage b, BufferedImage d, int x, int y, int rate) {

		try {
			int w = b.getWidth();
			int h = b.getHeight();

			g = d.createGraphics();
			g.drawImage(b, x, y, w / rate, h / rate, null);
			g.dispose();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return d;
	}

	/**
	 * 生成新图片到本地
	 */
	public void writeImageLocal(String newImage, BufferedImage img) {
		if (newImage != null && img != null) {
			try {
				File outputfile = new File(newImage);
				ImageIO.write(img, "jpg", outputfile);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void main(String[] args) throws IOException {

		ImageUtils tt = new ImageUtils();
//		Image src1 = Toolkit.getDefaultToolkit().getImage("F:\\Images\\small.png");
		URL url = new URL("https://static.kkstudy.cn/infantedu/qrcode/4889029b1fd8de7a44adee2ea5aca778.png");
		BufferedImage smallBImg = ImageIO.read(url);
		Image backImg = Toolkit.getDefaultToolkit().getImage("F:\\Images\\aaa.png");

//		BufferedImage d = ImageUtils.toBufferedImage(smallBI);
		BufferedImage backBImg = ImageUtils.toBufferedImage(backImg);

		tt.writeImageLocal("F:\\Images\\bbb.png", tt.modifyImagetogeter(smallBImg, backBImg, 500, 300, 4));
		// 将多张图片合在一起
		System.out.println("success");
	}

}
