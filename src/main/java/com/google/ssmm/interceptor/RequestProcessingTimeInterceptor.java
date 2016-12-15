package com.google.ssmm.interceptor;

import com.google.ssmm.common.LoggerNameConstants;
import com.google.ssmm.log.IMetricsOutPut;
import com.google.ssmm.log.MetricsManager;
import com.google.ssmm.log.ServiceInfo;
import com.google.ssmm.log.SpringWebContextHolder;
import com.google.ssmm.utils.IdUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class RequestProcessingTimeInterceptor extends HandlerInterceptorAdapter {
    private static final String TRACE_ID = "traceId";
    private static final String REQUEST_ID = "requestId";
    private static final String SESSION_ID = "sessionId";
    private static final String TOKEN_ID = "token";
    private static final String GID = "gid";
    private static final Logger PERFORMANCE_LOGGER = LoggerFactory.getLogger(LoggerNameConstants.PERFORMANCE_LOGGER);
    private static final Logger RAW_LOGGER = LoggerFactory.getLogger(LoggerNameConstants.RAW_LOGGER);
    private static final String HOST = getHost();
    private static final String DOMAIN = "baichanghui.com";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        MetricsManager metricsManager = MetricsManager.getInstance();
        //为什么一上来就要新建一个，如果是后来的请求的话怎么办，又新建一个吗？ 因为这是打印的是每次请求的参数信息到Performance.log日志文件中
        metricsManager.createNewMetrics();
        metricsManager.setStartTime(new Date());
        metricsManager.setDomain(System.getenv("ECO_DOMAIN"));
        // 添加source
        String source = request.getHeader("source");
        if (StringUtils.isNotBlank(source)) {
            metricsManager.addAtrributes("source", source);
        }
        // 添加channelId
        String channelid = request.getHeader("channelid");
        if (StringUtils.isNotBlank(channelid)) {
            metricsManager.addAtrributes("channelId", channelid);
        }
        // 添加clientType
        String clientType = request.getHeader("clientType");
        if (StringUtils.isNotBlank(clientType)) {
            metricsManager.addAtrributes("clientType", clientType);
        }
        // 添加market
        String market = request.getHeader("market");
        if (StringUtils.isNotBlank(market)) {
            metricsManager.addAtrributes("market", market);
        }
        if (StringUtils.isNotBlank(request.getHeader("landingPage"))) {
            metricsManager.addAtrributes("landingPage", request.getHeader("landingPage"));
        }
        // 为每个请求生成requestId
        String requestId = IdUtils.getRequestId();
        metricsManager.setRequestId(requestId);
        // 查看request中是否有traceId，没有则生成
        String traceId = StringUtils.EMPTY;
        if (request.getAttribute(TRACE_ID) == null) {
            traceId = IdUtils.getTraceId();
            // 将traceId放入request
            request.setAttribute(TRACE_ID, traceId);
        } else {
            traceId = request.getAttribute(TRACE_ID).toString();
        }
        metricsManager.setTraceId(traceId);
        // 查看request中是否有sessionId，没有则生成
        String sessionId = getSessionId(request);
        if (StringUtils.isBlank(sessionId)) {
            sessionId = IdUtils.getSessionId();
            //添加一个cookie，需要看一下
            Cookie cookie = new Cookie(SESSION_ID, sessionId);
            //cookie设置path是什么作用来。测试一下
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        metricsManager.setSessionId(sessionId);
        // 设置gid
        String gid = getGid(request);
        if (StringUtils.isBlank(gid)) {
            gid = createGid(request);
        }
        if (StringUtils.isNotBlank(gid)) {
            Cookie cookie = new Cookie(GID, gid);
            cookie.setPath("/");
            setCookieDomain(cookie);
            cookie.setMaxAge(Integer.MAX_VALUE);
            response.addCookie(cookie);
            metricsManager.setGid(gid);
        }
        // 设置token
        String tokenId = request.getHeader("token");
        if (StringUtils.isNotBlank(tokenId)) {
            metricsManager.setTokenId(tokenId);
        }
        // 设置path
        String path = request.getRequestURI();
        if (StringUtils.isNotBlank(path)) {
            metricsManager.setPath(path);
        }
        // 设置metrics输出
        metricsManager.setIMetricsOutPut(new IMetricsOutPut() {
            @Override
            public void saveLog(String metricsJson) {
                PERFORMANCE_LOGGER.info(metricsJson);
            }
        });
        // MDC新增参数，用于log4j
        MDC.put(SESSION_ID, sessionId);
        MDC.put(REQUEST_ID, requestId);
        if (StringUtils.isBlank(gid)) {
            MDC.put(TRACE_ID, traceId);
        } else {
            MDC.put(TRACE_ID, gid);
        }
        return true;
    }



    private void setCookieDomain(Cookie cookie) {
        String systemDomain = System.getenv("ECO_DOMAIN");
        if ((systemDomain != null && systemDomain.equals("prod"))) {
            cookie.setDomain(DOMAIN);
        }
    }



    private String createGid(HttpServletRequest request) throws IOException {
        // 获取gid的请求不能拦截
        if (request.getServletPath().contains("internal/user/gid")) {
            return "";
        }
        String clientType = request.getHeader("clientType");
        if (StringUtils.isBlank(clientType)) {
            return "";
        }
        if ((clientType.equalsIgnoreCase("android") || clientType.equalsIgnoreCase("iphone"))
            && versionIsLow(request)) {
            return "";
        }
        if (clientType.equalsIgnoreCase("h5") || clientType.equalsIgnoreCase("web")
            || clientType.equalsIgnoreCase("android") || clientType.equalsIgnoreCase("iphone")) {
            Map<String, Object> map = getHeadMap(request);
            //return loginServiceClient.getGid(map);
            return UUID.randomUUID().toString();
        }
        return "";
    }



    private Map<String, Object> getHeadMap(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(request.getHeader("os"))) {
            map.put("os", request.getHeader("os"));
        }
        if (StringUtils.isNotBlank(request.getHeader("osVersion"))) {
            map.put("osVersion", request.getHeader("osVersion"));
        }
        if (StringUtils.isNotBlank(request.getHeader("deviceType"))) {
            map.put("deviceType", request.getHeader("deviceType"));
        }
        if (StringUtils.isNotBlank(request.getHeader("source"))) {
            map.put("source", request.getHeader("source"));
        }
        if (StringUtils.isNotBlank(request.getHeader("channelId"))) {
            map.put("channelId", request.getHeader("channelId"));
        }
        if (StringUtils.isNotBlank(request.getHeader("clientType"))) {
            map.put("clientType", request.getHeader("clientType"));
        }
        if (StringUtils.isNotBlank(request.getHeader("market"))) {
            map.put("market", request.getHeader("market"));
        }
        if (StringUtils.isNotBlank(request.getHeader("appVersion"))) {
            map.put("appVersion", request.getHeader("appVersion"));
        }
        if (StringUtils.isNotBlank(request.getHeader("h5Version"))) {
            map.put("h5Version", request.getHeader("h5Version"));
        }
        if (StringUtils.isNotBlank(request.getHeader("longitude"))) {
            map.put("longitude", request.getHeader("longitude"));
        }
        if (StringUtils.isNotBlank(request.getHeader("latitude"))) {
            map.put("latitude", request.getHeader("latitude"));
        }
        if (StringUtils.isNotBlank(request.getHeader("deviceId"))) {
            map.put("deviceId", request.getHeader("deviceId"));
        }
        if (StringUtils.isNotBlank(request.getHeader("x-forwarded-for"))) {
            String findRealIP = findRealIP(request.getHeader("x-forwarded-for"));
            if (StringUtils.isNotBlank(findRealIP)) {
                map.put("realIP", findRealIP);
            }
        }
        return map;
    }

    private String findRealIP(String xForwardedFor) {
        if (xForwardedFor == null || xForwardedFor.trim().isEmpty()) {
            return null;
        }

        String[] ips = xForwardedFor.trim().split(", ");
        for (int idx = ips.length - 1; idx >= 0; idx--) {
            if (!ips[idx].startsWith("10.")) {
                return ips[idx];
            }
        }
        return null;
    }


    private boolean versionIsLow(HttpServletRequest request) {
        String clientType = request.getHeader("clientType");
        String source = request.getHeader("source");
        String appVersion = request.getHeader("appVersion");
        if (StringUtils.isBlank(source) || StringUtils.isBlank(appVersion)) {
            return true;
        }
        if (clientType.equalsIgnoreCase("android")) {
            if (source.equalsIgnoreCase("baichanghui")) {
                if (appVersion.compareToIgnoreCase("4.4.0") < 0) {
                    return true;
                }
            } else {
                if (appVersion.compareToIgnoreCase("4.0.0") < 0) {
                    return true;
                }
            }
        } else {
            if (source.equalsIgnoreCase("baichanghui")) {
                if (appVersion.compareToIgnoreCase("4.4.0") < 0) {
                    return true;
                }
            } else {
                if (appVersion.compareToIgnoreCase("5.0.0") < 0) {
                    return true;
                }
            }
        }
        return false;
    }



    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
        // 修改mtrics
        MetricsManager metricsManager = MetricsManager.getInstance();
        metricsManager.setHttpMethod(request.getMethod());
        metricsManager.setRemoteHost(request.getRemoteHost());
        metricsManager.setHost(HOST);
        metricsManager.setHttpCode(response.getStatus());
        ServiceInfo serviceInfo = SpringWebContextHolder.getBeanWithoutException(ServiceInfo.class);
        if (serviceInfo != null)
            metricsManager.setService(serviceInfo.getName());
        metricsManager.close();
        MDC.remove(TRACE_ID);
        MDC.remove(REQUEST_ID);
    }

    private String getSessionId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(SESSION_ID)) {
                    return cookie.getValue();
                }
            }
        }
        return StringUtils.EMPTY;
    }

    private String getGid(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(GID)) {
                    return cookie.getValue();
                }
            }
        }
        return StringUtils.EMPTY;
    }

    private static String getHost() {
        try {
            for (Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces(); e.hasMoreElements();) {
                NetworkInterface item = e.nextElement();
                for (InterfaceAddress address : item.getInterfaceAddresses()) {
                    if (address.getAddress() instanceof Inet4Address) {
                        Inet4Address inet4Address = (Inet4Address) address.getAddress();
                        if (inet4Address.getHostAddress().startsWith("10.")) {
                            return inet4Address.getHostAddress();
                        }
                    }
                }
            }
        } catch (IOException ex) {
            RAW_LOGGER.error("getIp erro:", ex);
        }
        return StringUtils.EMPTY;
    }
}
