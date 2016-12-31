package com.ychp.club.auth.infrastructure.impl.cache.redis;

import com.ychp.club.common.util.SessionUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/31
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JedisCache<K, V> implements Cache<K, V> {

    private String name;

    private SessionRepository sessionRepository;

    @Override
    public V get(K key) throws CacheException {
        return (V)sessionRepository.get(SessionUtils.getSessionKey(name, key));
    }

    @Override
    public V put(K key, V value) throws CacheException {
        return (V)sessionRepository.put(SessionUtils.getSessionKey(name, key), value);
    }

    @Override
    public V remove(K key) throws CacheException {
        return (V)sessionRepository.remove(SessionUtils.getSessionKey(name, key));
    }

    @Override
    public void clear() throws CacheException {
        sessionRepository.clear(name);
    }

    @Override
    public int size() {
        return sessionRepository.size();
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return (List<V>)sessionRepository.values();
    }

}
