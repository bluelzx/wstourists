package com.yj.ws.util.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;









import com.sun.org.apache.bcel.internal.generic.RETURN;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Pool;

public class RedisJava implements IRedisJava{
	private final static Logger log = Logger.getLogger(RedisJava.class);
	private Jedis jedis;//非切片额客户端连接
	private JedisPool jedisPool;//非切片连接池
	private ShardedJedis shardedJedis;//切片额客户端连接
	private ShardedJedisPool shardedJedisPool;//切片连接池
	private Pool pool;
	private void initialPool() 
	{ 
	    // 池基本配置 
	    JedisPoolConfig config = new JedisPoolConfig(); 
	    config.setMaxActive(20); 
	    config.setMaxIdle(5); 
	    config.setMaxWait(1000l); 
	    config.setTestOnBorrow(false); 
	        
	    jedisPool = new JedisPool(config,"127.0.0.1",6379);
	}
	    
	/** 
	 * 初始化切片池 
	 */ 
	private void initialShardedPool() 
	{ 
	    // 池基本配置 
	    JedisPoolConfig config = new JedisPoolConfig(); 
	    config.setMaxActive(20); 
	    config.setMaxIdle(5); 
	    config.setMaxWait(1000l); 
	    config.setTestOnBorrow(false); 
	    // slave链接 
	    List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>(); 
	    shards.add(new JedisShardInfo("127.0.0.1", 6379, "master")); 

	    // 构造池 
	    shardedJedisPool = new ShardedJedisPool(config, shards); 
	} 
	public RedisJava()   
	{ 
	    initialPool(); 
	    initialShardedPool(); 
	    shardedJedis = shardedJedisPool.getResource(); 
	    jedis = jedisPool.getResource(); 
	        
	        
	} 
	
	public static void main(String[] args) {
		RedisJava r=new RedisJava();
		r.jedis.append("1", "aaaa");
		System.out.println(r.jedis.get("1"));
	}
	
	public static RedisJava instance;
	public static RedisJava getInstance(){
        if(instance==null){
             if(instance==null){
                 instance=SingletonFactory.instance;
             }
        }
        return instance;
    } 
	public static class SingletonFactory{
		public static RedisJava instance=new RedisJava();
	}
	/**
	 * redis增加
	 */
	public boolean add(String key,String value){
		try {
			getInstance().jedis.sadd(key, value);
		} catch (Exception e) {
			log.debug(e.getMessage());
			return false;
		}finally{
			
		}
		return true;
	}
	
	/**
	 * 增加字段
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean appenValue(String key,String value){
		try {
			getInstance().jedis.append(key, value);
		} catch (Exception e) {
			log.debug(e.getMessage());
			return false;
		}finally{
			
		}
		return true;
	}
	/**
	 * redis得到结果
	 * @param key
	 * @return
	 */
	public String getValue(String key){
		return getInstance().jedis.get(key);
	}
	/**
	 * 修改key
	 * @param oldkey
	 * @param newkey
	 * @return
	 */
	public boolean updateKey(String oldkey,String newkey){
		try {
			getInstance().jedis.rename(oldkey, newkey);
		} catch (Exception e) {
			log.debug(e.getMessage());
			return false;
		}
		return true;
	}
	/**
	 * 修改多个key值
	 * @param oldkey
	 * @param newkey
	 * @return
	 */
	public boolean  updateKeys(byte[] oldkey,byte[] newkey){
		try {
			getInstance().jedis.rename(oldkey, newkey);
		} catch (Exception e) {
			log.debug(e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * 设置reids过期时间 
	 * @param key
	 * @param seconds   时间为秒
	 */
	public boolean expired(String key, int seconds) {
		try {
			getInstance().jedis.expire(key, seconds);
		} catch (Exception e) {
			log.debug(e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * 增加map
	 * @param key
	 * @param map
	 * @return
	 */
	public boolean addMap(String key,Map<String, String> map){
		try {
			getInstance().jedis.hmset(key, map);
		} catch (Exception e) {
			log.debug(e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * 得到map
	 * @param key
	 * @return
	 */
	public Map<String, String> getMap(String key){
		try {
			return getInstance().jedis.hgetAll(key);
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 存list
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean addList(String key,List<String> value){
		try {
			for (String va : value) {
				getInstance().jedis.lpush(key, va);
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			return false;
		}
		return true;
	}
	
	/*public List<String> getList(String key){
		List<String> list=null;
		
	}*/
	
	/**
	 * 增加实例化
	 * @param key
	 * @param obj
	 */
	public boolean addObject(String key,Object obj){
		try {
			if(obj!=null){
				 getInstance().jedis.set(key.getBytes(), SerializationUtil.serialize(obj));
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * 获取实例
	 * @param key
	 * @return
	 */
	public Object getObject(String key){
		byte[] bs = jedis.get(key.getBytes());
		return SerializationUtil.deserialize(bs);
	}
}






	