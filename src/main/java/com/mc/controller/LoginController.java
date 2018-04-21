package com.mc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created by machao on  2018/4/21 20:17
 */
@Controller
public class LoginController {

    @RequestMapping("/index")
    public String index(Model model){
        return "index";
    }
}
