package com.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


public class Tests {
	public static void main(String[] args) throws Exception {
			/*Gson gson = new Gson();
			String requestUrl = "http://127.0.0.1:8081/o2oSearch/shop/solrAjaxShops.xhtml";
			String toparams = "params=shop_name_title:*";
			Map map = HttpClientUtils.sendRequest(requestUrl, toparams,Map.class);*/
		String fromImagePath = "D:\\koala.jpg";
		String saveIamgePath = "D:\\IMG_SAVE\\changekoala.jpg";//D:\\changekoala.jpg
		File fromfile = new File(fromImagePath);
		File outfile = new File(saveIamgePath);
		InputStream fis = new FileInputStream(fromfile);
		OutputStream fos = new FileOutputStream(outfile);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		byte[] buffer = new byte[1024];
		int length = 0;
		while((length = fis.read(buffer))!=-1){
			bos.write(buffer, 0, buffer.length);
		}
		bos.close();
		fos.close();
		fis.close();
		System.out.println("复制成功！");
		
		
		
	}
}
