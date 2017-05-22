package com.google.ssmm.controller;

import com.google.ssmm.config.WebConfig;
import com.google.ssmm.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;
import java.util.concurrent.Executors;

/**
 * Created by xuhan on 16-12-7.
 */

@Controller
@RequestMapping(value = "/first")
public class Controller1 {


    private Logger logger = LoggerFactory.getLogger(Controller1.class);

    private static final String XUHAN="xuhan";
    /**
     * 不能加上@ResponseBody这个注解,否则是方法的返回值
     * @return
     */
    @RequestMapping(value = "1")
    public ModelAndView first(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("first");
        modelAndView.addObject(new Student(123,"123oh,yeah!"));
        Executors.newFixedThreadPool(4);
        return modelAndView;
    }


    /**
     * 调到当前controller的first方法中
     * @return
     */
    @RequestMapping(value = "2")
    public String second(){
        return "redirect:1";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String processFormData() {
        System.out.println("132");
        System.out.println("d1d1");
        return "first";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String processFormData1() {
        System.out.println("34");
        System.out.println("123");
        return "second";
    }


    @RequestMapping(value = "/thanks", method = RequestMethod.POST)
    public RedirectView thanks(@RequestBody Student student,ModelMap model) {
        model.put("name",student.getName());
        model.put("studentId",student.getStudentId());
        return new RedirectView("/first/form" ,true, false, false);
    }
}
