package com.wyl.utils.redis.demo;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConnectionFactory {
	
	private static JedisCluster jedisCluster = null;
	
	private static String urls = "192.168.1.111:6379;wangyulin-test-host:6380;wangyulin-test-host:6381;wangyulin-test-host:6382;wangyulin-test-host:6383;wangyulin-test-host:6384";
	//192.168.1.111:6379;wangyulin-test-host:6380;wangyulin-test-host:6381;wangyulin-test-host:6382;wangyulin-test-host:6383;wangyulin-test-host:6384
	//wangyulin-test-host:30001;wangyulin-test-host:30002;wangyulin-test-host:30003;wangyulin-test-host:30004;wangyulin-test-host:30005;wangyulin-test-host:30006
	
	static {  
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        
        String[] hostAndPorts = urls.split(";");
        for(String it : hostAndPorts) {
        	String[] items = it.split(":");
        	HostAndPort one = new HostAndPort(items[0],Integer.valueOf(items[1]));
        	nodes.add(one);
        }
		
        JedisPoolConfig conf = new JedisPoolConfig();
        conf.setMaxTotal(50);
        conf.setMaxIdle(30);
        conf.setMinIdle(10);
        //conf.setTestWhileIdle(false);
        //conf.setTestOnReturn(false);
        
        int scoketTimeout = 3000;
        int maxRedirections = 5;
        
        jedisCluster = new JedisCluster(nodes);
     }
	
	public static JedisCluster getRedisClient() {
		return jedisCluster;
	}
	
	public static void main(String [] args) {
		System.out.println(jedisCluster.get("w1"));
		jedisCluster.incr("w1".getBytes());
		System.out.println(jedisCluster.get("w1"));
	}
}
