package com.google.ssmm.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class LoggerNameConstants {
    private static Logger LOGGER = LoggerFactory.getLogger(LoggerNameConstants.class);

    public static final String IO_LOGGER = "ioLogger";
    public static final String RAW_LOGGER = "rawLogger";
    public static final String PERFORMANCE_LOGGER = "performanceLogger";

    public static void main(String[] args) {
        List<Long> subPoiList = Arrays.asList(1l);
        System.out.println("-------------------------");
        LOGGER.error("stagedPoiBase from TDC is empty! subPoiLoist={}", subPoiList);
    }

}
