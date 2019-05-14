package com.bdcom.hws.core.datasource;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.common.core.datasource.DatabaseType;
import org.common.core.datasource.DynamicDataSource;
import org.common.core.environment.QuartzProperties;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSourceFactory;

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
    @Bean(name="dynamicDataSource")
    public DataSource dynamicDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,@Qualifier("quartzDataSource") DataSource quartzDataSource) throws Exception {
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setDefaultTargetDataSource(masterDataSource);
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put(DatabaseType.xlt, masterDataSource);
        dataSourceMap.put(DatabaseType.quartz, quartzDataSource);
        dataSource.setTargetDataSources(dataSourceMap);
        return dataSource;
    }

    @Bean(name="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        //此处设置为了解决找不到mapper文件的问题
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 事务管理
     *
     * @return 事务管理实例
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}
