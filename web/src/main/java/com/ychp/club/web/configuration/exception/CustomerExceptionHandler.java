package com.ychp.club.web.configuration.exception;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/9
 */
@Slf4j
@ControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView error500(Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("code", 500);
        mav.addObject("message", ex.getMessage());
        List<String> stackTraces = Lists.newArrayList();
        if(ex.getStackTrace() != null){
            for(StackTraceElement stackTraceElement :ex.getStackTrace())
                stackTraces.add(stackTraceElement.toString());
        }
        mav.addObject("stackTrace", stackTraces);
        mav.setViewName("500");
        return mav;
    }

}
