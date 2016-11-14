package com.google.ssmm.config;

import com.google.ssmm.interceptor.BeforeControllerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(basePackages={"com.google.ssmm"})
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {
    @Bean
    public BeforeControllerInterceptor beforeControllerInterceptor() {
        return new BeforeControllerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(beforeControllerInterceptor());
    }

}
