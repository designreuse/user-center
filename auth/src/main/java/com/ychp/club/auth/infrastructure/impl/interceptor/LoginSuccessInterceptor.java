package com.ychp.club.auth.infrastructure.impl.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/12
 */
@Slf4j
@Primary
@Component
public class LoginSuccessInterceptor extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException, ServletException {
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        //输出登录提示信息
        log.info("管理员 " + userDetails.getUsername() + " 登录");
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
