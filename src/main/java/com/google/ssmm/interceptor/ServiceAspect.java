package com.google.ssmm.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ServiceAspect {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public Object invoke(ProceedingJoinPoint jp) throws Throwable {
        Object result = null;
        // 输入输出日志
        if (jp.getArgs() != null && jp.getArgs().length > 0) {
            List<Object> args = new ArrayList<Object>();
            for (int i = 0; i < jp.getArgs().length; i++) {
                if (!(jp.getArgs()[i] instanceof HttpServletRequest
                    || jp.getArgs()[i] instanceof HttpServletResponse)) {
                    args.add(jp.getArgs()[i]);
                }
            }
        }
        // 修改metrics
        try {
            result = jp.proceed();
        } catch (Exception e) {
            throw e;
        } finally {
        }
        return result;
    }

}

