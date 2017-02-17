package com.ychp.center.web.controller.apis;

import com.ychp.center.common.model.Paging;
import com.ychp.center.common.util.PageUtils;
import com.ychp.center.user.application.UserManager;
import com.ychp.center.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/9
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class UserApis {

    @Autowired
    private UserManager userManager;

    @RequestMapping("users")
    public Paging<User> users(@RequestParam(value = "pageNo",defaultValue = PageUtils.DEFAULT_PAGE_NO) Integer pageNo,
                        @RequestParam(value = "pageSize",defaultValue = PageUtils.DEFAULT_PAGE_SIZE) Integer pageSize){
        return userManager.paging(pageNo, pageSize, null);
    }

    @RequestMapping("updateStatus")
    @ResponseBody
    public Boolean updateStatus(@RequestParam("id") Long id, @RequestParam("status") Integer status){
        return userManager.updateStatus(id, status);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(){

        return "redirect:/user/list";
    }
}
