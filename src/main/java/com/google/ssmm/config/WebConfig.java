package com.google.ssmm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.SimpleServletHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by xuhan on 16-12-7.
 * http://hanqunfeng.iteye.com/blog/2114987
 */
@Configuration
@ComponentScan(basePackages={"com.google.ssmm"})
@EnableWebMvc
public class WebConfig extends WebMvcConfigurationSupport {
    private Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

    @Bean
    public HandlerAdapter servletHandlerAdapter() {
        return new SimpleServletHandlerAdapter();
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver commonsMultipartResolver() {
        logger.info("CommonsMultipartResolver");
        return new CommonsMultipartResolver();
    }

    @Bean
    public HandlerMapping resourceHandlerMapping() {
        logger.info("HandlerMapping");
        return super.resourceHandlerMapping();
    }
    /**
     * 描述 : <资源访问处理器>
     * <可以在jsp中使用/static/**的方式访问/WEB-INF/static/下的内容>
     * @param registry
     */

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.info("addResourceHandlers");
        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/views");
    }

    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        logger.info("RequestMappingHandlerMapping");

        return super.requestMappingHandlerMapping();
    }

    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        logger.info("RequestMappingHandlerAdapter");
        return super.requestMappingHandlerAdapter();
    }
}
