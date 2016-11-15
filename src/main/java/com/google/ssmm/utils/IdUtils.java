package com.google.ssmm.utils;

import java.util.UUID;

public class IdUtils {
    public static String getTraceId() {
        return UUID.randomUUID().toString();
    }

    public static String getRequestId() {
        return UUID.randomUUID().toString();
    }

    public static String getSessionId() {
        return UUID.randomUUID().toString();
    }
}
