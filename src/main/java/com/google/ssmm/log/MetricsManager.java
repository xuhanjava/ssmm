package com.google.ssmm.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *  Metrics:指标
 */
public class MetricsManager {
    private static final ThreadLocal<Metrics> threadLocalMetrics = new ThreadLocal<Metrics>();
    private IMetricsOutPut iMetricsOutPut;
    private static final Logger LOGGER = LoggerFactory.getLogger(MetricsManager.class);

    private MetricsManager() {}

    private static class staticHolder {
        private static final MetricsManager MANAGER = new MetricsManager();
    }

    private Metrics getMetrics() {
        Metrics metrics = threadLocalMetrics.get();
        if (metrics == null) {
            metrics = new Metrics();
            threadLocalMetrics.set(metrics);
        }
        return metrics;
    }

    public Metrics createNewMetrics() {
        threadLocalMetrics.set(new Metrics());
        return threadLocalMetrics.get();
    }

    public void close() {
        // 统计接口执行时间,纪录日志
        threadLocalMetrics.get().setTime(getSpanTime());
        ObjectMapper mapper = new ObjectMapper();
        try {
            String metricsLog = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(getMetrics());
            this.iMetricsOutPut.saveLog(metricsLog);
        } catch (Exception e) {
            LOGGER.error("metrics close error:", e);
        }

        threadLocalMetrics.set(null);
    }

    public static MetricsManager getInstance() {
        return staticHolder.MANAGER;
    }

    public void setRequestId(String requestId) {
        getMetrics().setRequestId(requestId);
    }

    public void setTraceId(String traceId) {
        getMetrics().setTraceId(traceId);
    }

    public void setGid(String gid) {
        getMetrics().setGid(gid);
    }

    public void setPath(String path) {
        getMetrics().setPath(path);
    }

    public void setSessionId(String sessionId) {
        getMetrics().setSessionId(sessionId);
    }

    public void setTokenId(String tokenId) {
        getMetrics().setTokenId(tokenId);
    }

    public void setUserId(String userId) {
        getMetrics().setUserId(userId);
    }

    public void setRemoteHost(String remoteHost) {
        getMetrics().setRemoteHost(remoteHost);
    }

    public void setHost(String host) {
        getMetrics().setHost(host);
    }

    public void setOperation(String operation) {
        getMetrics().setOperation(operation);
    }

    public void setService(String service) {
        getMetrics().setService(service);
    }

    public void setTime(Long time) {
        getMetrics().setTime(time);
    }

    public void setStartTime(Date startTime) {
        getMetrics().setStartTime(startTime);
    }

    public void setHttpMethod(String httpMethod) {
        getMetrics().setHttpMethod(httpMethod);
    }

    public void setHttpCode(int httpCode) {
        getMetrics().setHttpCode(httpCode);
    }

    public void setDomain(String domain) {
        getMetrics().setDomain(domain);
    }

    public void addAtrributes(String key, Object value) {
        Map<String, List<Object>> attributes = getMetrics().getAttributes();
        if (attributes == null) {
            getMetrics().setAttributes(new HashMap<String, List<Object>>());
            attributes = getMetrics().getAttributes();
        }
        if (attributes.containsKey(key)) {
            attributes.get(key).add(value);
        } else {
            List<Object> values = new ArrayList<Object>();
            values.add(value);
            attributes.put(key, values);
        }
    }

    public void addTimes(String key, long value) {
        Map<String, List<Long>> times = getMetrics().getTimes();
        if (times == null) {
            getMetrics().setTimes(new HashMap<String, List<Long>>());
            times = getMetrics().getTimes();
        }
        if (times.containsKey(key)) {
            times.get(key).add(value);
        } else {
            List<Long> values = new ArrayList<Long>();
            values.add(value);
            times.put(key, values);
        }
    }

    public void addCounter(String key, int value) {
        Map<String, List<Integer>> counters = getMetrics().getCounters();
        if (counters == null) {
            getMetrics().setCounters(new HashMap<String, List<Integer>>());
            counters = getMetrics().getCounters();
        }
        if (counters == null) {
            getMetrics().setCounters(new HashMap<String, List<Integer>>());
            counters = getMetrics().getCounters();
        }
        if (counters.containsKey(key)) {
            counters.get(key).add(value);
        } else {
            List<Integer> values = new ArrayList<Integer>();
            values.add(value);
            counters.put(key, values);
        }
    }

    public IMetricsOutPut getIMetricsOutPut() {
        return iMetricsOutPut;
    }

    public void setIMetricsOutPut(IMetricsOutPut iMetricsOutPut) {
        this.iMetricsOutPut = iMetricsOutPut;
    }

    public MetricsProfiler startProfiler(String functionName) {
        return new MetricsProfiler(this, functionName, new Date());
    }

    private long getSpanTime() {
        if (getMetrics().getStartTime() != null) {
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(getMetrics().getStartTime());
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(new Date());
            return endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();
        } else {
            return 0l;
        }
    }

}
