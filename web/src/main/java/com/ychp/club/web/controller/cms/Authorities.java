package com.ychp.club.web.controller.cms;

import com.google.common.collect.Maps;
import com.ychp.club.auth.application.AuthorityManager;
import com.ychp.club.auth.model.App;
import com.ychp.club.auth.model.Authority;
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
@RequestMapping("/cms/authority")
public class Authorities {

    @Autowired
    private AuthorityManager authorityManager;

    @RequestMapping("list")
    public String users(Model model,
                        @RequestParam("appId") Long appId,
                        @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                        @RequestParam(value = "pageSize",defaultValue = "20") Integer pageSize){
        Map<String, Object> params = Maps.newHashMap();
        params.put("appId", appId);

        List<Authority> authorities = authorityManager.pagingAuthority(pageNo, pageSize, params);

        model.addAttribute("authorities", authorities);
        return "auth/authorities";
    }

}
