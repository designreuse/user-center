package com.ychp.club.web.controller;

import com.google.common.base.Throwables;
import com.ychp.club.auth.model.shiro.CustomerUsernamePasswordToken;
import com.ychp.club.common.util.CustomerStringUtils;
import com.ychp.club.user.application.UserManager;
import com.ychp.club.user.model.LoginUser;
import com.ychp.club.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/13
 */
@Slf4j
@Controller
@RequestMapping
public class Login {

    @Autowired
    private UserManager userManager;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage(){
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        @RequestParam(value = "remember", defaultValue = "false") Boolean remember,
                        @RequestParam(value = "target", required = false) String target,
                        @RequestParam(value = "checkCode", required = false) String checkCode,
                        RedirectAttributes redirectAttributes, HttpSession session){

        User user = userManager.findByUsername(username);

        CustomerUsernamePasswordToken token;
        if(user != null) {
            token = new CustomerUsernamePasswordToken(
                    user.getUsername() + CustomerStringUtils.SPLIT_CHARACTER + user.getRoleId(),
                    password, user.getSalt(), user.getPassword(), user.getStatus(), remember);
        } else {
            token = new CustomerUsernamePasswordToken(null, null, null, null, null, false);
        }

        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
            session.setAttribute("online", LoginUser.makeUser(user));
        } catch(UnknownAccountException uae){
            log.error("unknown account {}", username);
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        } catch(IncorrectCredentialsException ice){
            log.error("credentials incorrect username[{}] password[{}]", username, password);
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        } catch(LockedAccountException lae){
            log.error("account[{}] locked", username);
            redirectAttributes.addFlashAttribute("message", "账户已锁定");
        } catch(ExcessiveAttemptsException eae){
            log.error("excessive attempts account[{}]", username);
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
        } catch(AuthenticationException ae){
            log.error("authenticate account[{}] fail, case:{}", username, Throwables.getStackTraceAsString(ae));
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        }

        //验证是否登录成功
        if(currentUser.isAuthenticated()){
            if(StringUtils.isEmpty(target)){
                return "redirect:/";
            } else {
                return "redirect:" + target;
            }
        }else{
            token.clear();
            return "redirect:/login";
        }

    }

    @RequestMapping(value="/logout",method = RequestMethod.GET)
    public String logout(RedirectAttributes redirectAttributes ){
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return "redirect:/login";
    }

}
