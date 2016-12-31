package com.ychp.club.auth.infrastructure.impl.cache;

import com.ychp.club.auth.infrastructure.ShiroCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/31
 */
public class CustomerShiroCacheManager implements CacheManager,Destroyable {

    private ShiroCacheManager shiroCacheManager;

    private CustomerShiroCacheManager(ShiroCacheManager shiroCacheManager){
        this.shiroCacheManager = shiroCacheManager;
    }

    public static CustomerShiroCacheManager generalManagerWithJedis(ShiroCacheManager shiroCacheManager){
        return new CustomerShiroCacheManager(shiroCacheManager);
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return shiroCacheManager.getCache(name);
    }

    @Override
    public void destroy() throws Exception {
        shiroCacheManager.destroy();
    }
}
