package com.ychp.club.web;

import com.ychp.club.auth.AuthorityConfiguration;
import com.ychp.club.user.UserConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
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
