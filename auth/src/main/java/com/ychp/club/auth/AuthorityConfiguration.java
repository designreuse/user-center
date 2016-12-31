package com.ychp.club.auth;

import com.ychp.club.auth.infrastructure.impl.cache.CustomerShiroCacheManager;
import com.ychp.club.auth.infrastructure.impl.cache.redis.JedisCacheManager;
import com.ychp.club.auth.infrastructure.impl.cache.redis.SessionRepository;
import com.ychp.club.common.redis.config.RedisConfiguration;
import com.ychp.club.common.redis.dao.JedisTemplate;
import org.apache.shiro.cache.CacheManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/12
 */
@MapperScan("com.ychp.club.auth.model.mysql")
@ComponentScan("com.ychp.club.auth")
@AutoConfigureAfter(RedisConfiguration.class)
@Import(RedisConfiguration.class)
@Configuration
public class AuthorityConfiguration {

    @Bean
    public SessionRepository sessionRepository(JedisTemplate jedisTemplate){
        return SessionRepository.generalRepositoryWithJedis(jedisTemplate);
    }

    @Bean
    public JedisCacheManager jedisCacheManager(SessionRepository sessionRepository){
        return JedisCacheManager.generalCacheWithJedis(sessionRepository);
    }

    @Bean
    public CacheManager cacheManager(JedisCacheManager jedisCacheManager){
        return CustomerShiroCacheManager.generalManagerWithJedis(jedisCacheManager);
    }

}
