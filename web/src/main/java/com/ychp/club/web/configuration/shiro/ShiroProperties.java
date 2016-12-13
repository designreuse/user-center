package com.ychp.club.web.configuration.shiro;

import com.google.common.collect.Sets;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/12
 */
@Data
@Component
@ConfigurationProperties(prefix = "shiro")
public class ShiroProperties {

    private Set<String> ignoreExt = Sets.newHashSet(".jpg", ".png", ".gif", ".bmp", ".js", ".css");


}
