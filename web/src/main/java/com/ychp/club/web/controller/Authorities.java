package com.ychp.club.web.controller;

import com.google.common.base.Throwables;
import com.ychp.club.auth.application.AuthorityManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/15
 */
@Slf4j
@Controller
@RequestMapping("/authority")
public class Authorities {

    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    @Autowired
    private AuthorityManager authorityManager;

    @RequestMapping("reload")
    @ResponseBody
    public boolean reload(){

        AbstractShiroFilter shiroFilter = null;

        try {
            shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
        } catch (Exception e) {
            log.error(Throwables.getStackTraceAsString(e));
        }

        // 获取过滤管理器
        PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
        DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

        // 清空初始权限配置
        manager.getFilterChains().clear();
        shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();

        Map<String, String> filterChainDefinitionMap = authorityManager.loadAuthorities(1L);
        // 重新构建生成
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();

        for (Map.Entry<String, String> entry : chains.entrySet()) {
            String url = entry.getKey();
            String chainDefinition = entry.getValue().trim().replace(" ", "");
            manager.createChain(url, chainDefinition);
        }

        return true;
    }

}
