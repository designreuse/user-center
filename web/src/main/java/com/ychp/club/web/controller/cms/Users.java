package com.ychp.club.web.controller.cms;

import com.ychp.club.common.model.Paging;
import com.ychp.club.common.util.PageUtils;
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

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/9
 */
@Slf4j
@Controller
@RequestMapping("/cms")
public class Users {

    @Autowired
    private UserManager userManager;

    @RequestMapping("users")
    public String users(Model model, @RequestParam(value = "pageNo",defaultValue = PageUtils.DEFAULT_PAGE_NO) Integer pageNo,
                        @RequestParam(value = "pageSize",defaultValue = PageUtils.DEFAULT_PAGE_SIZE) Integer pageSize){
        Paging<User> userPaging = userManager.paging(pageNo, pageSize, null);
        model.addAttribute("users", userPaging);
        return "auth/user/users";
    }

    @RequestMapping("updateStatus")
    @ResponseBody
    public Boolean updateStatus(@RequestParam("id") Long id, @RequestParam("status") Integer status){
        boolean isSuccess = userManager.updateStatus(id, status);
        return isSuccess;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(){

        return "redirect:/user/list";
    }
}
