package com.nosql.redis;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClusterTest {
	private static JedisCluster jedisCluster;

	public static void main(String[] args) {
		// 数据库链接池配置
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(200);
		config.setMaxIdle(50);
		config.setMinIdle(8);// 设置最小空闲数
		config.setMaxWaitMillis(10000);
		config.setTestOnBorrow(true);
		config.setTestOnReturn(true);
		// Idle时进行连接扫描
		config.setTestWhileIdle(true);
		// 表示idle object evitor两次扫描之间要sleep的毫秒数
		config.setTimeBetweenEvictionRunsMillis(30000);
		// 表示idle object evitor每次扫描的最多的对象数
		config.setNumTestsPerEvictionRun(10);
		// 表示一个对象至少停留在idle状态的最短时间，然后才能被idle object
		// evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
		config.setMinEvictableIdleTimeMillis(60000);
		// Redis集群的节点集合
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		jedisClusterNodes.add(new HostAndPort("192.168.32.129", 6380));
		jedisClusterNodes.add(new HostAndPort("192.168.32.129", 6381));
		jedisClusterNodes.add(new HostAndPort("192.168.32.129", 6382));
		jedisClusterNodes.add(new HostAndPort("192.168.32.129", 6383));
		jedisClusterNodes.add(new HostAndPort("192.168.32.129", 6384));
		jedisClusterNodes.add(new HostAndPort("192.168.32.129", 6385));
		JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes, 2000, 6000, 100,
				"xuekui123", config);
//		
//		jedisCluster = new JedisCluster(jedisClusterNodes, 2000, 6000,config);
//		jedisCluster.set("a", "aa");
		// 取数据
		String baby = jedisCluster.get("a");
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("name", "小雪");map.put("age", "2");
//		jedisCluster.hmset("permap", map);
		String a=jedisCluster.hmget("permap", "name").toString();
		System.out.println(a);
		System.out.println(baby);
	}
}
