package com.fidecard.cache;


import org.apache.commons.lang3.StringUtils;

public interface CacheHandler {
	
	void put(String queue, String key, Object object);
	
	void put(String key, Object object);
	
	Object get(String queue, String key);
	
	Object get(String key);
	
	void invalidate(String queue, String key);
	
	void invalidate(String key);
	
	default String getKeyName(String queue, String key) {
		if (StringUtils.isEmpty(queue)) {
			return key;
		} else {
			return queue + ":" + key;
		}
	}
	
}
