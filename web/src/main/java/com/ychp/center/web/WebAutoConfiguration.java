package com.ychp.center.web;

import com.ychp.center.auth.AuthorityConfiguration;
import com.ychp.center.user.UserConfiguration;
import com.ychp.center.web.configuration.shiro.ShiroConfiguration;
import com.ychp.coding.freemarker.configuration.CustomerFreemarkerConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/9
 */
@ComponentScan("com.ychp.club.web")
@EnableScheduling
@AutoConfigureAfter({ShiroConfiguration.class})
@Import({UserConfiguration.class, AuthorityConfiguration.class})
@Configuration
public class WebAutoConfiguration {

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return container -> {
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
            container.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401"));
            container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/error/403"));
            container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
        };
    }



}
