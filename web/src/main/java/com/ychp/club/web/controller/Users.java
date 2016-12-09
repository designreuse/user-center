package com.ychp.club.web.controller;

import com.ychp.club.user.application.UserManager;
import com.ychp.club.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/9
 */
@Controller
@RequestMapping("/user")
public class Users {

    @Autowired
    private UserManager userManager;

    @RequestMapping("paging")
    public String users(Model model, @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                        @RequestParam(value = "pageSize",defaultValue = "20") Integer pageSize){

        List<User> userList = userManager.paging(pageNo, pageSize, null);

        model.addAttribute("users", userList);
        return "users";
    }
}
