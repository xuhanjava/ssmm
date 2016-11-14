package com.google.ssmm.config;

import org.apache.log4j.Logger;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
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

        Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        dynamic.addMapping("/"); // url mapping can be specified as this /abc/*
        dynamic.setLoadOnStartup(1);
        servletContext.addListener(new ContextLoaderListener(ctx));
    }
}
