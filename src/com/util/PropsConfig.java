package com.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 读取配置文件工具类
 * @author lubang713
 *
 */
public class PropsConfig{

	private static Logger logger=Logger.getLogger(PropsConfig.class);
	
	private static Properties props = new Properties();
	
	static{
		load();
	}
	
	private static synchronized void load() {
		
		try {
			if(props==null || props.isEmpty()){
			
				String configName = System.getProperty("sys.config.name");
	
				 InputStream is = PropsConfig.class.getClassLoader().getResourceAsStream(configName);
	
				props.load(is);
			}
			
		} catch (FileNotFoundException e) {
			logger.error("PropsConfig.load FileNotFoundException", e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("PropsConfig.load IOException", e);
		}
			
	}

	public  String getPropValue(String key) {
		if(props == null || props.isEmpty()){
			load();
		}
		return (String)props.get(key);
	}
	public static int getPropValue(String key,int defaultV) {
		return new PropsConfig().getPropValue(key)!=null?Integer.parseInt(new PropsConfig().getPropValue(key)):defaultV;
	}
	public static String getPropValue(String key,String defaultV) {
		return new PropsConfig().getPropValue(key)!=null?new PropsConfig().getPropValue(key):defaultV;
	}
	
	public static void main(String[] args){
		PropsConfig config = new PropsConfig();
		System.out.println(config.getPropValue("url_bill"));
		
	}
}
