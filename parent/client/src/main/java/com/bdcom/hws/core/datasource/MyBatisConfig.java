package com.bdcom.hws.core.datasource;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.bdcom.hws.environment.QuartzProperties;

@Configuration
public class MyBatisConfig {
	@Value("${spring.datasource.driverClassName}")
 	private String mysqlDriverName;
 	@Value("${spring.datasource.url}")
 	private String masterUrl;
 	@Value("${spring.datasource.username}")
 	private String commonUserName;
 	@Value("${spring.datasource.password}")
 	private String commonUserPassword;

    @Bean(name="masterDataSource")
    @Primary
    public DataSource masterDataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", mysqlDriverName);
        props.put("url", masterUrl);
        props.put("username", commonUserName);
        props.put("password", commonUserPassword);
        return DruidDataSourceFactory.createDataSource(props);
    }
    @Autowired
    QuartzProperties quartzProperties;
    @Bean(name="quartzDataSource")
    public DataSource quartzDataSource() throws Exception {
    	 Properties props = new Properties();
         props.put("driverClassName", mysqlDriverName);
         props.put("url", quartzProperties.getQuartzUrl());
         props.put("username", commonUserName);
         props.put("password", commonUserPassword);
         return DruidDataSourceFactory.createDataSource(props);
    }
}
