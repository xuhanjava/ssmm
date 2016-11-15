package com.google.ssmm.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringWebContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;

    private static Logger logger = LoggerFactory.getLogger(SpringWebContextHolder.class);

    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {

        return applicationContext;
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> requiredType) {

        return applicationContext.getBean(requiredType);
    }

    /**
     * 返回bean 如果不存在 返回null 吃掉异常，慎用
     * 
     * @param requiredType
     * @return
     */
    public static <T> T getBeanWithoutException(Class<T> requiredType) {
        T t = null;
        try {
            if (applicationContext != null) {
                t = applicationContext.getBean(requiredType);
            }
        } catch (BeansException e) {
        }
        return t;
    }

    /**
     * 清除SpringWebContextHolder中的ApplicationContext为Null.
     */
    public static void clearHolder() {
        logger.debug("清除SpringWebContextHolder中的ApplicationContext:" + applicationContext);
        applicationContext = null;
    }

    /**
     * 实现ApplicationContextAware接口, 注入Context到静态变量中.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {

        logger.debug("注入ApplicationContext到SpringWebContextHolder:" + applicationContext);

        if (SpringWebContextHolder.applicationContext != null) {
            logger.warn("SpringWebContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:"
                + SpringWebContextHolder.applicationContext);
        }

        SpringWebContextHolder.applicationContext = applicationContext; // NOSONAR
    }

    /**
     * 实现DisposableBean接口, 在Context关闭时清理静态变量.
     */
    @Override
    public void destroy() throws Exception {
        SpringWebContextHolder.clearHolder();
    }

}
