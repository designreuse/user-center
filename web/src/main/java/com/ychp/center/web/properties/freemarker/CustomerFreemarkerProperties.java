package com.ychp.center.web.properties.freemarker;

import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/7/30
 */
@Primary
@Component
@ConfigurationProperties(prefix = "spring.freemarker")
public class CustomerFreemarkerProperties extends FreeMarkerProperties {

    private Map<String,Object> variables = new HashMap<String, Object>();

    public void setVariables(Map<String,Object> variables){
        this.variables = variables;
    }

    public  Map<String,Object> getVariables(){
        return variables;
    }

}
