package com.yj.ws.util.redis;

import java.util.List;
import java.util.Map;

public interface IRedisJava {
	public boolean add(String key,String value);
	public String getValue(String key);
	public boolean updateKey(String oldkey,String newkey);
	public boolean  updateKeys(byte[] oldkey,byte[] newkey);
	public boolean expired(String key, int seconds);
	public boolean addMap(String key,Map<String, String> map);
	public Map<String, String> getMap(String key);
	public boolean addList(String key,List<String> value);
	public boolean addObject(String key,Object obj);
	public Object getObject(String key);
}
