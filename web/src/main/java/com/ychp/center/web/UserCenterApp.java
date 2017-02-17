package com.ychp.center.web;

import com.ychp.center.web.properties.shiro.ShiroProperties;
import com.ychp.coding.common.YchpBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/9
 */
@AutoConfigureAfter(WebAutoConfiguration.class)
@EnableWebMvc
@SpringBootApplication
@EnableConfigurationProperties(ShiroProperties.class)
public class UserCenterApp {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(UserCenterApp.class);
        application.setBanner(new YchpBanner());
        application.run(args);
    }
}
