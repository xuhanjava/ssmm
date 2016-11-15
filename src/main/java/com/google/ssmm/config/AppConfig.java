/*
package com.google.ssmm.config;

import com.google.ssmm.interceptor.BeforeControllerInterceptor;
import com.google.ssmm.interceptor.RequestProcessingTimeInterceptor;
import com.google.ssmm.interceptor.SecondInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages={"com.google.ssmm"})
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {
    @Bean
    public BeforeControllerInterceptor beforeControllerInterceptor() {
        return new BeforeControllerInterceptor();
    }

    @Bean
    public RequestProcessingTimeInterceptor requestProcessingTimeInterceptor(){
        return new RequestProcessingTimeInterceptor();
    }

    @Bean
    public SecondInterceptor secondInterceptor(){
        return new SecondInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(beforeControllerInterceptor());
        //registry.addInterceptor(requestProcessingTimeInterceptor());
        registry.addInterceptor(secondInterceptor());
    }

}
*/
