package com.google.ssmm.controller;

import com.google.ssmm.common.SourceType;
import com.google.ssmm.entity.Student;
import com.google.ssmm.entity.TableOne;
import com.google.ssmm.interceptor.SourceRequired;
import com.google.ssmm.service.FirstServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Walter on 16-11-14.
 */
@RestController
@RequestMapping("/")
//@SourceRequired(sources = {SourceType.BAICHANGHUI})
public class FirstController {
    private Logger logger = LoggerFactory.getLogger(FirstController.class);

    @Autowired
    private FirstServiceInterface firstService;

    /*放在处理器的一般方法（非功能处理方法）上时，是为表单准备要展示的表单引用
    对象，如注册时需要选择的所在城市等，而且在执行功能处理方法（@RequestMapping 注解的方法）之前，自动添加
    到模型对象中，用于视图页面展示时使用；*/
    @ModelAttribute(value="xuhan")
    private Student getStudent(HttpServletRequest request){
       return  (Student) request.getAttribute("xuhan");
    }

    @SourceRequired(sources = {SourceType.HUIZHANG})
    @RequestMapping(value ="first",method = RequestMethod.GET)
    public String firstMethod(
        @ModelAttribute(value ="xuhan") Student student
    ){
        System.out.println(student.getStudentId());
        return "hello";
    }

    @RequestMapping(value = "student/{studentId}",method = RequestMethod.GET)
    public Student getStudent(
        @PathVariable(value = "studentId")Integer studentId,
        @RequestParam(value = "studentName",required = false) String studentName
    ){
        return new Student(studentId,studentName);
    }

    @RequestMapping(value = "tx",method = RequestMethod.GET)
    public String getTransaction(){
        return "123";
    }

    @RequestMapping(value ="test1",method = RequestMethod.POST)
    public void test(){
        TableOne one = new TableOne();
        one.setName("oneXuhan");
        firstService.insertTableOne(one);
    }

}
