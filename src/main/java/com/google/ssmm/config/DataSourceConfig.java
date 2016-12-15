package com.google.ssmm.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl(
            "jdbc:mysql://localhost:3306/xuhan?useUnicode=yes&characterEncoding=UTF-8");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        dataSource.setAcquireIncrement(1);
        dataSource.setAcquireRetryAttempts(3);
        dataSource.setAcquireRetryDelay(300);
        dataSource.setInitialPoolSize(3);
        dataSource.setMaxPoolSize(10);
        dataSource.setMinPoolSize(3);
        dataSource.setIdleConnectionTestPeriod(60);
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws PropertyVetoException {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis.xml"));
        return sqlSessionFactory;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws PropertyVetoException, Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory().getObject());
        return sqlSessionTemplate;
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager() throws PropertyVetoException {

        return new DataSourceTransactionManager(dataSource());
    }
}
