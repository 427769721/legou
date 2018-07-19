package com.legou.manager.web;

import com.alibaba.dubbo.common.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: xwh
 * Date: 2018/7/19 Time: 11:48
 * Version:V1.0
 */
@Controller
public class ViewAction {
    @RequestMapping(value = "/")
    public String index() {

        return "index";
    }
    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public String one(@PathVariable String page) {
        return page;
    }

    @RequestMapping(value = "/pages/{pageName1}", method = RequestMethod.GET)
    public String two(@PathVariable String pageName1) {
        return "pages/" + pageName1;
    }

    @RequestMapping(value = "/pages/{pageName1}/{pageName2}", method = RequestMethod.GET)
    public String three(@PathVariable String pageName1, @PathVariable String pageName2) {
        return "pages/" + pageName1 + "/" + pageName2;
    }



}
