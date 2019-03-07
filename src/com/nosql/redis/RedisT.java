package com.nosql.redis;

import redis.clients.jedis.Jedis;

public class RedisT {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.30.128",6381);
		jedis.auth("xuekui123");
//		jedis.set("name", "薛奎");
		System.out.println(jedis.get("name"));
		
		
	}
}
