package com.zj.spring.controller;

import com.zj.spring.beans.annotations.AutoWired;
import com.zj.spring.service.TestService;
import com.zj.spring.web.mvc.Controller;
import com.zj.spring.web.mvc.RequestMapping;
import com.zj.spring.web.mvc.RequestParam;

/**
 * @author zhaoj
 * @version TestController.java, v 0.1 2020-01-13 10:53
 */
@Controller
public class TestController {

    @AutoWired
    private TestService testService;

    @RequestMapping("/test")
    public Integer count(@RequestParam("experience") String experience) {

        return testService.calSalary(Integer.parseInt(experience));
    }
}
