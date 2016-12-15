package com.ychp.club.web.controller.cms;

import com.ychp.club.auth.application.AuthorityManager;
import com.ychp.club.auth.model.App;
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
@RequestMapping("/cms/app")
public class Apps {

    @Autowired
    private AuthorityManager authorityManager;

    @RequestMapping("list")
    public String users(Model model, @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                        @RequestParam(value = "pageSize",defaultValue = "20") Integer pageSize){

        List<App> appList = authorityManager.pagingApp(pageNo, pageSize, null);

        model.addAttribute("apps", appList);
        return "auth/apps";
    }

}
