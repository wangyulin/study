package redis.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DemoUtils<syncronized> {
	
	private static volatile DemoUtils INSTANCE;
	
	private DemoUtils(){
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(32);  
        config.setMaxIdle(6);  
        config.setMinIdle(0);  
        config.setMaxWaitMillis(15000);  
        config.setMinEvictableIdleTimeMillis(300000);  
        config.setSoftMinEvictableIdleTimeMillis(-1);  
        config.setNumTestsPerEvictionRun(3);  
        config.setTestOnBorrow(false);  
        config.setTestOnReturn(false);  
        config.setTestWhileIdle(false);  
        config.setTimeBetweenEvictionRunsMillis(60000);//一分钟
        pool = new JedisPool(config,"wangyulin-test-host",6379,15000);
        jedis = pool.getResource();
	}
	
	public static DemoUtils<?> getInstance(){
        if(INSTANCE == null){
           synchronized(DemoUtils.class){
               if(INSTANCE == null){
                   INSTANCE = new DemoUtils();
               }
           }
        }
        return INSTANCE;
    }
	
	public String keyName = "host_list";
	
	private SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
	
	private Jedis jedis;

	private JedisPool pool;
	
	public List<byte[]> getListFromRedis(String key) {
		return jedis.lrange(key.getBytes(), 0, -1);
	}
	
	public boolean isExist(String key,String target) {
		List<byte[]> res = jedis.lrange(key.getBytes(), 0, -1);
		if(res != null) {
			for(byte[] it : jedis.lrange(key.getBytes(), 0, -1)) {
				if(target.equals(new String(it))) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public synchronized void setEntity(String hostName) {
		List<byte[]> res = jedis.lrange(keyName.getBytes(), 0, -1);
		if(res != null) {
			boolean flag = false;//是否已经包含
			for(byte[] it : res) {
				if(hostName.equals(new String(it))) {
					flag = true;
				}
			}
			if(!flag) {
				jedis.lpush(keyName.getBytes(), hostName.getBytes());
			}
		}
	}
	
	public synchronized void inc(String hostName) {
		jedis.incr(hostName.getBytes());
	}
	
	public String getDate() {
		return dateformat.format(new Date());
	}
	
	public String getKey(String salt) {
		return salt + "-" + getDate();
	}
	
}
