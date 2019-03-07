package com.model.factory;

import java.util.HashMap;
import java.util.Map;

public class HumenFactory extends Factory {
	
	private static Map<String,Object> map = new HashMap<String,Object>();

	@SuppressWarnings("unchecked")
	@Override
	public  <T extends Humen> T createHuman(Class<?> clazz) {
		Humen humen = null;
		try {
			humen = (Humen) Class.forName(clazz.getName()).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T) humen;
	}
	
	/**
	 * 延迟加载
	 * @param type
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Humen> T createStaHuman(String type,Class<?> clazz) {
		Humen humen = null;
		if(map.containsKey(type))
			humen = (Humen) map.get(type);
		else{
			try {
				humen = (Humen) Class.forName(clazz.getName()).newInstance();
				map.put(type, humen);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return (T) humen;
	}

}
