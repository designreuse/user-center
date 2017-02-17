package com.ychp.center.auth.infrastructure.impl.cache.redis;

import com.ychp.center.auth.infrastructure.ShiroCacheManager;
import org.apache.shiro.cache.Cache;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/31
 */
public class JedisCacheManager implements ShiroCacheManager {

    private SessionRepository sessionRepository;

    private JedisCacheManager(SessionRepository sessionRepository){
        this.sessionRepository = sessionRepository;
    }

    public static JedisCacheManager generalCacheWithJedis(SessionRepository sessionRepository){
        return new JedisCacheManager(sessionRepository);
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) {
        return new JedisCache<>(name, sessionRepository);
    }

    @Override
    public void destroy() {
        //TODO 暂时不做
    }
}
