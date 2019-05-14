package com.bdcom.server.core.quartz;

import java.util.Properties;

import org.common.core.environment.QuartzProperties;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.bdcom.server.core.listener.MyJobListener;

@Configuration
public class ScheduleConfig {
	@Autowired
	MyJobListener myJobListener;
	@Autowired
	MyJobFactory myJobFactory;
	
	@Autowired
	QuartzProperties qp;
	@Bean
	public SchedulerFactoryBean getSchedulerFactoryBean(){
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setJobFactory(myJobFactory);
		Properties quartzProperties = qp.toProperties();
		System.out.println("1111111111111:"+quartzProperties.getProperty("org.quartz.dataSource.quartzDataSource.Url"));
		if(null != quartzProperties)factory.setQuartzProperties(quartzProperties);
		return factory;
	}
	@Bean(name="myScheduler")
	public  Scheduler getScheduler() throws SchedulerException{
		//SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = getSchedulerFactoryBean().getScheduler();
		scheduler.getListenerManager().addJobListener(myJobListener);
		return scheduler;
	}
	
}
