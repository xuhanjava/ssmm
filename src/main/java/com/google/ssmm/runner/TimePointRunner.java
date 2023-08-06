package com.google.ssmm.runner;

import com.google.ssmm.dao.FinancePointMapper;
import com.google.ssmm.entity.FinancePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TimePointRunner implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private FinancePointMapper mapper;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {//保证只执行一次
            try {
                System.out.println(123);
                System.out.println(mapper);
                FinancePoint record = new FinancePoint();
                record.setCreateTime(new Date());
                record.setTimePoint("1");
                record.setFinancePointcol("1");
                record.setOutExt("{}");
                record.setPrice("11");
                record.setStockName("1");
                record.setStockNo("1");
                record.setDate("1");
                mapper.insert(record);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
