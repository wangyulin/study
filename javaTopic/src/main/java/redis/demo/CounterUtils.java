package com.wyl.utils.redis.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import redis.clients.jedis.JedisCluster;

public class CounterUtils {
	
	private static JedisCluster client = RedisConnectionFactory.getRedisClient();
	
	private static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String serverNameListKey = "host_list";
	
	public static boolean exist(String key) {
		return client.exists(key.getBytes());
	}
	
	public static List<byte[]> getListFromRedis(String key) {
		List<byte[]> res = client.lrange(key.getBytes(), 0, -1);
//		System.out.println("-------------------------------");
//		for(byte[] it : res) {
//			System.out.println(new String(it));
//		}
//		System.out.println("-------------------------------");
		return res;
	}
	
	public static boolean existsOnServerList(List<byte[]> list,String target) {
		if(list != null) {
			for(byte[] it : list) {
				if(target.equals(new String(it))) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static String get(String key) {
		byte[] res = client.get(key.getBytes());
		return (res == null)? null:new String(res);
	}
	
	public static List<byte[]> lrang(String key) {
		return client.lrange(key.getBytes(),0,-1);
	}
	
	public static long del(String key) {
		return client.del(key.getBytes());
	}
	
	public static synchronized void setEntity(String hostName) {
		List<byte[]> serverList = getListFromRedis(serverNameListKey);
		if(serverList.size() <=0 || !existsOnServerList(serverList,hostName)) {
			client.lpush(serverNameListKey.getBytes(), hostName.getBytes());
		}
	}
	
	public static void inr(String serverName) {
		client.incr(serverName.getBytes());
	}
	
	public static synchronized void inc(String hostName) {
		client.incr(hostName.getBytes());
	}
	
	public static String getDate() {
		return dateformat.format(new Date());
	}
	
	public static String getKey(String salt) {
		return salt + "-" + getDate();
	}	
}
