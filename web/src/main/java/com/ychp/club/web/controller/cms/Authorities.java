package com.ychp.club.web.controller.cms;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ychp.club.auth.application.AuthorityManager;
import com.ychp.club.auth.model.App;
import com.ychp.club.auth.model.Authority;
import com.ychp.club.common.model.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/15
 */
@Slf4j
@Controller
@RequestMapping("/cms")
public class Authorities {

    @Autowired
    private AuthorityManager authorityManager;

    @RequestMapping("/perms")
    public String users(Model model,
                        @RequestParam(value = "appId",required = false) Long appId,
                        @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                        @RequestParam(value = "pageSize",defaultValue = "20") Integer pageSize){
        Paging<Authority> authorities = new Paging<>(pageNo, pageSize);
        if(appId != null){
            Map<String, Object> params = Maps.newHashMap();
            params.put("appId", appId);
            authorities = authorityManager.pagingAuthority(pageNo, pageSize, params);
        }

        model.addAttribute("authorities", authorities);
        model.addAttribute("appId", appId);
        return "auth/app/authorities";
    }

}
