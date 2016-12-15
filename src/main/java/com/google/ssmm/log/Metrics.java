package com.google.ssmm.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
import java.util.Map;

class Metrics {
    @JsonProperty(value = "RequestId")
    private String requestId;
    @JsonProperty(value = "TraceId")
    private String traceId;
    @JsonProperty(value = "SessionId")
    private String sessionId;
    @JsonProperty(value = "TokenId")
    private String tokenId;
    @JsonProperty(value = "UserId")
    private String userId;
    @JsonProperty(value = "RemoteHost")
    private String remoteHost;
    @JsonProperty(value = "Host")
    private String host;
    @JsonProperty(value = "Operation")
    private String operation;
    @JsonProperty(value = "Service")
    private String service;
    @JsonProperty(value = "Time")
    private Long time;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT")
    @JsonProperty(value = "StartTime")
    private Date startTime;
    @JsonProperty(value = "HttpMethod")
    private String httpMethod;
    @JsonProperty(value = "HttpCode")
    private int httpCode;
    @JsonProperty(value = "Attributes")
    private Map<String, List<Object>> attributes;
    @JsonProperty(value = "Times")
    private Map<String, List<Long>> times;
    @JsonProperty(value = "Counters")
    private Map<String, List<Integer>> counters;
    @JsonProperty(value = "Domain")
    private String domain;
    @JsonProperty(value = "Gid")
    private String gid;
    @JsonProperty(value = "Path")
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public Map<String, List<Object>> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, List<Object>> attributes) {
        this.attributes = attributes;
    }

    public Map<String, List<Long>> getTimes() {
        return times;
    }

    public void setTimes(Map<String, List<Long>> times) {
        this.times = times;
    }

    public Map<String, List<Integer>> getCounters() {
        return counters;
    }

    public void setCounters(Map<String, List<Integer>> counters) {
        this.counters = counters;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

}
