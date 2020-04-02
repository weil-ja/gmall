package com.atguigu.com.passport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class passportController {
    
    @RequestMapping("index")
    public String index() {
        
        return "index";
    }
}
