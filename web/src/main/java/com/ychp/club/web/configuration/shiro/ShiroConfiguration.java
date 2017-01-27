package com.ychp.club.web.configuration.shiro;

import com.ychp.club.auth.AuthorityConfiguration;
import com.ychp.club.auth.application.AuthorityManager;
import com.ychp.club.auth.infrastructure.impl.factory.CustomerShiroFactoryBeanImpl;
import com.ychp.club.auth.infrastructure.impl.realm.CustomerShiroRealm;
import com.ychp.club.web.properties.shiro.ShiroProperties;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/12
 */
@Configuration
@AutoConfigureAfter({AuthorityConfiguration.class})
public class ShiroConfiguration {

    @Bean(name = "customerShiroRealm")
    public AuthorizingRealm customerShiroRealm(AuthorityManager authorityManager) {
        AuthorizingRealm realm = new CustomerShiroRealm();
        realm.setCacheManager(authorityManager.getCache());
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
    public DefaultWebSecurityManager getDefaultWebSecurityManager(AuthorizingRealm authorizingRealm, AuthorityManager authorityManager) {
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
        webSecurityManager.setRealm(authorizingRealm);
        webSecurityManager.setCacheManager(authorityManager.getCache());
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
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager, AuthorityManager authorityManager, ShiroProperties shiroProperties) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new CustomerShiroFactoryBeanImpl(shiroProperties.getIgnoreExt());
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/cms/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");
        loadShiroFilterChain(shiroFilterFactoryBean, authorityManager);
        return shiroFilterFactoryBean;
    }
}
