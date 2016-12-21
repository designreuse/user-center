package com.ychp.club.web.configuration.shiro;

import com.google.common.collect.Sets;
import com.ychp.club.auth.AuthorityConfiguration;
import com.ychp.club.auth.application.AuthorityManager;
import com.ychp.club.auth.infrastructure.impl.factory.CustomerShiroFactoryBeanImpl;
import com.ychp.club.auth.infrastructure.impl.realm.CustomerShiroRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/12
 */
@Configuration
@AutoConfigureAfter({AuthorityConfiguration.class})
@EnableConfigurationProperties(ShiroProperties.class)
public class ShiroConfiguration {

    private Set<String> ignoreExt = Sets.newHashSet(".jpg", ".png", ".gif", ".bmp", ".js", ".css", ".map", ".eot", ".svg", ".ttf", ".woff");

    @Bean
    public EhCacheManager cacheManager(){
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return cacheManager;
    }

    @Bean(name = "customerShiroRealm")
    public AuthorizingRealm customerShiroRealm(CacheManager cacheManager) {
        AuthorizingRealm realm = new CustomerShiroRealm();
        realm.setCacheManager(cacheManager);
        return realm;
    }


    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(AuthorizingRealm authorizingRealm, CacheManager cacheManager) {
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
        webSecurityManager.setRealm(authorizingRealm);
        webSecurityManager.setCacheManager(cacheManager);
        return webSecurityManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 加载shiroFilter权限控制规则（从数据库读取然后配置）
     *
     */
    private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean, AuthorityManager authorityManager){
        Map<String, String> filterChainDefinitionMap = authorityManager.loadAuthorities(1L);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }


    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager, AuthorityManager authorityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new CustomerShiroFactoryBeanImpl(ignoreExt);
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的连接
        shiroFilterFactoryBean.setSuccessUrl("/cms");
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");

        loadShiroFilterChain(shiroFilterFactoryBean, authorityManager);
        return shiroFilterFactoryBean;
    }
}
