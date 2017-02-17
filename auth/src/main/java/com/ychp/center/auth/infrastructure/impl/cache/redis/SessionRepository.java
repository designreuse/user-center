package com.ychp.center.auth.infrastructure.impl.cache.redis;

import com.google.common.collect.Lists;
import com.ychp.center.common.util.SerializableUtils;
import com.ychp.center.common.util.SessionUtils;
import com.ychp.coding.redis.dao.JedisTemplate;
import org.apache.shiro.cache.CacheException;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static com.ychp.center.common.util.SessionUtils.EXPIRE_SECONDS;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/31
 */
public class SessionRepository {

    private final JedisTemplate jedisTemplate;

    public SessionRepository(JedisTemplate jedisTemplate) {
        this.jedisTemplate = jedisTemplate;
    }

    public static SessionRepository generalRepositoryWithJedis(JedisTemplate jedisTemplate){
        return new SessionRepository(jedisTemplate);
    }

    Object get(String key) throws CacheException {
        return jedisTemplate.excute(jedis -> {
            byte[] bytes = jedis.get(key.getBytes());
            return SerializableUtils.toObject(bytes);
        });
    }

    Object put(String key, Object value) throws CacheException {
        return jedisTemplate.excute(jedis -> {
            jedis.setex(key.getBytes(), EXPIRE_SECONDS, SerializableUtils.toByteArray(value));
            byte[] bytes = jedis.get(key.getBytes());
            return SerializableUtils.toObject(bytes);
        });
    }

    Object remove(String key) throws CacheException {
        return jedisTemplate.excute(jedis -> {
            byte[] bytes = jedis.get(key.getBytes());
            jedis.del(key.getBytes());
            return SerializableUtils.toObject(bytes);
        });
    }

    void clear(String space) throws CacheException {
        jedisTemplate.excute(jedis -> {
            Set<String> keys = jedis.keys(SessionUtils.getSessionMatchKey(space));
            for(String key : keys){
                jedis.del(key.getBytes());
            }
            return true;
        });
    }

    int size() {
        return jedisTemplate.excute(jedis -> jedis.keys(SessionUtils.getSessionMatchKey()).size());
    }

    Set<String> keys() {
        return jedisTemplate.excute(jedis -> jedis.keys(SessionUtils.getSessionMatchKey()));
    }

    Collection<Object> values() {
        return jedisTemplate.excute(jedis -> {
            Set<String> keys = jedis.keys(SessionUtils.getSessionMatchKey());
            List<Object> values = Lists.newArrayList();
            byte[] bytes;
            for(String key : keys){
                bytes = jedis.get(key.getBytes());
                values.add(SerializableUtils.toObject(bytes));
            }
            return values;
        });
    }
}
