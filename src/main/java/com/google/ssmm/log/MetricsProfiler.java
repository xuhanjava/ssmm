package com.google.ssmm.log;

import java.util.Calendar;
import java.util.Date;

public class MetricsProfiler {
    private String functionName;
    private Date startDate;
    private long time;
    private MetricsManager metricsManager;

    public MetricsProfiler(MetricsManager metricsManager, String functionName, Date startDate) {
        this.metricsManager = metricsManager;
        this.functionName = functionName;
        this.startDate = startDate;
    }

    public void end() {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(new Date());
        this.time = endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();
        metricsManager.addTimes(functionName, time);
    }
}
