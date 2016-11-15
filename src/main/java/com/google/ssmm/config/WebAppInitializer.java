package com.google.ssmm.config;

import org.apache.log4j.Logger;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import java.util.EnumSet;

/**
 * 就是对web.xml中的处理。允许servlet，filter，listener不必声明在web.xml中，而是以硬编码的方式存在，实现容器的零配置。
 */
public class WebAppInitializer implements WebApplicationInitializer {

    private static final Logger log = Logger.getLogger(WebAppInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "test");
        String activeProfile = System.getenv("ECO_DOMAIN");
        log.info("Setting active profile as: " + activeProfile);
        if (null != activeProfile) {
            System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, activeProfile);
        }
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.scan("com.google.ssmm.config");
        ctx.setServletContext(servletContext);


        //建立之前的那种 dispatcherServlet 的一种servlet
        Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        dynamic.addMapping("/"); // url mapping can be specified as this /abc/*
        dynamic.setLoadOnStartup(1);
        servletContext.addListener(new ContextLoaderListener(ctx));

    }
}
