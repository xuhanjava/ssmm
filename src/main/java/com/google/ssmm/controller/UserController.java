package com.google.ssmm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {


    @RequestMapping("/1")
    public String register() {
        System.out.println("123");
        return "123";
    }
}
