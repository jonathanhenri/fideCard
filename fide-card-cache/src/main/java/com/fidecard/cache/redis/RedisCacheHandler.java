package com.fidecard.cache.redis;

import com.fidecard.cache.CacheHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class RedisCacheHandler implements CacheHandler {
	
	private final RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	public RedisCacheHandler(RedisTemplate<String, Object> redisTemplate) {
		Assert.notNull(redisTemplate, "redisTemplate obrigatorio");
		this.redisTemplate = redisTemplate;
	}
	
	@Override
	public void put(String queue, String key, Object object) {
		redisTemplate.opsForValue().set(getKeyName(queue, key), object);
	}
	
	@Override
	public void put(String key, Object object) {
		put(StringUtils.EMPTY, key, object);
	}
	
	@Override
	public Object get(String queue, String key) {
		return redisTemplate.opsForValue().get(getKeyName(queue, key));
	}
	
	@Override
	public Object get(String key) {
		return get(StringUtils.EMPTY, key);
	}
	
	@Override
	public void invalidate(String queue, String key) {
		redisTemplate.delete(getKeyName(queue, key));
	}
	
	@Override
	public void invalidate(String key) {
		invalidate(StringUtils.EMPTY, key);
	}
	
}
