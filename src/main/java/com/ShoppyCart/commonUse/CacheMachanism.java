package com.ShoppyCart.commonUse;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cache.internal.EnabledCaching;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import jakarta.persistence.CacheStoreMode;
import jakarta.persistence.EntityManager;

@Service
public class CacheMachanism {
	@Autowired   CacheManager cacheManager;
	@Autowired SessionFactory sessionFactory;

	public CacheMachanism() {
	}

	public   Object getCachedValue(String cacheName, Object key) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            Cache.ValueWrapper valueWrapper = cache.get(key);
            return (valueWrapper != null) ? valueWrapper.get() : null;
        }
        return null;
    }
	
	
	// Remove a specific key from the cache
    public void evictCacheEntry(String cacheName, Object key) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.evict(key); // Evicts the entry for the specified key
        }
    }
    
    
    public String sessionCache(String str) {
    	Session session = sessionFactory.openSession();
    	
    
   return "n";
    }
}
