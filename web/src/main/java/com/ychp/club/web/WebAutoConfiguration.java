package com.ychp.club.web;

import com.ychp.club.auth.AuthorityConfiguration;
import com.ychp.club.user.UserConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
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
}
