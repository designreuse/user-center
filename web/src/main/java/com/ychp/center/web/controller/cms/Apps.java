package com.ychp.center.web.controller.cms;

import com.ychp.center.common.util.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/9
 */
@Slf4j
@Controller
@RequestMapping("/cms")
public class Apps {


    @RequestMapping("/apps")
    public String apps(Model model, @RequestParam(value = "pageNo",defaultValue = PageUtils.DEFAULT_PAGE_NO) Integer pageNo,
                        @RequestParam(value = "pageSize",defaultValue = PageUtils.DEFAULT_PAGE_SIZE) Integer pageSize){
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("pageSize", pageSize);
        return "auth/app/apps";
    }

}
