package com.fidecard.cache.local;

import com.fidecard.cache.CacheHandler;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
@Primary
public class LocalCacheHandler implements CacheHandler {
	
	private static final int TEMPO_EXPIRACAO_EM_HORAS = 4;
	private static final int TAMANHO_MAXIMO_CACHE = 600;
	
	private Cache<String, Object> cache;
	
	@PostConstruct
	private void init() {
		cache = Caffeine.newBuilder().expireAfterWrite(TEMPO_EXPIRACAO_EM_HORAS, TimeUnit.HOURS)
				.maximumSize(TAMANHO_MAXIMO_CACHE).build();
	}
	
	@Override
	public void put(String queue, String key, Object object) {
		cache.put(getKeyName(queue, key), object);
	}
	
	@Override
	public void put(String key, Object object) {
		put(StringUtils.EMPTY, key, object);
	}
	
	@Override
	public Object get(String queue, String key) {
		return cache.getIfPresent(getKeyName(queue, key));
	}
	
	@Override
	public Object get(String key) {
		return get(StringUtils.EMPTY, key);
	}
	
	@Override
	public void invalidate(String queue, String key) {
		cache.invalidate(getKeyName(queue, key));
	}
	
	@Override
	public void invalidate(String key) {
		invalidate(StringUtils.EMPTY, key);
	}
	
}
