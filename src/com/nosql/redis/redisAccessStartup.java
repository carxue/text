package com.nosql.redis;

import java.util.ArrayList;

public class redisAccessStartup {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RedisConnector redisConnector = new RedisConnector();
		
//		redisConnector.putData();
		ArrayList<String> data = redisConnector.getData();
		for (int i = 0; i < data.size(); i++) {
			System.out.println("key"+i+" is:"+data.get(i));
			
		}
		
		//redisConnector.putHyperLog();
		redisConnector.getHyperLogCount();

	}

}
