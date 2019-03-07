package com.nosql.redis;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(100);
		config.setMaxIdle(50);
		config.setMinIdle(20);
		config.setMaxWaitMillis(6 * 1000);
		config.setTestOnBorrow(true);
		
		// Redis集群的节点集合
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		jedisClusterNodes.add(new HostAndPort("192.168.30.129", 6380));//192.168.30.128  192.168.12.104 192.168.12.105
		jedisClusterNodes.add(new HostAndPort("192.168.30.129", 6381));
		jedisClusterNodes.add(new HostAndPort("192.168.30.129", 6382));
		jedisClusterNodes.add(new HostAndPort("192.168.30.129", 6383));
		jedisClusterNodes.add(new HostAndPort("192.168.30.129", 6384));
		jedisClusterNodes.add(new HostAndPort("192.168.30.129", 6385));
		// 节点，超时时间，最多重定向次数，链接池 jedisClusterNodes, 2000, 100,config)
		JedisCluster jedisCluster = new JedisCluster( jedisClusterNodes, 2000,200, 100,"xuekui123",config);
		jedisCluster.set("xue", "薛奎");
		System.out.println(jedisCluster.get("xue"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
