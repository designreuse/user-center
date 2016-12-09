package com.ychp.club.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/9
 */
@MapperScan("com.ychp.club.user.model.mysql")
@ComponentScan("com.ychp.club.user")
@Configuration
public class UserConfiguration {
}
