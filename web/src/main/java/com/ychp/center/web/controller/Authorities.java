package com.ychp.center.web.controller;

import com.google.common.collect.Maps;
import com.ychp.center.common.util.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/15
 */
@Slf4j
@Controller
@RequestMapping("")
public class Authorities {

    @RequestMapping("/perms")
    public String users(Model model,
                        @RequestParam(value = "appId",required = false) Long appId,
                        @RequestParam(value = "pageNo",defaultValue = PageUtils.DEFAULT_PAGE_NO) Integer pageNo,
                        @RequestParam(value = "pageSize",defaultValue = PageUtils.DEFAULT_PAGE_SIZE) Integer pageSize){
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("pageSize", pageSize);
        Map<String,Object> params = Maps.newHashMap();
        params.put("appId", appId);
        model.addAttribute("params", params);
        return "app/authorities";
    }

}
