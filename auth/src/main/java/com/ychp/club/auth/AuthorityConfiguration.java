package com.ychp.club.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/12
 */
@MapperScan("com.ychp.club.auth.model.mysql")
@ComponentScan("com.ychp.club.auth")
@Configuration
public class AuthorityConfiguration {
}
