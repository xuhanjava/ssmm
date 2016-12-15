package com.google.ssmm.common;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Walter
 */

public class SysContent {
    private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) requestLocal.get();
    }

    public static void setRequest(HttpServletRequest request) {
        requestLocal.set(request);
    }
}
