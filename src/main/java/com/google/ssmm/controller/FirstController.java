package com.google.ssmm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xuhan on 16-11-14.
 */
@RestController
@RequestMapping("/")
public class FirstController {
    @RequestMapping(value ="first",method = RequestMethod.GET)
    public void first(){
        System.out.println("begin first method");
    }
}
