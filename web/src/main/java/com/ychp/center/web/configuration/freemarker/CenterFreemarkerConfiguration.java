package com.ychp.center.web.configuration.freemarker;

import com.jagregory.shiro.freemarker.ShiroTags;
import com.ychp.coding.freemarker.configuration.CustomerFreemarkerConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/7/30
 */
@Slf4j
@Configuration
@AutoConfigureAfter(CustomerFreemarkerConfiguration.class)
@Import(CustomerFreemarkerConfiguration.class)
public class CenterFreemarkerConfiguration {

    @Autowired(required = false)
    FreeMarkerConfigurer freeMarkerConfigurer;

    @PostConstruct
    public void initShareVariables() {
        if(freeMarkerConfigurer != null) {
            log.info("=====================>  shared variables ");
            freeMarkerConfigurer.getConfiguration().setSharedVariable("shiro", new ShiroTags());
            for(Object name : freeMarkerConfigurer.getConfiguration().getSharedVariableNames()){
                log.info("==========>  {}", name.toString());
            }
            log.info("=====================>  shared variables end ");
        }
    }

}
