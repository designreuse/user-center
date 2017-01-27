package com.ychp.club.web;

import com.ychp.club.web.properties.shiro.ShiroProperties;
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
public class ClubApp {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ClubApp.class);
        application.setBanner(new ClubBanner());
        application.run(args);
    }
}
