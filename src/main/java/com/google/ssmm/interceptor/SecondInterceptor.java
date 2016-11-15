package com.google.ssmm.interceptor;

import com.google.ssmm.entity.Student;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xuhan on 16-11-17.
 */
public class SecondInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        //request.setAttribute("xuhan",new Student(23,"xuhan"));
        //设置页面缓存有关的东西
        //response.setHeader();

        return true;
    }
}
