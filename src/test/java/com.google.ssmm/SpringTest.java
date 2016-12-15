package com.google.ssmm;

import com.google.ssmm.config.AOPConfig;
import com.google.ssmm.config.DataConfig;
import com.google.ssmm.config.DataSourceConfig;
import com.google.ssmm.config.WebAppInitializer;
import com.google.ssmm.config.WebConfig;
import com.google.ssmm.entity.TableOne;
import com.google.ssmm.service.FirstServiceInterface;
import com.google.ssmm.service.SecondServiceInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by xuhan on 16-12-13.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class,
    AOPConfig.class, WebConfig.class, WebAppInitializer.class, DataConfig.class})
public class SpringTest {

    @Autowired
    private FirstServiceInterface firstService;

    @Autowired
    private SecondServiceInterface secondService;

    @Test
    public void testTransaction(){
        TableOne one = new TableOne();
        one.setName("oneXuhan");
        firstService.insertTableOne(one);
    }

    @Test
    public void testFormat(){
        String format = String.format("许iuhdiahdiuh%sdshosdhiaushui%s", "虚汗1", "虚汗2");
        System.out.println(format);
    }
}
