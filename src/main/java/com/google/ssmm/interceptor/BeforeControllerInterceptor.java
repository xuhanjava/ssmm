package com.google.ssmm.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.ssmm.controller.FirstController;
import com.google.ssmm.common.SourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class BeforeControllerInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(BeforeControllerInterceptor.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 是在controller调用之前执行的，链式调用。当返回的而是false时候就不执行下面的拦截器和controller对象了
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        //返回这个方法对应的类
        Class<?> beanType = handlerMethod.getBeanType();
        FirstController controller = (FirstController) beanType.newInstance();

        //该方法
        //只是获得request请求对应的那个method
        Method method = handlerMethod.getMethod();

        Method firstMethod = beanType.getMethod("firstMethod");

        SourceRequired sourceMethod = method.getAnnotation(SourceRequired.class);
        SourceRequired firstMethodSource = firstMethod.getAnnotation(SourceRequired.class);
        SourceRequired sourceClass = beanType.getAnnotation(SourceRequired.class);

        if (firstMethodSource != null && sourceClass != null) {
            logger.info("method SourceRequired:" + firstMethodSource.sources()[0].getName());
            logger.info("class SourceRequired:" + sourceClass.sources()[0].getName());
        }

        //对SourceRequired的处理。请求中必须带有source这个header
        //优先处理method上的这个注解
        if (sourceMethod != null) {
            if (isUntuorizedRequest(request, response, sourceMethod)) {
                return false;
            }
        } else if (sourceClass != null) {
            if (isUntuorizedRequest(request, response, sourceClass)) {
                //这里必须返回一个false.否则请求继续执行，会返回给客户端响应值。却带着错误的401状态位
                return false;
            }
        }

        //浏览器上的cookie能传过来并接受。而postman发过来的就得特殊处理之后才能获得
        if (!getGid(request.getCookies())) {
            Cookie cookie = new Cookie("gid", "xuhangid123");
            //同一个tomcat服务器下共享cookie
            cookie.setPath("/");
            //跨域共享cookie
            cookie.setDomain("baichanghui.com");
            cookie.setMaxAge(Integer.MAX_VALUE);
            //response中设置的cookie并且返回的话就是显示在浏览器隐私设置中的那些 cookie。并不会删除客户端浏览器上这个ip对应的cookie。
            response.addCookie(cookie);
        }
        return true;
    }

    private boolean isUntuorizedRequest(HttpServletRequest request, HttpServletResponse response,
        SourceRequired sourceRequired) {
        boolean isExists = true;
        for (SourceType sourceType : sourceRequired.sources()) {
            //请求中的header必须带有source。且等于方法上的对应值
            if (!StringUtils.isEmpty(request.getHeader("source")) && request.getHeader("source").toUpperCase().trim()
                .equals(sourceType.getName())) {
                isExists = false;
                break;
            }
        }
        if (isExists) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return true;
        }
        return false;
    }

    private boolean getGid(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if ("gid".equals(cookie.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行，也就是说在这个方法中你可以对ModelAndView进行操
     * 作。这个方法的链式结构跟正常访问的方向是相反的.当preHandle返回为true时候才会执行
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        logger.info("after interceptor");
    }

    /**
     * 当请求完成之后执行。也就是当视图渲染完成之后执行。这个方法一般是做垃圾的清理。当preHandle返回为true时候才会执行
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
        logger.info("request complete");
    }
}

