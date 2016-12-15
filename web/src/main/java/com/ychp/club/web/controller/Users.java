package com.ychp.club.web.controller;

import com.ychp.club.user.application.UserManager;
import com.ychp.club.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/9
 */
@Slf4j
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

    @RequestMapping("updateStatus")
    @ResponseBody
    public Boolean updateStatus(@RequestParam("id") Long id, @RequestParam("status") Integer status){
        boolean isSuccess = userManager.updateStatus(id, status);
        return isSuccess;
    }

    @RequestMapping("view")
    public String view(Model model, @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                        @RequestParam(value = "pageSize",defaultValue = "20") Integer pageSize){

        List<User> userList = userManager.paging(pageNo, pageSize, null);

        model.addAttribute("users", userList);
        return "users";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(Model model, @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                       @RequestParam(value = "pageSize",defaultValue = "20") Integer pageSize){

        List<User> userList = userManager.paging(pageNo, pageSize, null);

        model.addAttribute("users", userList);
        return "users";
    }
}
