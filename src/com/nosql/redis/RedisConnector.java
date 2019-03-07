package com.nosql.redis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConnector {

	private JedisCluster cluster;
	private Set<HostAndPort> clusterNodes;

	RedisConnector() {
		connectRedis();

	}

	private void connectRedis() {
		
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(300);
		config.setMaxIdle(100);
		config.setMinIdle(20);
		config.setMaxWaitMillis(6 * 1000);
		config.setTestOnBorrow(true);
		
		
		clusterNodes = new HashSet<HostAndPort>();

		clusterNodes.add(new HostAndPort("192.168.30.128", 6380));
		clusterNodes.add(new HostAndPort("192.168.30.128", 6381));
		clusterNodes.add(new HostAndPort("192.168.30.128", 6382));
		clusterNodes.add(new HostAndPort("192.168.30.128", 6383));
		clusterNodes.add(new HostAndPort("192.168.30.128", 6384));
		clusterNodes.add(new HostAndPort("192.168.30.128", 6385));

		cluster = new JedisCluster( clusterNodes, 2000,200, 100,"xuekui123",config);
		//System.out.println("hello:" + cluster.echo("hello"));
		// System.out.println("hello;" +jedis.get("name"));

	}

	public void putData() {
		int max = 10000;
		for (int i = 0; i < max; i++) {

			try {
				if (cluster == null) {
					cluster = new JedisCluster(clusterNodes);

				} else {
					String keyString = "key" + i;
					cluster.set(keyString, String.valueOf(i));
					System.out.println("put:" + keyString + "    " + "value:"
							+ i);
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

		}

	}

	public ArrayList<String> getData() {

		ArrayList<String> dataList = new ArrayList<String>();

		int max = 10000;
		for (int i = 0; i < max; i++) {

			try {
				if (cluster == null) {
					cluster = new JedisCluster(clusterNodes);
				} else {
					String keyString = "key" + i;
					dataList.add(cluster.get(keyString));
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
		return dataList;
	}
	
	public void putHyperLog() {
		
//		try {
//	 		long revalue = cluster.pfadd("user","1");
//			System.out.println(revalue);
//		} catch (Exception e1) {
//			
//			e1.printStackTrace();
//		}
		
		int max = 10000;
		for (int i = 0; i < max; i++) {

			try {
				if (cluster == null) {
					cluster = new JedisCluster(clusterNodes);
				} else {
					String keyString = "key_" + i;
					for (int j = 0; j < 10; j++) {
						long revalue = cluster.pfadd(keyString, String.valueOf(i));
						System.out.println(i+"th"+revalue);
				}
					
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
		
	}
	
	public void getHyperLogCount() {
		try {
			
			
			if (cluster == null) {
				cluster = new JedisCluster(clusterNodes);
			} else {
				for (int i = 0; i < 10000; i++) {
					long count= cluster.pfcount("key_" + i);
					System.out.println("key_"+i+": total "+count);
				}	
				
			}
		} catch (Exception e) {

			
			e.printStackTrace();
		}
		
	}
	public void  close() throws IOException {
		cluster.close();
	}
}
