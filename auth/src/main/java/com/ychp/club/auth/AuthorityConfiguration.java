package com.ychp.club.auth;

import com.ychp.club.user.UserConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
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
@Import(UserConfiguration.class)
@AutoConfigureAfter(UserConfiguration.class)
@Configuration
public class AuthorityConfiguration {
}
