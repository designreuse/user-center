package com.ychp.club.web.configuration.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/9
 */
@Controller
@RequestMapping("/error")
public class Errors {

    @RequestMapping("/404")
    public String error404(){
        return "404";
    }

    @RequestMapping("/401")
    public String error401(){
        return "401";
    }
}
