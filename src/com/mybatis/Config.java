package com.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	public static String getValue(String key) {
		Properties props = new Properties();
		InputStream is = null;
		try {
			is = Config.class.getResourceAsStream("/config.properties");
			props.load(is);
			return props.getProperty(key);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				is.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
