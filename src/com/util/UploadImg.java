package com.util;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class UploadImg {
	
	private String fromFileStr;
	
	private String saveToFileStr;
	/**
	 * 处理后的图片文件名前缀
	 */
	private String sysimgfile;
	
	private int width;
	
	private int height;
	
	private String suffix;

	public UploadImg(String fromFileStr, String saveToFileStr,
			String sysimgfile, int width, int height, String suffix) {
		this.fromFileStr = fromFileStr;
		this.saveToFileStr = saveToFileStr;
		this.sysimgfile = sysimgfile;
		this.width = width;
		this.height = height;
		this.suffix = suffix;
	}
	
	/**
	 * 图片切割
	 * @return
	 * @throws Exception
	 */
	public boolean createThumbnai() throws Exception{
		double ratio = 0.0;
		File f = new File(fromFileStr);
		if(!f.isFile()){
			throw new Exception("不是文件");
		}
		File thF = new File(saveToFileStr,sysimgfile+"."+suffix);
		BufferedImage bi = ImageIO.read(f);
		Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);
		//BufferedImage bid = new BufferedImage(width, height, bi.SCALE_SMOOTH);//和上面一句功能一样 
		if(bi.getHeight() > width || bi.getWidth() > height){
			if(bi.getHeight() > bi.getWidth()){
				ratio = (double)width/bi.getHeight();
			}else{
				ratio = (double)height/bi.getWidth();
			}
			AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio),null);
			itemp = op.filter(bi, null);//执行转换
			ImageIO.write((BufferedImage)itemp, suffix, thF);
			return true;
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		UploadImg ui = new UploadImg("d://眨眼.gif","D:\\IMG_SAVE","eye_150_150",500,500,"gif");
		System.out.println(ui.createThumbnai());
	}

}
