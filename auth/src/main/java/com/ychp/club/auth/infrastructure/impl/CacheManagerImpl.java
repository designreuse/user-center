package com.ychp.club.auth.infrastructure.impl;


import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/12
 */
@Component
public class CacheManagerImpl implements CacheManager {


    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return null;
    }

}
