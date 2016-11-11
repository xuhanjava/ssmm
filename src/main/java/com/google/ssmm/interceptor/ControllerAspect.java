package com.google.ssmm.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ControllerAspect {
    private static final Logger RAW_LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public Object invoke(ProceedingJoinPoint jp) throws Throwable {
        RAW_LOGGER.info("进入系统日志记录");
        Object result = null;
        // 输入输出日志
        IOLog ioLog = new IOLog();
        if (jp.getArgs() != null && jp.getArgs().length > 0) {
            List<Object> args = new ArrayList<Object>();
            for (int i = 0; i < jp.getArgs().length; i++) {
                if (!(jp.getArgs()[i] instanceof HttpServletRequest
                    || jp.getArgs()[i] instanceof HttpServletResponse)) {
                    args.add(jp.getArgs()[i]);
                }
            }
            if (args.size() > 0) {
                ioLog.setInputParam(args.toArray());
            }
        }
        HttpServletRequest request = SysContent.getRequest();
        ioLog.setOperationName(((MethodSignature) jp.getSignature()).getMethod().getName());
        try {
            result = jp.proceed();
            ioLog.setOutputParam(result);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                RAW_LOGGER.info("进入系统日志记录模块");
                if (request != null) {
                    String url = request.getRequestURI().toString();
                    String contextPath = request.getContextPath();
                    url = url.replace(contextPath + "/", "");
                    RAW_LOGGER.info("日志模块记录的url:" + url);
                    SystemCacheService systemCacheService = SpringContextHolder.getBean("systemCacheService");
                    Resource resource = systemCacheService.getResource(url);
                    String needLog = resource.getNeedLog();
                    String needResultLog = resource.getNeedResultLog();
                    if (StringUtils.isNotBlank(needLog) && needLog.equals("Y")) {
                        RAW_LOGGER.info("需要记录输入参数url:" + url);
                        SystemLogWithBLOBs systemLog = new SystemLogWithBLOBs();
                        systemLog.setResource(url);
                        String logPrimarykeyValue = "";
                        systemLog.setInput(mapper.writeValueAsString(ioLog.getInputParam()));
                        String logPrimarykey = resource.getLogPrimarykey();
                        if (StringUtils.isNoneBlank(logPrimarykey)) {
                            logPrimarykeyValue = getLogPrimarykey(ioLog, logPrimarykey);
                            RAW_LOGGER
                                .info("获取主键:" + url + ", 主键key:" + logPrimarykey + ", value:" + logPrimarykeyValue);
                            systemLog.setLogPrimarykey(logPrimarykey);
                            systemLog.setLogPrimarykeyValue(logPrimarykeyValue);
                        }
                        if (StringUtils.isNotBlank(needResultLog) && needResultLog.equals("Y")) {
                            RAW_LOGGER.info("需要记录返回参数url:" + url);
                            systemLog.setOutput(mapper.writeValueAsString(ioLog.getOutputParam()));
                        }
                        systemLog.setDescription(getLogDescription(resource.getDescription(), logPrimarykeyValue));
                        User user = (User) SecurityUtils.getSubject().getPrincipal();
                        if (user != null) {
                            systemLog.setUserName(user.getUserName());
                        }
                        ISystemLogDAO systemLogDAO = SpringContextHolder.getBean("systemLogDAO");
                        systemLogDAO.insert(systemLog);
                    }
                }
                RAW_LOGGER.info("[IO]:" + mapper.writeValueAsString(ioLog));
            } catch (JsonProcessingException e2) {
                RAW_LOGGER.error("write log erro:", e2);
            }
        }
        return result;
    }

    private String getLogPrimarykey(IOLog ioLog, String logPrimarykey) {
        if (ioLog == null || ioLog.getInputParam() == null) {
            return "";
        }
        Object inputParam = ioLog.getInputParam();
        Object outputParam = ioLog.getOutputParam();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<LinkedHashMap<String, Object>> list =
                objectMapper.readValue(mapper.writeValueAsString(inputParam), List.class);
            for (LinkedHashMap<String, Object> linkedHashMap : list) {
                Object object = linkedHashMap.get(logPrimarykey);
                if (object != null) {
                    String valueAsString = mapper.writeValueAsString(object);
                    return valueAsString.replace("\"", "");
                }
            }
        } catch (Exception e) {
            RAW_LOGGER.debug("转换json失败:" + ioLog.getInputParam(), e);
        }
        try {
            LinkedHashMap<String, Object> linkedHashMap =
                objectMapper.readValue(mapper.writeValueAsString(outputParam), LinkedHashMap.class);
            Object object = linkedHashMap.get(logPrimarykey);
            if (object != null) {
                String valueAsString = mapper.writeValueAsString(object);
                return valueAsString.replace("\"", "");
            }
        } catch (Exception e) {
            RAW_LOGGER.debug("转换json失败:" + ioLog.getInputParam(), e);
        }
        return null;
    }

    private String getLogDescription(String description, String logPrimarykeyValue) {
        if (StringUtils.isBlank(logPrimarykeyValue)) {
            return description;
        }
        return description + ":" + logPrimarykeyValue;

    }
}

