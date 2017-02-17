package com.ychp.center.web.interceptor;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ychp.center.user.application.UserManager;
import com.ychp.center.user.model.LoginUser;
import com.ychp.center.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 17/1/10
 */
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private Map<String,List<String>> whiteList;

    private final UserManager userManager;

    @Autowired
    public LoginInterceptor(UserManager userManager) {
        this.userManager = userManager;
        whiteList = Maps.newHashMap();
        List<String> urls;
        BufferedReader br = null;
        String[] values;
        String url;
        InputStream is = this.getClass().getResourceAsStream("/auth/default_auth");
        try {
            br = new BufferedReader(new InputStreamReader(is));
            for(String line =br.readLine();line!=null;line = br.readLine()){
                if(StringUtils.isEmpty(line) || line.trim().startsWith("#")){
                    continue;
                }
                values = line.split(":");
                url = values[0].trim();
                for(String method : values[1].split(",")){
                    method = method.toUpperCase();
                    urls = whiteList.get(method);
                    if(urls == null){
                        urls = Lists.newArrayList();
                    }
                    urls.add(url);
                    whiteList.put(method, urls);
                }
            }
        } catch (FileNotFoundException e) {
            log.error("white list file not exist");
        } catch (IOException e) {
            log.error("white list read fail, error = {}", Throwables.getStackTraceAsString(e));
        } finally{
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    log.error("close bufferedReader fail, error = {}", Throwables.getStackTraceAsString(e));
                }
            }
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if(!"/login".equals(uri) && !"/logout".equals(uri)){
            WebUtils.saveRequest(request);
        }
        String method = request.getMethod().toUpperCase();
        List<String> urls;
        urls = whiteList.get(method);
        if(urls != null){
            for(String item : urls){
                if(uri.matches(item)){
                    return true;
                }
            }
        }
        HttpSession session = request.getSession();

        if(session.getAttribute("online") == null) {
            Subject currentUser = SecurityUtils.getSubject();
            if(currentUser.getPrincipal() == null) {
                return true;
            }
            String username = ((String) currentUser.getPrincipal()).split(",")[0];
            User user = userManager.findByUsername(username);
            if(user == null){
                return true;
            }

            Session shiroSession = currentUser.getSession();
            shiroSession.setAttribute(DefaultSubjectContext.AUTHENTICATED_SESSION_KEY, true);
            session.setAttribute("online", LoginUser.makeUser(user));
            response.sendRedirect(uri);
        }

        return true;
    }

}
