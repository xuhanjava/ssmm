package com.google.ssmm.config;

import com.google.ssmm.service.FirstServiceInterface;
import com.google.ssmm.service.SecondServiceInterface;
import com.google.ssmm.service.impl.FirstServiceImpl;
import com.google.ssmm.service.impl.SecondServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by xuhan on 16-12-13.
 */
@Configuration
@ComponentScan(value ="com.googole.ssmm.service")
@MapperScan(value ="com.google.ssmm.dao") //不扫描dao还是不行的
@EnableTransactionManagement
public class DataConfig {

    /**
     * 这里的方法都必须是public的。private的话就是无法得到对应的bean.通过 @Autowired 只能获得这个单例。new Test()则不受单例限制
     * @Lazy //无论加不加　@Lazy，被　@Scope("prototype")多例　修饰的类都会　懒加载。
     * @return
     */
    @Bean(name = "FirstServiceInterface")
    public FirstServiceInterface getFirstServiceInterface(){
        return new FirstServiceImpl();
    }

    //@Lazy 懒加载模式.默认情况下是恶汉式和单例这种模式
    @Bean(name = "SecondServiceInterface")
    public SecondServiceInterface getSecondServiceInterface(){
        return new SecondServiceImpl();
    }

}
